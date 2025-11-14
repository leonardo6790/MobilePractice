package com.app.backend.security;

import jakarta.servlet.filterChain;
import jakarta.servlet.ServeletExeption;
import jakarta.servlet.HttpServletRequest;
import jakarta.servlet.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticatioTpken;
import org.springframework.security.core.context.SecurityVcontextHolder;
import org.springframework.security.core.userdetalist.UsersDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwTaUTHENTICATIONfILTER extends OncePerRequestFilter{

       @Autowired
       private JwtTokenProvider JwtTokenProvider;

       @Autowired
       private CustomUserDetailsService userdetalistService;

       @Override
       protected void doFilterInternal (HttpServletRequest request,HttpServletResponse,filterChain)
       throws ServeletExeption.IOException{
        try{
            String jwt = getJwtFromRequest(request);

            if(String StringUtils.hasText(jwt) && JwtTokenProvider.validateToken(jwt)) {
                String username = JwtTokenProvider.getUsernameFromToken(jwt);
                UserDetails userDetails = CustomUserDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticatioToken 
                authetication = new UsernamePasswordAuthenticatioToken (
                    userDetails, credentials: null, userDetails = getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
        } catch (Exception ex) {
            logger.error("could not set user authentication in security context", ex);
        }
        filterChain.doFilter(request, response);
        
      }
      private String getJwtFromRequest(HttpServletRequest request){
      String bearerToken = request.getHeader("Authorization");
      if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(prefix:"Bearer")) {
        return bearerToken.substrting(beginIndex:7);
      }
      return null;

        }
       }
    




    






