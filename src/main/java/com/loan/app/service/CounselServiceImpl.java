package com.loan.app.service;

import com.loan.app.domain.Counsel;
import com.loan.app.dto.CounselDTO.Request;
import com.loan.app.dto.CounselDTO.Response;
import com.loan.app.exception.BaseException;
import com.loan.app.exception.ResultType;
import com.loan.app.repository.CounselRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CounselServiceImpl implements CounselService {
    private final ModelMapper modelMapper;
    private final CounselRepository counselRepository;

    @Override
    public Response create(Request request) {
        Counsel counsel = modelMapper.map(request, Counsel.class);
        counsel.setAppliedAt(LocalDateTime.now());

        Counsel created = counselRepository.save(counsel);

        return modelMapper.map(created, Response.class);
    }

    @Override
    public Response get(Long counselId) {
        Counsel counsel = counselRepository.findById(counselId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        return modelMapper.map(counsel, Response.class);
    }
}
