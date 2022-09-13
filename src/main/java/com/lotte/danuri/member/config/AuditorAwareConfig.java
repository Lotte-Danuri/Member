package com.lotte.danuri.member.config;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@EnableJpaAuditing
@Configuration
public class AuditorAwareConfig implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        HttpServletRequest request = ((ServletRequestAttributes)
            RequestContextHolder.currentRequestAttributes()).getRequest();
        Long memberId = Long.parseLong(
            Optional
                .ofNullable(request.getHeader(("memberId")))
                .orElse("-1"));
        return Optional.ofNullable(memberId);
    }

}
