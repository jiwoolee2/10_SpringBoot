package com.example.demo.authority.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MyUserDetails implements UserDetails{

	private String username;
	private String password;
	private String memberName;
	private String memberPhone;
	private String role;
	private Collection<? extends GrantedAuthority> authorities;
	
}
