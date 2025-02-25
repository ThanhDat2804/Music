package com.music.auth.service.api;

import com.music.auth.service.service.KeycloakUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class PublicApi {

    private final KeycloakUserService keycloakUserService;

    @PutMapping("/{username}/forgot-password")
    public void forgotPassword(@PathVariable String username) {
        keycloakUserService.forgotPassword(username);
    }
}
