package com.adriankich.library.domain.model;

import com.adriankich.library.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "lib_authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private int birthYear;

    @Column(nullable = false, unique = true)
    private String cpf;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}
