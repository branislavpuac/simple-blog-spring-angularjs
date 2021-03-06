package org.bp.lab.simpleblog.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/api/**").permitAll()
			.antMatchers(HttpMethod.PUT, "/api/comments/like/**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/comments").permitAll()
			.antMatchers(HttpMethod.POST, "/api/**").authenticated()
			.antMatchers(HttpMethod.PUT, "/api/**").authenticated()
			.antMatchers(HttpMethod.DELETE, "/api/**").authenticated()
			.antMatchers(HttpMethod.GET, "/api/users").authenticated()
			.anyRequest().permitAll().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
			.logout()
			.logoutUrl("/logout")
			.permitAll().and()
			.csrf().csrfTokenRepository(csrfTokenRepository()).and()
			.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
	}
	
	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter(){

			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
					FilterChain filterChain) throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
				if(csrf != null) {
					Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
					String token = csrf.getToken();
					if(cookie == null || token != null && !token.equals(cookie.getValue())){
						cookie = new Cookie("XSRF-TOKEN", token);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
				filterChain.doFilter(request, response);
			}
			
		};
	}
	
	private CsrfTokenRepository csrfTokenRepository(){
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}	
	
}
