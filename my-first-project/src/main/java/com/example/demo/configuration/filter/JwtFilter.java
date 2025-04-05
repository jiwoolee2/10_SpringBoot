package com.example.demo.configuration.filter;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.authority.model.vo.MyUserDetails;
import com.example.demo.token.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{
	// `OncePerRequestFilter`: 요청 1건당 한 번만 동작하는 필터. 모든 요청에 대해 실행됨.

	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;
	// 토큰이 유효한지 검증
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		log.info("authorization : {}",authorization);
		
		if(authorization == null || !authorization.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		

        String token = authorization.substring(7); // "Bearer " 제거

        try {
            Claims claims = jwtUtil.parseJwt(token); // 유효한 토큰인지 확인
            String username = claims.getSubject();   // 토큰 안에 있는 사용자 이름 추출

            MyUserDetails user = (MyUserDetails) userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authentication
			= new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities()); // 마지막에 role
			
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			// 세부설정 사용자의 IP주소, MAC주소, SessionID 등등이 포함될 수 있음
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			// session.setAttribute("loginMember",사용자정보); 이 과정과 같은 느낌
			
		} catch(ExpiredJwtException e) {
			log.info("만료됨");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("만료된 토큰입니다");
			return;
		} catch(JwtException e) {
			log.info("유효하지 않은 토큰값");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("유효하지 않은 토큰입니다.");
			return;
		}

        // 다음 필터로 계속 진행
        filterChain.doFilter(request, response);
    }
		
}

		
		





