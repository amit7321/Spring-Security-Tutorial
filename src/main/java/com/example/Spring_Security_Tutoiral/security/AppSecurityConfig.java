package com.example.Spring_Security_Tutoiral.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.Spring_Security_Tutoiral.security.AppUserRole.admin;
import static com.example.Spring_Security_Tutoiral.security.AppUserRole.student;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .antMatchers("/api/**")
                .hasRole(student.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService()
    {
        UserDetails userStudent = User.builder()
                .username("amit")
                .password(passwordEncoder.encode("123"))
                .roles(student.name())
                .build();

        UserDetails userAdmin = User.builder()
                .username("smith")
                .password(passwordEncoder.encode("123"))
                .roles(admin.name())
                .build();

        UserDetails adminTrainee = User.builder()
                .username("akash")
                .password(passwordEncoder.encode("123"))
                .roles(AppUserRole.adminTrainee.name())
                .build();

        return new InMemoryUserDetailsManager(userStudent, userAdmin, adminTrainee);
    }

}
