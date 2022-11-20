package com.example.auth2github;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Data
public class Controller {

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @GetMapping("/user")
    public Map<String, Object> user(
            @AuthenticationPrincipal OAuth2User principal,
            Authentication auth)
    {
        var oauthToken = (OAuth2AuthenticationToken) auth;
        var client = oAuth2AuthorizedClientService.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName()
        );

        System.out.println(client.getAccessToken().getTokenValue());
        return principal.getAttributes();
    }




}
