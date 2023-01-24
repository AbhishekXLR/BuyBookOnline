package com.rest.books.bootrestbooks.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String requestToken = request.getHeader("Authorization");
        System.out.println(requestToken);

        String username = null;
        String token = null;

        if (requestToken != null && requestToken.startsWith("Bearer")) {

            token = requestToken.substring(7);

            try {
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get Jwt Token");
            } catch (ExpiredJwtException e) {
                System.out.println("Jwt token has been Expired");
            } catch (MalformedJwtException e) {
                System.out.println("invalid Jwt Token");
            }

        } else {
            System.out.println("Jwt token is not starting with Bearer");
        }

        // once we get the token, now Validate
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);


            if (this.jwtTokenHelper.validateToken(token, userDetails)) {

                // now Authenticate

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                request.setAttribute("loginUser",userDetails);  //**Adding this Extra Line, Here we are setting Attributes of user(logged-In) from UserDetails using Token of logged-In user.


                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            } else {
                System.out.println("Invalid Jwt Token!");
            }
        } else {
            System.out.println("Username is null or SecurityContextHolder has something in it");
        }

        filterChain.doFilter(request, response);
    }
}
