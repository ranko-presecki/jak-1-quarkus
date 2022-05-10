package jak.meetup.client;

import jak.meetup.model.Sorting;
import jak.meetup.model.SortingResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/v1")
public interface SortingClient {
    @POST
    @Path("sorting")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    SortingResponse sortData(Sorting body) throws ProcessingException;

}
