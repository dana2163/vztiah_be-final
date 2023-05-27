package com.institute.journal.domain.user;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.institute.journal.domain.user.UserPermission.*;

public enum UserRole {
    TEACHER(Sets.newHashSet(
            STUDENT_READ, STUDENT_WRITE, STUDENT_DELETE,
            GRADE_READ, GRADE_WRITE
    )),

    PARENT(Sets.newHashSet(
            STUDENT_READ,
            GRADE_READ
    ));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + name()));
        return permissions;
    }
}
