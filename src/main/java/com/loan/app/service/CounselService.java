package com.loan.app.service;

import com.loan.app.dto.CounselDTO.Request;
import com.loan.app.dto.CounselDTO.Response;

public interface CounselService {
    Response create(Request request);

    Response get(Long counselId);
}
