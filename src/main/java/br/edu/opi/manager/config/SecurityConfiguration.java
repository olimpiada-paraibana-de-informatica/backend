package br.edu.opi.manager.config;

import br.edu.opi.manager.security.SecurityFilter;
import br.edu.opi.manager.security.SecurityUtils;
import br.edu.opi.manager.utils.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Security Configuration.
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"br.edu.opi.manager"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private SecurityFilter securityFilter;

	@Autowired
	public SecurityConfiguration(SecurityFilter securityFilter) {
		this.securityFilter = securityFilter;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/*/login").permitAll()
				.antMatchers(HttpMethod.GET, RestConstants.STATUS_URI).permitAll()
				.antMatchers(HttpMethod.GET, RestConstants.OPI_CATEGORY_URI).permitAll()
				.antMatchers(HttpMethod.GET, RestConstants.CITY_URI).permitAll()
				.antMatchers(HttpMethod.GET, RestConstants.STATE_URI).permitAll()
				.antMatchers(HttpMethod.POST, RestConstants.SCHOOL_URI).permitAll()
				.antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
				.antMatchers(HttpMethod.GET, "/configuration/ui/**").permitAll()
				.antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
				.antMatchers(HttpMethod.GET, "/configuration/exceptions/**").permitAll()
				.antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
				.antMatchers(HttpMethod.GET, "/swagger-ui.html/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
		// @formatter:on
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration cors = new CorsConfiguration();
		cors.setAllowedOrigins(SecurityUtils.ACCESS_CONTROL_ALLOW_ORIGIN_LIST);
		cors.setAllowedMethods(SecurityUtils.ACCESS_CONTROL_ALLOW_METHODS_LIST);
		cors.setExposedHeaders(SecurityUtils.ACCESS_CONTROL_EXPOSE_HEADERS_LIST);
		cors.setAllowedHeaders(SecurityUtils.ACCESS_CONTROL_ALLOW_HEADERS_LIST);
		source.registerCorsConfiguration("/**", cors);
		return source;
	}

}
