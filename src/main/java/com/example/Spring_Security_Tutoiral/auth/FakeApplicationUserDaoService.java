package com.example.Spring_Security_Tutoiral.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.Spring_Security_Tutoiral.security.AppUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers()
    {
        List <ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "annasmith",
                        passwordEncoder.encode("password"),
                        student.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),

                new ApplicationUser(
                        "linda",
                        passwordEncoder.encode("password"),
                        admin.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),

                new ApplicationUser(
                        "tom",
                        passwordEncoder.encode("password"),
                        adminTrainee.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }
}
