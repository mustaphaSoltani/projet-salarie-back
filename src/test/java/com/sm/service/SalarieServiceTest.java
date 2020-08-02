package com.sm.service;

import com.sm.SalariesServiceApplication;
import com.sm.domain.SalarieDTO;
import com.sm.model.Salarie;
import com.sm.repository.SalarieRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SalariesServiceApplication.class)
class SalarieServiceTest {

    @Autowired
    private SalarieRepository salarieRepository;
    @Autowired
    private SalarieService salarieService;

    @Test
    void createSalarie() {
        List<SalarieDTO> salarieDTOList = getSalarieDTOList();
        List<Salarie> salarieDTOSaved = this.salarieService.createSalarie(salarieDTOList);
        assertThat(salarieDTOSaved).isNotNull();
        assertThat(salarieDTOSaved.size()).isEqualTo(5);
    }

    @Test
    void getSalarieByCriterion() {
        String critereNo = "salno";
        String critereName = "salname";
        String criterePosition = "position";
        salarieRepository.saveAll(getSalarieList());

        List<Salarie> salariesResultByNo = salarieService.getSalarieByCriterion(critereNo);
        List<Salarie> salariesResultByName = salarieService.getSalarieByCriterion(critereName);
        List<Salarie> salariesResultByPosition = salarieService.getSalarieByCriterion(criterePosition);
        assertThat(salariesResultByNo.size()).isEqualTo(2);
        assertThat(salariesResultByName.size()).isEqualTo(4);
        assertThat(salariesResultByPosition.size()).isEqualTo(1);
    }


    private List<SalarieDTO> getSalarieDTOList() {
        SalarieDTO salarieDTO = new SalarieDTO(1L, "11", "Mustapha", "consultant");
        SalarieDTO salarieDTO1 = new SalarieDTO(2L, "11", "François", "consultant");
        SalarieDTO salarieDTO2 = new SalarieDTO(3L, "12", "Elodie", "consultant");
        SalarieDTO salarieDTO3 = new SalarieDTO(4L, "13", "Elodie", "consultant");
        SalarieDTO salarieDTO4 = new SalarieDTO(4L, "14", "Celine", "consultant");

        return new ArrayList<>(asList(salarieDTO, salarieDTO1, salarieDTO2, salarieDTO3, salarieDTO4));
    }

    private List<Salarie> getSalarieList() {
        Salarie salarie = new Salarie(1L, "11", "Mustapha", "consultant");
        Salarie salarie1 = new Salarie(2L, "11", "François", "consultant");
        Salarie salarie2 = new Salarie(3L, "11", "Elodie", "consultant");
        Salarie salarie3 = new Salarie(4L, "11", "Elodie", "consultant");
        Salarie salarie4 = new Salarie(4L, "14", "Celine", "consultant");

        return new ArrayList<>(asList(salarie, salarie1, salarie2, salarie3, salarie4));
    }
}
