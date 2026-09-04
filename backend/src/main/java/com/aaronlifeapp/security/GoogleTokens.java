package com.aaronlifeapp.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.Instant;

@Entity
public class GoogleTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(columnDefinition = "TEXT")
    private String accessToken;

    @Column(columnDefinition = "TEXT")
    private String refreshToken;

    private Instant expiresAt;

    // getters/setters unchanged
    public void setId(String id) { this.id = id; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
    public String getId() { return this.id; }
    public String getAccessToken() { return this.accessToken; }
    public String getRefreshToken() { return this.refreshToken; }
    public Instant getExpiresAt() { return this.expiresAt; }
}