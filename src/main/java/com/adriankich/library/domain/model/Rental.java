package com.adriankich.library.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "lib_rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate rentalDate;

    @Column(nullable = false)
    private LocalDate returnDate;

    private boolean returned;

    @ManyToMany(mappedBy = "rentals")
    private List<Book> books;

    @ManyToOne()
    @JoinColumn(name = "renter_id")
    private Renter renter;
}
