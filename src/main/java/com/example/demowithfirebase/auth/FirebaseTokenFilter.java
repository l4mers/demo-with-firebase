package com.example.demowithfirebase.auth;

import com.example.demowithfirebase.exception.NotAllowedException;
import com.example.demowithfirebase.model.CustomerExample;
import com.example.demowithfirebase.repository.CustomerExampleRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FirebaseTokenFilter extends OncePerRequestFilter {

    private final CustomerExampleRepository customerExampleRepository;
    //Extraherar och validerar token
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String idToken = authorizationHeader.substring(7);
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
                String email = decodedToken.getEmail();
                //eller om email är id
                //String email = decodedToken.getUid();

                //kolla om användaren finns annars kasta 403 global custom exception
                CustomerExample exampleCustomer = customerExampleRepository.getCustomerByEmail(email)
                        .orElseThrow(() -> new NotAllowedException("Not an allowed user. Contact your administrator"));

                // lista som man kan lägga till andra claims eller roller satta av firebase
                List<GrantedAuthority> authorities = new ArrayList<>();

                FirebaseAuthenticationToken authentication = new FirebaseAuthenticationToken(exampleCustomer, idToken, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
