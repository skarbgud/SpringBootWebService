package com.gyuhyeong.example.springboot;

import com.gyuhyeong.example.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)    // 테스트를 진행할때 JUnit에 내장된 실행자 외에 다른 실행자를 실행 시킨다. 여기서는 SpringRunner라는 스프링 실행자를 사용. 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
@WebMvcTest(controllers = {HelloController.class}, secure = false)   // 스프링 테스트 어노테이션 중 Spring MVC에 집중할 수 있는 어노테이션. @Controller, @ControllerAdvice등을 사용할 수 있다. @Service, @Component, @Repository등은 사용x. 여기서는 컨트롤러만 사용하기 때문에 선언
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;    // 웹 API를 테스트를 할 때 사용한다. 스프링 MVC 테스트의 시작점. 이 클래스를 통해 HTTP GET, POST등에 대한 API를 테스트 할 수 있다.

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))   // API 테스트할 때 요청 파라미터는 String 값만 허용해서 문자열로 변경
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))    // JSON 응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
