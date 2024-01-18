package com.loan.app.service;

import com.loan.app.dto.TermsDTO.Response;
import com.loan.app.dto.TermsDTO.Request;

import java.util.List;

public interface TermsService {
    Response create(Request request);

    List<Response> getAll();
}
