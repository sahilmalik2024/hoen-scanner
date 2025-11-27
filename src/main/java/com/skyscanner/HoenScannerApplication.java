package com.skyscanner;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;


public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    private java.util.List<SearchResult> searchResults;

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hoen-scanner";
    }

    @Override
    public void initialize(final Bootstrap<HoenScannerConfiguration> bootstrap) {

    }

    @Override
    public void run(final HoenScannerConfiguration configuration, final Environment environment) throws Exception {

        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

        java.util.List<SearchResult> carResults = java.util.Arrays.asList(
                mapper.readValue(
                        getClass().getClassLoader().getResource("rental_cars.json"),
                        SearchResult[].class
                )
        );

        java.util.List<SearchResult> hotelResults = java.util.Arrays.asList(
                mapper.readValue(
                        getClass().getClassLoader().getResource("hotels.json"),
                        SearchResult[].class
                )
        );

        searchResults = new java.util.ArrayList<>();
        searchResults.addAll(carResults);
        searchResults.addAll(hotelResults);

        environment.jersey().register(new SearchResource(searchResults));
    }

}
