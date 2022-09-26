package com.esmile.appEsmile.login;

import com.esmile.appEsmile.service.impl.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfiguration(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers(HttpMethod.GET, "/paciente/**").hasAnyRole("PATIENT", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/paciente/**").hasAnyRole("PATIENT", "ADMIN")
                .antMatchers(HttpMethod.POST, "/paciente/**").hasAnyRole("PATIENT", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/paciente/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/dentista/**").hasAnyRole("DENTIST", "ADMIN")
                .antMatchers(HttpMethod.POST, "/dentista/**").hasAnyRole("DENTIST", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/dentista/**").hasAnyRole("DENTIST", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/dentista/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}