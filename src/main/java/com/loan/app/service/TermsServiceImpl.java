package com.loan.app.service;

import com.loan.app.domain.Terms;
import com.loan.app.dto.TermsDTO.Request;
import com.loan.app.dto.TermsDTO.Response;
import com.loan.app.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {
    private final ModelMapper modelMapper;
    private final TermsRepository termsRepository;

    @Override
    public Response create(Request request) {
        Terms terms = modelMapper.map(request, Terms.class);

        Terms created = termsRepository.save(terms);

        return modelMapper.map(created, Response.class);
    }

    @Override
    public List<Response> getAll() {
        List<Terms> termsList = termsRepository.findAll();
        return termsList.stream().map(t -> modelMapper.map(t, Response.class)).collect(Collectors.toList());
    }
}
