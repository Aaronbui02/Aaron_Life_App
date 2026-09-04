package com.aaronlifeapp.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoogleTokensRepository extends JpaRepository<GoogleTokens, String> {
}