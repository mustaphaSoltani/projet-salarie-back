package com.sm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SalarieRequestDTO {
    private String critere;
    private List<SalarieDTO> salaries;

    public SalarieRequestDTO() {
    }

}
