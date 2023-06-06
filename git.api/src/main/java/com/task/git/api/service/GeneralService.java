package com.task.git.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.git.api.model.ErrorResponseBody;
import com.task.git.api.model.RepositoryDto;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@NoArgsConstructor
public class GeneralService {

    public ResponseEntity getRepositoryResponseEntity(String username) throws IOException, InterruptedException {

        String url = String.format("https://api.github.com/users/%s/repos", username);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(url)
        ).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 404){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(ErrorResponseBody
                    .builder()
                    .status("404")
                    .message("Given user does not exists")
                    .build()
            );
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<RepositoryDto> dtoList = objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class, RepositoryDto.class));
        for( RepositoryDto var : dtoList){
            var.branches();
        }

        return ResponseEntity.ok(dtoList.stream().filter(c -> !c.isFork()));
    }
}
