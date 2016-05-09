package com.hackday.entity;

public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    private final String name;

    UserRole(final String s) {
        name = s;
    }

    @Override
    public String toString() {
        return name;
    }
}
