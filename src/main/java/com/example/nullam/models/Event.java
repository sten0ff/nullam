package com.example.nullam.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime time;
    private String place;
    private String info;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "member_id") }
    )
    @ToString.Exclude
    private List<Member> members;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "company_id") }
    )
    @ToString.Exclude
    private List<Company> companies;

}