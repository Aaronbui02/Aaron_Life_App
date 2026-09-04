package com.aaronlifeapp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.stereotype.Service;

@Service
public class DatabaseOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {

    private final GoogleTokensRepository googleTokensRepository;
    private final ClientRegistrationRepository clientRegistrationRepository;

    public DatabaseOAuth2AuthorizedClientService(GoogleTokensRepository googleTokensRepository,
                                                 ClientRegistrationRepository clientRegistrationRepository) {
        this.googleTokensRepository = googleTokensRepository;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        return (T) googleTokensRepository.findAll().stream().findFirst()
                .map(tokens -> new OAuth2AuthorizedClient(
                        clientRegistrationRepository.findByRegistrationId(clientRegistrationId),
                        principalName,
                        new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, tokens.getAccessToken(), null, tokens.getExpiresAt()),
                        tokens.getRefreshToken() == null ? null : new OAuth2RefreshToken(tokens.getRefreshToken(), null)
                ))
                .orElse(null);
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
        GoogleTokens tokens = googleTokensRepository.findAll().stream().findFirst().orElse(new GoogleTokens());
        tokens.setAccessToken(authorizedClient.getAccessToken().getTokenValue());
        tokens.setExpiresAt(authorizedClient.getAccessToken().getExpiresAt());
        if (authorizedClient.getRefreshToken() != null) {
            tokens.setRefreshToken(authorizedClient.getRefreshToken().getTokenValue());
        }
        googleTokensRepository.save(tokens);
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
        googleTokensRepository.deleteAll();
    }
}