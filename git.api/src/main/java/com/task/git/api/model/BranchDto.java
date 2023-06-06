package com.task.git.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchDto {
    private String name;
    private String sha;

    @JsonProperty("commit")
    private void  unpackNameFromNestedObject(Map<String, String> commit){
        sha = commit.get("sha");
    }
}
