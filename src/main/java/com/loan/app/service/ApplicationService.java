package com.loan.app.service;

import com.loan.app.dto.ApplicationDTO.Request;
import com.loan.app.dto.ApplicationDTO.Response;

public interface ApplicationService {
    Response create(Request request);

    Response get(Long applicationId);

    Response update(Long applicationId, Request request);
}
