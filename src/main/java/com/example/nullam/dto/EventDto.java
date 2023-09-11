package com.example.nullam.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class EventDto {
    private Long id;
    private String name;
    private LocalDateTime time;
    private String place;
    private String info;
}
