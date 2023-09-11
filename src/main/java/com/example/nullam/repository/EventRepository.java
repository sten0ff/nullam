package com.example.nullam.repository;

import com.example.nullam.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByTimeBefore(LocalDateTime before);
    List<Event> findAllByTimeAfter(LocalDateTime after);
}