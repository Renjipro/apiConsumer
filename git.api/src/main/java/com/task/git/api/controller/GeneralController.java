package com.task.git.api.controller;

import com.task.git.api.model.ErrorResponseBody;
import com.task.git.api.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class GeneralController {

    private final GeneralService generalService;

    @GetMapping(value = "repositories/{username}")
    @ResponseBody
    public ResponseEntity getRepositories(@RequestHeader("Accept") String accept, @PathVariable("username") String username) throws IOException, InterruptedException {
        if ("application/xml".equals(accept)) {
            return ResponseEntity.status(HttpStatusCode.valueOf(406)).body(ErrorResponseBody
                    .builder()
                    .status("406")
                    .message("Incorrect header value")
                    .build()
            );
        }
        return generalService.getRepositoryResponseEntity(username);
    }
}
