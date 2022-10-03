package com.example.Spring_Security_Tutoiral.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.Spring_Security_Tutoiral.security.AppUserPermission.*;

public enum AppUserRole {
    student(Sets.newHashSet()),
    admin(Sets.newHashSet(course_write, course_read, student_read, student_write )),
    adminTrainee(Sets.newHashSet(course_read, student_read));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities()
    {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE" + this.name()));

         return permissions;
    }
}
