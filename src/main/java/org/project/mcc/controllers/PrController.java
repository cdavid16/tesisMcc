package org.project.mcc.controllers;

import org.project.mcc.ProcessPullRequest;
import org.project.mcc.models.PrRequest;
import org.project.mcc.models.PullRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class PrController {

    private final ProcessPullRequest processor;

    @Autowired
    public PrController(RestTemplate restTemplate) {
        processor = new ProcessPullRequest(restTemplate);
    }

    @PostMapping("pr/create")
    public ResponseEntity<PullRequest> exportPrToAws(@RequestBody PrRequest body) {
        PullRequest pr = processor.processPr(body.getRepository(), body.getPrId());
        return ResponseEntity.of(Optional.ofNullable(pr));
    }

}
