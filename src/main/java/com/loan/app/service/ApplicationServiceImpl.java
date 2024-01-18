package com.loan.app.service;

import com.loan.app.domain.Application;
import com.loan.app.dto.ApplicationDTO.Request;
import com.loan.app.dto.ApplicationDTO.Response;
import com.loan.app.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ModelMapper modelMapper;
    private final ApplicationRepository applicationRepository;

    @Override
    public Response create(Request request) {
        Application application = modelMapper.map(request, Application.class);
        application.setAppliedAt(LocalDateTime.now());

        Application created = applicationRepository.save(application);

        return modelMapper.map(created, Response.class);
    }
}
