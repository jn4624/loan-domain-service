package com.loan.app.controller;

import com.loan.app.dto.ApplicationDTO.Request;
import com.loan.app.dto.ApplicationDTO.Response;
import com.loan.app.dto.ResponseDTO;
import com.loan.app.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applications")
public class ApplicationController extends AbstractController {
    private final ApplicationService applicationService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request) {
        return ok(applicationService.create(request));
    }
}
