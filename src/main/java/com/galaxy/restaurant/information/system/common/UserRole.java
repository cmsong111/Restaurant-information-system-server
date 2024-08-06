package com.galaxy.restaurant.information.system.common;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    MANAGER, USER, ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
