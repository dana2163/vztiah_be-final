package com.institute.journal.domain.user;

public enum UserPermission {
    STUDENT_READ("student:read"), STUDENT_WRITE("student:write"), STUDENT_DELETE("student:delete"),
    GRADE_READ("grade:read"), GRADE_WRITE("grade:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
