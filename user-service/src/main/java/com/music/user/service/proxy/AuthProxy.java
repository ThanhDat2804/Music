package com.music.user.service.proxy;

import com.music.user.service.config.FeignClientConfig;
import com.music.user.service.dto.KeycloakUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "auth-service",configuration = FeignClientConfig.class)
public interface AuthProxy {

    @GetMapping("/auth/users/{userId}")
    KeycloakUser getUserById(
            @PathVariable String userId);
}
