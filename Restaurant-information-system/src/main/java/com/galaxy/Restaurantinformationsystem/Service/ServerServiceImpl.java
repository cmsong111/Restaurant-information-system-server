package com.galaxy.Restaurantinformationsystem.Service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ServerServiceImpl implements ServerService {
    @Override
    public String updatekids() {

        WebClient client = WebClient.builder()
                .baseUrl("https://api.odcloud.kr")
                .build();

        String response = client.get().uri(uri -> uri.path("/api/15102055/v1/uddi:122cc22d-cde3-41d0-83c8-db83298b994f")
                        .queryParam("serviceKey", "P85FKUFu2kJIbADd5Sf2NMcuhK9sGMcciPLj3s0tm+tOG9SBwS0Dk6t5dkUnl0Un7EA/5TzGsbI/2fV+cmA4/Q==")
                        .queryParam("perPage", 500)
                        .queryParam("page", 1)
                        .build()
                )
                .retrieve()
                .bodyToFlux(String.class)
                .toString();
        System.out.println(response);
        return response;
    }
}
