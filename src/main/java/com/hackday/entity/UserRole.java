package com.hackday.entity;

public enum UserRole {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String name;

    UserRole(final String s) {
        name = s;
    }

    @Override
    public String toString() {
        return name;
    }
}
