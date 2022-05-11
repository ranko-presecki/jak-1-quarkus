package jak.meetup;

import io.micrometer.core.annotation.Counted;
import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
import jak.meetup.logging.interceptor.LogPerformance;
import jak.meetup.model.Sorting;
import jak.meetup.model.SortingResponse;
import jak.meetup.service.SortingService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@LogPerformance
public class SortingResource {

    @Inject
    SortingService sortingService;

    @Inject
    Vertx vertx;

    @POST
    @Path("sorting")
    @Counted("demo.request.counter")
    public SortingResponse sortData(Sorting body) throws ProcessingException {
        validate(body);
        return sortingService.sort(body);
    }

    @POST
    @Path("delegated/sorting")
    public Uni<Response> sortDataDelegated(Sorting body) {
        Optional<String> destination = sortingService.getDestination();
        if (destination.isEmpty()) {
            throw new ResponseProcessingException(Response.status(Response.Status.SERVICE_UNAVAILABLE).build(), "no defined destinations");
        }

        vertx.executeBlocking(promise -> promise.complete(sortingService.delegatedSort(destination.get(), body)));

        return Uni.createFrom().item(Response.ok().build());
    }

    private void validate(Sorting body) throws ProcessingException {
        if (body == null || body.getArraySize() == null) {
            throw new ResponseProcessingException(Response.status(Response.Status.BAD_REQUEST).build(), "no arraySize");
        } else if (body.getArraySize() > Integer.MAX_VALUE) {
            throw new ResponseProcessingException(Response.status(Response.Status.BAD_REQUEST).build(), "arraySize too large");
        }
    }
}
