package com.schedease.sched_ease_backend_feedback.util;

package com.schedease.util;

import com.schedease.service.CustomUserDetailsService;
import com.schedease.sched_ease_backend_feedback.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, javax.servlet.http.HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String jwtToken = extractJwtFromRequest(request);
        if (jwtToken != null && jwtTokenUtil.validateToken(jwtToken)) {
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if (userDetails != null) {
                // Set the user in the security context
                SecurityContextHolder.getContext().setAuthentication(userDetails);
            }
        }
        chain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}

