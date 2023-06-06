package com.task.git.api.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JacksonXmlRootElement
public class ErrorResponseBody {
    private String status;
    private String message;
}
