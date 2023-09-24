package com.products.config;



import com.products.filter.JwtFilter;
import com.products.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${app.cors.allowed-origins}")
	private String[]  corsAllowedOrigins;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtFilter jwtFilter;

	private static final String[] AUTH_WHITELIST = {
			"/authenticate",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/v3/api-docs",
			"/webjars/**",
			"/health"
	};

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors().configurationSource(request -> {
					CorsConfiguration cors = new CorsConfiguration();
					cors.setAllowedOrigins(Arrays.asList(corsAllowedOrigins));
					cors.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
					cors.setAllowedHeaders(Arrays.asList("*"));
					cors.setAllowCredentials(true);
					cors.setExposedHeaders(Arrays.asList("Authorization"));

					return cors;
				}).and()
				.csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests()
		        .antMatchers(AUTH_WHITELIST).permitAll()
				.antMatchers("/authenticate").permitAll()
				.antMatchers("/signup").permitAll()
				// all other requests need to be authenticated
				.anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
				// make sure we use stateless session; session won't be used to
				// store user's state.
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}


}