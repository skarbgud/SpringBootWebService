package com.gyuhyeong.example.springboot.web;

import com.gyuhyeong.example.springboot.web.dto.HelloResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환하는 RestController로 만들어 준다 -> 스프링에서 각 메소드마다 @ResponseBody를 선언했던것을 한번에 사용할 수 있게 해준다.
public class HelloController {

    @GetMapping("/hello")       // == @RequestMapping(method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDTO helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDTO(name, amount);
    }
}
