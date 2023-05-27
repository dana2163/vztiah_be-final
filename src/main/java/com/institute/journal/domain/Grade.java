package com.institute.journal.domain;

import com.institute.journal.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne
    private User teacher;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Student> students = new ArrayList<>();

    public Grade(String name) {
        this.name = name;
    }
}
