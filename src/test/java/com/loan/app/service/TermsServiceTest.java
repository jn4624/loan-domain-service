package com.loan.app.service;

import com.loan.app.domain.Terms;
import com.loan.app.dto.TermsDTO.Request;
import com.loan.app.dto.TermsDTO.Response;
import com.loan.app.repository.TermsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TermsServiceTest {
    @InjectMocks
    TermsServiceImpl termsService;

    @Mock
    private TermsRepository termsRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewTermsEntity_When_RequestCreateTerms() {
        Terms entity = Terms.builder()
                .name("대출 이용 약관")
                .termsDetailUrl("https://abc-storage.acc/abcdefg")
                .build();

        Request request = Request.builder()
                .name("대출 이용 약관")
                .termsDetailUrl("https://abc-storage.acc/abcdefg")
                .build();

        when(termsRepository.save(any(Terms.class))).thenReturn(entity);

        Response actual = termsService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
        assertThat(actual.getTermsDetailUrl()).isSameAs(entity.getTermsDetailUrl());
    }

    @Test
    void Should_ReturnAllResponseOfExistTermsEntities_When_RequestTermsList() {
        Terms entityA = Terms.builder()
                .name("대출 이용 약관 1")
                .termsDetailUrl("https://abc-defg.hij/klmn")
                .build();

        Terms entityB = Terms.builder()
                .name("대출 이용 약관 2")
                .termsDetailUrl("https://abc-defg.hij/klmn")
                .build();

        List<Terms> list = new ArrayList<>(Arrays.asList(entityA, entityB));

        when(termsRepository.findAll()).thenReturn(list);

        List<Response> actual = termsService.getAll();

        assertThat(actual.size()).isSameAs(list.size());
    }
}
