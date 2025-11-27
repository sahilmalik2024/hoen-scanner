package com.skyscanner;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchResource {
    private final List<SearchResult> allResults;

    public SearchResource(List<SearchResult> allResults) {
        this.allResults = allResults;
    }

    @POST
    public List<SearchResult> search(Search search) {
        String city = search.getCity();

        if (city == null || city.isEmpty()) {
            // return empty list (could also return 400 bad request in a real service)
            return java.util.Collections.emptyList();
        }

        return allResults.stream()
                .filter(result -> result.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }
}
