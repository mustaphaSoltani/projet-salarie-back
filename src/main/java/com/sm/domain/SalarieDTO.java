package com.sm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SalarieDTO {

    private Long id;
    private String salno;
    private String salname;
    private String position;

    public SalarieDTO() {
    }
}
