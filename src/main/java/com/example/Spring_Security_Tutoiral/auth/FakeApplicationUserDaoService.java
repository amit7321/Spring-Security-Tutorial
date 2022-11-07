package com.example.Spring_Security_Tutoiral.auth;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static com.example.Spring_Security_Tutoiral.security.AppUserRole.student;

public class FakeApplicationUserDaoService implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;

    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return Optional.empty();
    }

    private List<ApplicationUser> getApplicationUsers()
    {
        List <ApplicationUser> applicationUsers = List.newArrayList(
                new ApplicationUser(
                        "annasmith",
                        passwordEncoder.encode("password"),
                        student.getGrantedAuthorities(),
                )
        )
    }
}
