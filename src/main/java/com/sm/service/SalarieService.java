package com.sm.service;

import com.sm.domain.SalarieDTO;
import com.sm.model.Salarie;
import com.sm.repository.SalarieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SalarieService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final SalarieRepository salarieRepository;

    public SalarieService(SalarieRepository salarieRepository) {
        this.salarieRepository = salarieRepository;
    }

    public List<Salarie> createSalarie(List<SalarieDTO> salarieDTOList) {
        List<Salarie> salaries = new ArrayList<>();
        salarieDTOList.forEach(salarieDTO -> {
            Salarie salarie = modelMapper.map(salarieDTO, Salarie.class);
            salarieRepository.save(salarie);
            salaries.add(salarie);
        });
        return salaries;
    }

    public List<Salarie> getSalarieByCriterion(String critere) {
        return getSalariesByCritere(critere);
    }

    private List<Salarie> getSalariesByCritere(String critere) {
        List<Salarie> salaries = salarieRepository.findAll();
        Set<String> set = new HashSet<>(salaries.size());
        switch (critere) {
            case "position":
                salaries.removeIf(p -> !set.add(p.getPosition()));
                break;
            case "salno":
                salaries.removeIf(p -> !set.add(p.getSalno()));
                break;
            case "salname":
                salaries.removeIf(p -> !set.add(p.getSalname()));
                break;
        }
        return salaries;
    }
}
