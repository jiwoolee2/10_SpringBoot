package com.example.demo.authority.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Value
@Getter
@Builder
@ToString
public class MyUserDetails implements UserDetails{

	private String username;
	private String password;
	private String MemberName;
	private String MemberPhone;
	private Collection<? extends GrantedAuthority> authorities;
	
}
