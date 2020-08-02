package com.sm.controller;

import com.sm.SalariesServiceApplication;
import com.sm.controller.config.TestUtil;
import com.sm.domain.SalarieDTO;
import com.sm.domain.SalarieRequestDTO;
import com.sm.repository.SalarieRepository;
import com.sm.service.SalarieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SalariesServiceApplication.class)
public class SalarieRessourceTest {

    private MockMvc restSalarieMockMvc;

    @Autowired
    private SalarieRepository salarieRepository;

    @Autowired
    private SalarieService salarieService;

    @Autowired
    private EntityManager em;

    @Qualifier("mappingJackson2HttpMessageConverter")
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
    private SalarieRequestDTO salarieRequestDTO;

    private List<SalarieDTO> getSalarieDTOList() {
        SalarieDTO salarieDTO = new SalarieDTO(1L, "11", "Mustapha", "consultant");
        SalarieDTO salarieDTO1 = new SalarieDTO(2L, "11", "François", "consultant");
        SalarieDTO salarieDTO2 = new SalarieDTO(3L, "12", "Elodie", "consultant");
        SalarieDTO salarieDTO3 = new SalarieDTO(4L, "13", "Elodie", "consultant");
        SalarieDTO salarieDTO4 = new SalarieDTO(4L, "14", "Celine", "consultant");

        return new ArrayList<>(asList(salarieDTO, salarieDTO1, salarieDTO2, salarieDTO3, salarieDTO4));
    }

    public SalarieRequestDTO createEntity(EntityManager em) {
        SalarieRequestDTO salarieRequestDTO = new SalarieRequestDTO();
        salarieRequestDTO.setCritere("position");
        salarieRequestDTO.setSalaries(getSalarieDTOList());
        return salarieRequestDTO;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SalariesResource salarieRessource = new SalariesResource(salarieService);
        this.restSalarieMockMvc = MockMvcBuilders.standaloneSetup(salarieRessource)
                .setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @Before
    public void initTest() {
        salarieRequestDTO = createEntity(em);
    }

    @Test
    public void createSalarie() throws Exception {
        int databaseSizeBeforeCreate = salarieRepository.findAll().size();
        restSalarieMockMvc.perform(post("/api/salaries/addSalarie")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(salarieRequestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getSalaries() throws Exception {
        salarieService.createSalarie(getSalarieDTOList());

        restSalarieMockMvc.perform(get("/api/salaries").param("critere", "position"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].salno").value("11"))
                .andExpect(jsonPath("$.[0].salname").value("Mustapha"))
                .andExpect(jsonPath("$.[0].position").value("consultant"));
    }

}
