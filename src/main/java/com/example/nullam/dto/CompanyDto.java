package com.example.nullam.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CompanyDto {
    private String name;
    private String regnumber;
    private String ammount;
    private String payment;
    private String info;
}
