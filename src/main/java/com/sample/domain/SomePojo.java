package com.sample.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.InetAddress;
import java.security.Security;

/**
 * Created by Kane on 2022/07/29.
 */
@Component
@ConfigurationProperties(prefix = "foo")
@Getter
@Setter
@ToString
@Validated
public class SomePojo {

    // @Value("${foo.remote-address")
    @NotNull
    InetAddress remoteAddress;

    // @Value("${foo.security.username")
    // String securityUsername;

    @Valid
    Security security = new Security();

    @Getter
    @Setter
    @ToString
    public static class Security {

        @NotEmpty
        String username;

        // 게터(getters)와 세터(setters)
    }
}
