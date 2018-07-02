package com.webdev.siteparser.servise.secority;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Service
public class SecurityGenerate {
    public ModelAndView modelGenerate(String uri){
        User user = getUser();

        ModelAndView modelAndView = new ModelAndView(uri);
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", getUserRoleAsString());

        return modelAndView;
    }

    public String getUserRoleAsString(){
        Collection<GrantedAuthority> authorities = getUser().getAuthorities();

        StringBuilder sb = new StringBuilder();
        for (GrantedAuthority ga:
             authorities) {
            if (sb.length() > 0){
                sb.append(" ");
            }
            sb.append(ga.getAuthority());
        }
        return sb.toString();
    }
    private User getUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
