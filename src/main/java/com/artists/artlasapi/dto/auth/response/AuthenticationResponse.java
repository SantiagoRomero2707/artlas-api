package com.artists.artlasapi.dto.auth.response;

import org.springframework.security.core.GrantedAuthority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private String firstname;
    private String lastname;
    private Collection<? extends GrantedAuthority> authorities;
}
