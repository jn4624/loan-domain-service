package com.loan.app.service;

import com.loan.app.domain.Counsel;
import com.loan.app.dto.CounselDTO.Response;
import com.loan.app.dto.CounselDTO.Request;
import com.loan.app.exception.BaseException;
import com.loan.app.exception.ResultType;
import com.loan.app.repository.CounselRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CounselServiceTest {
    @InjectMocks
    CounselServiceImpl counselService;

    @Spy
    private ModelMapper modelMapper;

    @Mock
    private CounselRepository counselRepository;

    @Test
    void Should_ReturnResponseOfNewCounselEntity_When_RequestCounsel() {
        Counsel entity = Counsel.builder()
                .name("Member Lim")
                .cellPhone("010-1234-5678")
                .email("abcdefg@naver.com")
                .memo("저는 대출을 받고 싶어요. 연락을 주세요.")
                .address("서울특별시 어딘구 모른동")
                .addressDetail("123동 345호")
                .zipCode("12345")
                .build();

        Request request = Request.builder()
                .name("Member Lim")
                .cellPhone("010-1234-5678")
                .email("abcdefg@naver.com")
                .memo("저는 대출을 받고 싶어요. 연락을 주세요.")
                .address("서울특별시 어딘구 모른동")
                .addressDetail("123동 345호")
                .zipCode("12345")
                .build();

        when(counselRepository.save(any(Counsel.class))).thenReturn(entity);

        Response actual = counselService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
    }

    @Test
    void Should_ReturnResponseOfExistCounselEntity_When_RequestExistCounselId() {
        Long findId = 1L;

        Counsel entity = Counsel.builder()
                .counselId(1L)
                .build();

        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = counselService.get(findId);

        assertThat(actual.getCounselId()).isSameAs(findId);
    }

    @Test
    void Should_ThrowException_When_RequestNotExistCounselId() {
        Long findId = 2L;

        when(counselRepository.findById(findId)).thenThrow(new BaseException(ResultType.SYSTEM_ERROR));

        Assertions.assertThrows(BaseException.class, () -> counselService.get(findId));
    }

    @Test
    void Should_ReturnUpdatedResponseOfExistCounselEntity_When_RequestUpdateExistCounselInfo() {
        Long findId = 1L;

        Counsel entity = Counsel.builder()
                .counselId(1L)
                .name("Member Lim")
                .build();

        Request request = Request.builder()
                .name("Member Kim")
                .build();

        when(counselRepository.save(any(Counsel.class))).thenReturn(entity);
        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = counselService.update(findId, request);

        assertThat(actual.getCounselId()).isSameAs(findId);
        assertThat(actual.getName()).isSameAs(request.getName());
    }

    @Test
    void Should_DeletedCounselEntity_When_RequestDeleteExistCounselInfo() {
        Long targetId = 1L;

        Counsel entity = Counsel.builder()
                .counselId(1L)
                .build();

        when(counselRepository.save(any(Counsel.class))).thenReturn(entity);
        when(counselRepository.findById(targetId)).thenReturn(Optional.ofNullable(entity));

        counselService.delete(targetId);

        assertThat(entity.isDeleted()).isSameAs(true);
    }
}
