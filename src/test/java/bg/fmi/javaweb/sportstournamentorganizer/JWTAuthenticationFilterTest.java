package bg.fmi.javaweb.sportstournamentorganizer;

import bg.fmi.javaweb.sportstournamentorganizer.security.CustomUserDetailsService;
import bg.fmi.javaweb.sportstournamentorganizer.security.JWTAuthenticationFilter;
import bg.fmi.javaweb.sportstournamentorganizer.security.JWTGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class JWTAuthenticationFilterTest {

    @InjectMocks
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private JWTGenerator jwtGenerator;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoFilterInternal_ValidToken_SetsAuthentication() throws Exception {
        String token = "valid.jwt.token";
        String username = "testuser";
        UserDetails userDetails = new User(username, "password", Collections.emptyList());

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtGenerator.validateToken(token)).thenReturn(true);
        when(jwtGenerator.getUsernameFromJwt(token)).thenReturn(username);
        when(customUserDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertNotNull(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        assertEquals(username, ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
    }

    @Test
    void testDoFilterInternal_InvalidToken_DoesNotSetAuthentication() throws Exception {
        // Setup
        String token = "invalid.jwt.token";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtGenerator.validateToken(token)).thenReturn(false);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        // Perform the filter operation
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assertions
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}
