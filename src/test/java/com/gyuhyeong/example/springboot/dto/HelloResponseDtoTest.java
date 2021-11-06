package com.gyuhyeong.example.springboot.dto;

import com.gyuhyeong.example.springboot.web.dto.HelloResponseDTO;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDTO dto = new HelloResponseDTO(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);  // assertThat -> assertj라는 테스트 검증 라이브러리의 검증메소드
        assertThat(dto.getAmount()).isEqualTo(amount);  // JUnit의 assertThat보다 assertj의 assertThat이 더 좋은 장점 -> CoreMatchers와 달리 추가적인 라이브러리 필요x, 자동완성이 더 확실하게 지원
    }
}
