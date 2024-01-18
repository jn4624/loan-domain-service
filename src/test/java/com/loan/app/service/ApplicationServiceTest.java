package com.loan.app.service;

import com.loan.app.domain.Application;
import com.loan.app.dto.ApplicationDTO.Request;
import com.loan.app.dto.ApplicationDTO.Response;
import com.loan.app.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {
    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewApplicationEntity_When_RequestCreateApplication() {
        Application entity = Application.builder()
                .name("Member Lim")
                .cellPhone("010-1234-5678")
                .email("abcdefg@naver.com")
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();

        Request request = Request.builder()
                .name("Member Lim")
                .cellPhone("010-1234-5678")
                .email("abcdefg@naver.com")
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();

        when(applicationRepository.save(any(Application.class))).thenReturn(entity);

        Response actual = applicationService.create(request);

        assertThat(actual.getHopeAmount()).isSameAs(entity.getHopeAmount());
        assertThat(actual.getName()).isSameAs(entity.getName());
    }
}
