package jak.meetup.service;

import io.quarkus.arc.log.LoggerName;
import jak.meetup.client.SortingClient;
import jak.meetup.model.Sorting;
import jak.meetup.model.SortingResponse;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ProcessingException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class SortingService {

    @ConfigProperty(name = "sorting.urls")
    Optional<String> sortingUrls;
    private List<String> destinationList;

    @LoggerName("FILE")
    Logger fileLog;

    @LoggerName("ERROR")
    Logger errorLog;

    public SortingResponse sort(Sorting sorting) {
        SortingResponse ret = sorting(sorting.getArraySize());
        fileLog.infof("[SortingService.sort] Sorting performed with %s logged", ret.getTimeSpentInMs());
        return ret;
    }

    private static SortingResponse sorting(long arraySize) {
        long start = System.currentTimeMillis();
        List<String> l = new ArrayList<>();
        for (int i = 0; i <= arraySize; i++) {
            l.add(UUID.randomUUID().toString());
        }
        long startSort = System.currentTimeMillis();
        Collections.sort(l);
        long end = System.currentTimeMillis();

        return new SortingResponse()
                .timeSpentCreatingListInMs(startSort - start)
                .timeSpentSortingListInMs(end - startSort)
                .timeSpentInMs(end - start);
    }

    public Optional<String> getDestination() {
        if (sortingUrls.isEmpty()) {
            return Optional.empty();
        }

        // init
        if (destinationList == null) {
            destinationList = Arrays.stream(sortingUrls.get().split(","))
                    .map(String::trim)
                    .filter(element -> !"".equals(element))
                    .collect(Collectors.toList());
        }

        int destinationOptions = destinationList.size();
        if (destinationOptions == 0) {
            return Optional.empty();
        }

        int destinationOption = new Random().nextInt(destinationOptions);
        return Optional.of(destinationList.get(destinationOption));
    }

    public SortingResponse delegatedSort(final String destination, Sorting body) {
        SortingClient sortingClient = RestClientBuilder.newBuilder()
                .baseUri(URI.create(destination))
                .build(SortingClient.class);

        try {
            return sortingClient.sortData(body);
        } catch (ProcessingException e) {
            errorLog.error("Error calling sortClient", e);
            throw e;
        }
    }
}
