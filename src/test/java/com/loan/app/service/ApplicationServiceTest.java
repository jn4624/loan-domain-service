package com.loan.app.service;

import com.loan.app.domain.Application;
import com.loan.app.dto.ApplicationDTO.Request;
import com.loan.app.dto.ApplicationDTO.Response;
import com.loan.app.exception.BaseException;
import com.loan.app.exception.ResultType;
import com.loan.app.repository.ApplicationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

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

    @Test
    void Should_ReturnResponseOfExistApplicationEntity_When_RequestExistApplicationId() {
        Long findId = 1L;

        Application entity = Application.builder()
                .applicationId(1L)
                .build();

        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = applicationService.get(findId);

        assertThat(actual.getApplicationId()).isSameAs(findId);
    }

    @Test
    void Should_ThrowException_When_RequestNotExistApplicationId() {
        Long findId = 1L;

        when(applicationRepository.findById(findId)).thenThrow(new BaseException(ResultType.SYSTEM_ERROR));

        Assertions.assertThrows(BaseException.class, () -> applicationService.get(findId));
    }

    @Test
    void Should_ReturnUpdatedResponseOfExistApplicationEntity_When_RequestUpdateExistApplicationInfo() {
        Long findId = 1L;

        Application entity = Application.builder()
                .applicationId(1L)
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();

        Request request = Request.builder()
                .hopeAmount(BigDecimal.valueOf(10000000))
                .build();

        when(applicationRepository.save(any(Application.class))).thenReturn(entity);
        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = applicationService.update(findId, request);

        assertThat(actual.getApplicationId()).isSameAs(findId);
        assertThat(actual.getHopeAmount()).isSameAs(request.getHopeAmount());
    }

    @Test
    void Should_DeletedApplicationEntity_When_RequestDeleteExistApplicationInfo() {
        Long targetId = 1L;

        Application entity = Application.builder()
                .applicationId(1L)
                .build();

        when(applicationRepository.save(any(Application.class))).thenReturn(entity);
        when(applicationRepository.findById(targetId)).thenReturn(Optional.ofNullable(entity));

        applicationService.delete(targetId);

        assertThat(entity.isDeleted()).isSameAs(true);
    }
}
