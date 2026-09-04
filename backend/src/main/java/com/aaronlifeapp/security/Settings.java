package com.aaronlifeapp.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String pinHash;

    public void setId(String id) {
        this.id = id;
    }
    public void setPinHash(String pinHash) {
        this.pinHash = pinHash;
    }

    public String getId() {
        return this.id;
    }
    public String getPinHash() {
        return this.pinHash;
    }
}