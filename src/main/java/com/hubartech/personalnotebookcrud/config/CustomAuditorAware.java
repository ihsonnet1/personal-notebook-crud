package com.hubartech.personalnotebookcrud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableJpaAuditing
public class CustomAuditorAware {
    public Optional<LocalDateTime> getCreatedOn() {
        return Optional.of(LocalDateTime.now());
    }
}
