package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	
	}
	
	@Bean 
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(cf->cf.disable());
		
		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
		
		http.formLogin(frmLogin->frmLogin.disable()); //Form 을 이용한 로그인을 사용하지 않겠다
		http.httpBasic(basic->basic.disable()); //http basic 인증방식을 사용하지 않겠다
		//세션 유지 안 하겠다 -> url 호출 뒤 응답할 때까진 유지되지만 응답 후엔 삭제됨.
		http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilter(new JWTAuthenticationFilter
				(authenticationConfiguration.getAuthenticationManager(), memberRepository));
		
		//스프링 시큐리티가 등록한 필터들 중 AuthorizationFilter 앞에서 작성한 필터 삽입
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), AuthorizationFilter.class);
		
		return http.build();
	}
}
