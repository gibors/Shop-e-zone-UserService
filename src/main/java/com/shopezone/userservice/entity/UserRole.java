package com.shopezone.userservice.entity;

public enum UserRole {
    USER, ADMIN;

    public static boolean isValidRole(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

    public static UserRole fromString(String value) {
        return valueOf(value.toUpperCase());
    }
}
