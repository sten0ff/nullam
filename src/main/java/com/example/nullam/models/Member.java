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
public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String secondname;
    private String regcode;
    private String payment;
    private String info;
    @ManyToMany(mappedBy = "members")
    @ToString.Exclude
    private List<Event> events;
}