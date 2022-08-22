package com.example.Spring_Security_Tutoiral.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.Spring_Security_Tutoiral.security.AppUserPermission.*;

public enum AppUserRole {
    student(Sets.newHashSet()),
    admin(Sets.newHashSet(course_write, course_read, student_read, student_write));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }
}
