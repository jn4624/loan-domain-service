package com.loan.app.controller;

import com.loan.app.dto.CounselDTO.Response;
import com.loan.app.dto.CounselDTO.Request;
import com.loan.app.dto.ResponseDTO;
import com.loan.app.service.CounselService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/counsels")
public class CounselController extends AbstractController {
    private final CounselService counselService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request) {
        return ok(counselService.create(request));
    }
}
