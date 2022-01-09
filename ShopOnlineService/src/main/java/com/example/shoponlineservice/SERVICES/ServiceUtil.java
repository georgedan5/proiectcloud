package com.example.shoponlineservice.SERVICES;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ServiceUtil {

    public static String getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(noUserAuthenticated(authentication)) {
            throw new RuntimeException("No Authenticated User");
        }

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }

        throw new RuntimeException("Invalid User Type Exception");
    }

    private static boolean noUserAuthenticated(Authentication authentication) {
        return authentication == null || authentication.getPrincipal() == null;
    }
}
