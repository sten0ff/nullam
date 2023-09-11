package com.example.nullam.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String regnumber;
    private String ammount;
    private String payment;
    private String info;
    @ManyToMany(mappedBy = "companies")
    @ToString.Exclude
    private List<Event> events;
}
