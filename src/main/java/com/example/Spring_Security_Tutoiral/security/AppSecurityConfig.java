package com.example.Spring_Security_Tutoiral.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.Spring_Security_Tutoiral.security.AppUserPermission.course_write;
import static com.example.Spring_Security_Tutoiral.security.AppUserRole.*;

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
                .antMatchers("/api/**").hasRole(student.name())
                .antMatchers(HttpMethod.DELETE,"/management/api/*").hasAuthority(course_write.name())
                .antMatchers(HttpMethod.POST,"/management/api/*").hasAuthority(course_write.name())
                .antMatchers(HttpMethod.PUT,"/management/api/*").hasAuthority(course_write.name())
                .antMatchers(HttpMethod.GET,"/management/api/*").hasAnyRole(admin.name(), adminTrainee.name())
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
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
                /*.roles(student.name())*/
                .authorities(student.getGrantedAuthorities())
                .build();

        UserDetails userAdmin = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
                //.roles(admin.name())
                .authorities(admin.getGrantedAuthorities())
                .build();

        UserDetails adminTrainee = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("123"))
                //.roles(AppUserRole.adminTrainee.name())
                .authorities(AppUserRole.adminTrainee.getGrantedAuthorities())
                .build();

         return new InMemoryUserDetailsManager(userStudent, userAdmin, adminTrainee);
    }

}
