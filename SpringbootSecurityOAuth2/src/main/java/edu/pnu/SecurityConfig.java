package edu.pnu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//이 클래스가 설정 클래스라고 정의(IoC 컨테이너에 로드)
@EnableWebSecurity
//스프링 시큐리티 적용에 필요한 필터 객체들 자동 생성
public class SecurityConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	//이 메서드가 리턴하는 객체를 IoC 컨테이너에 등록하라는 지시
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(security->security
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		//여기까지 접근권한 설정
		http.csrf(cf->cf.disable());
		//CSRF 보호 비활성화(사이트간 요청 위조)
		http.headers(hr->hr.frameOptions(fo->fo.disable()));
		
		http.formLogin(form->
						form.loginPage("/login")
							.defaultSuccessUrl("/loginSuccess", true)
							// /member를 호출해서 로그인 화면으로 왔을 때 로그인에 성공한 뒤 /loginSuccess로 이동하겠다는 의미
							//그렇지 않고 로그인에 성공한 뒤 호출한 url인 /member
					);
		//		http.formLogin(form->{}); : SpringBoot가 제공해주는 로그인을 사용하겠다는 false로 설정하면 됨
		
		http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		
		http.logout(logout->logout
				.invalidateHttpSession(true)
				//현재 브라우저와 연결된 세션 강제 종료
				.deleteCookies("JSESSIONID")
				//세션 아이디가 저장된 쿠키 삭제
				.logoutSuccessUrl("/login"));
				//로그아웃 후 이동할 URL 지정
		http.oauth2Login(oauth2->oauth2
				.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess",true));
		
		return http.build();
	}
	
//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("manager")
//		.password("{noop}abcd") //{noop}:No OPeration 비밀번호가 암호화되어있지 않다는 의미
//		.roles("MANAGER");
//		auth.inMemoryAuthentication()
//		.withUser("admin")
//		.password("{noop}abcd")
//		.roles("ADMIN");
//	}

}
