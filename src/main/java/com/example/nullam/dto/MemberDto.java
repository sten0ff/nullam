package com.example.nullam.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MemberDto {
    private String name;
    private String secondname;
    private String regcode;
    private String payment;
    private String info;;
}
