package com.galaxy.Restaurantinformationsystem.common;

public enum UserRole {
    MANAGER, USER, ADMIN;

    public String getAuthority() {
        return "ROLE_" + name();
    }
}
