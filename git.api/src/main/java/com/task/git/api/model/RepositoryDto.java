package com.task.git.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value={ "fork" }, allowSetters= true)
public class RepositoryDto {
    private boolean fork;
    private String name;
    private String login;
    @JsonProperty("owner")
    private void  unpackNameFromNestedObject(Map<String, String> owner){
        login = owner.get("login");
    }
    private List<BranchDto> branches;

    public void branches() throws IOException, InterruptedException {
        String url = String.format("https://api.github.com/repos/%s/%s/branches", login, name);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(url)
        ).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        branches = objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class, BranchDto.class));
    }

}
