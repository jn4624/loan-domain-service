package com.loan.app.service;

import com.loan.app.dto.TermsDTO.Response;
import com.loan.app.dto.TermsDTO.Request;

public interface TermsService {
    Response create(Request request);
}
