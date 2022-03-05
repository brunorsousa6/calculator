package br.com.erudio.springbootaws.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="books")
public class Book {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="author", nullable = false, length = 100)
    private String author;

    @Column(name ="launch_date", nullable = false, length = 30)
    @Temporal(TemporalType.DATE)
    private Date launchDate;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, length = 250)
    private String title;
}
