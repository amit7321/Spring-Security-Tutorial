package com.example.Spring_Security_Tutoiral.security;

public enum AppUserPermission {
    student_read("student:read"),
    student_write("student:write"),
    course_read("course:read"),
    course_write("course:write");

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
