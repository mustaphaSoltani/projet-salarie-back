package com.sm.controller;

import com.sm.domain.SalarieDTO;
import com.sm.domain.SalarieRequestDTO;
import com.sm.model.Salarie;
import com.sm.service.SalarieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/salaries")
public class SalariesResource {

    private final SalarieService salarieService;

    public SalariesResource(SalarieService salarieService) {
        this.salarieService = salarieService;
    }

    @PostMapping(value = "/addSalarie")
    public ResponseEntity<?> createSalarie(@RequestBody SalarieRequestDTO salarieRequestDTO) throws Exception {
        try {
            List<SalarieDTO> salarieDTOList = getSalarieDTOS(salarieRequestDTO);
            List<Salarie> result = salarieService.createSalarie(salarieDTOList);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (
                Exception e) {
            e.printStackTrace();
            String errorMessage = "The salarie's code must be not null and unique !!!";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity<?> getSalaries(@RequestParam String critere) throws Exception {
        try {

            List<Salarie> result = salarieService.getSalarieByCriterion(critere);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "The salarie's code must be not null and unique !!!";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

    }

    private List<SalarieDTO> getSalarieDTOS(SalarieRequestDTO salarieRequestDTO) {
        String critere = salarieRequestDTO.getCritere();
        List<SalarieDTO> salarieDTOList = salarieRequestDTO.getSalaries();
        Set<String> set = new HashSet<>(salarieDTOList.size());
        switch (critere) {
            case "position":
                salarieDTOList.removeIf(p -> !set.add(p.getPosition()));
                break;
            case "salno":
                salarieDTOList.removeIf(p -> !set.add(p.getSalno()));
                break;
            case "salname":
                salarieDTOList.removeIf(p -> !set.add(p.getSalname()));
                break;
        }
        return salarieDTOList;
    }

}
