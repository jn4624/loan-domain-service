package com.loan.app.service;

import com.loan.app.domain.Counsel;
import com.loan.app.dto.CounselDTO.Response;
import com.loan.app.dto.CounselDTO.Request;
import com.loan.app.repository.CounselRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

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
}
