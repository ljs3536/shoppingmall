package com.hertz.shoppingMall.config.security;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.model.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 2L;
    private final Long memberId;
    private final String username;
    private final String password;
    private final String nickname;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Member member) {
        this.memberId = member.getId();
        this.username = member.getLoginId();
        this.password = member.getPassword();
        this.nickname = member.getNickname();
        // Convert Enum role to GrantedAuthorities
        this.authorities = convertRoleToAuthorities(member.getRole());
    }

    // Method to convert Enum role to GrantedAuthorities
    private Collection<? extends GrantedAuthority> convertRoleToAuthorities(Role role) {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + role.name())
        );
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
