package com.sample.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.sample.ComponentScanBasePackage; // 상위 패키지를 스캔의 기준으로 한다
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Configuration, @EnableAutoConfiguration, @ComponentScan을 지정한 것과 동일
@SpringBootApplication(scanBasePackageClasses = { ComponentScanBasePackage.class })
@RestController // 원래 컨트롤러에 작성할 애너테이션
public class Application {

    @RequestMapping("/")
    public String hello() {
        return "Hello World!! take";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
