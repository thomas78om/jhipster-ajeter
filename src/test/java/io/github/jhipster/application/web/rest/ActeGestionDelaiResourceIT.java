package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.AjeterApp;
import io.github.jhipster.application.domain.ActeGestionDelai;
import io.github.jhipster.application.repository.ActeGestionDelaiRepository;
import io.github.jhipster.application.service.ActeGestionDelaiService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ActeGestionDelaiResource} REST controller.
 */
@SpringBootTest(classes = AjeterApp.class)
public class ActeGestionDelaiResourceIT {

    private static final String DEFAULT_R_AC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_R_AC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_R_AG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_R_AG_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_R_GA_CODE = "AAAAAAAAAA";
    private static final String UPDATED_R_GA_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_A_GD_DELAI = 1L;
    private static final Long UPDATED_A_GD_DELAI = 2L;
    private static final Long SMALLER_A_GD_DELAI = 1L - 1L;

    private static final Long DEFAULT_A_GD_ATTENTE = 1L;
    private static final Long UPDATED_A_GD_ATTENTE = 2L;
    private static final Long SMALLER_A_GD_ATTENTE = 1L - 1L;

    private static final String DEFAULT_A_GD_COMM = "AAAAAAAAAA";
    private static final String UPDATED_A_GD_COMM = "BBBBBBBBBB";

    @Autowired
    private ActeGestionDelaiRepository acteGestionDelaiRepository;

    @Autowired
    private ActeGestionDelaiService acteGestionDelaiService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restActeGestionDelaiMockMvc;

    private ActeGestionDelai acteGestionDelai;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActeGestionDelaiResource acteGestionDelaiResource = new ActeGestionDelaiResource(acteGestionDelaiService);
        this.restActeGestionDelaiMockMvc = MockMvcBuilders.standaloneSetup(acteGestionDelaiResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ActeGestionDelai createEntity(EntityManager em) {
        ActeGestionDelai acteGestionDelai = new ActeGestionDelai()
            .rACCode(DEFAULT_R_AC_CODE)
            .rAGCode(DEFAULT_R_AG_CODE)
            .rGACode(DEFAULT_R_GA_CODE)
            .aGDDelai(DEFAULT_A_GD_DELAI)
            .aGDAttente(DEFAULT_A_GD_ATTENTE)
            .aGDComm(DEFAULT_A_GD_COMM);
        return acteGestionDelai;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ActeGestionDelai createUpdatedEntity(EntityManager em) {
        ActeGestionDelai acteGestionDelai = new ActeGestionDelai()
            .rACCode(UPDATED_R_AC_CODE)
            .rAGCode(UPDATED_R_AG_CODE)
            .rGACode(UPDATED_R_GA_CODE)
            .aGDDelai(UPDATED_A_GD_DELAI)
            .aGDAttente(UPDATED_A_GD_ATTENTE)
            .aGDComm(UPDATED_A_GD_COMM);
        return acteGestionDelai;
    }

    @BeforeEach
    public void initTest() {
        acteGestionDelai = createEntity(em);
    }

    @Test
    @Transactional
    public void createActeGestionDelai() throws Exception {
        int databaseSizeBeforeCreate = acteGestionDelaiRepository.findAll().size();

        // Create the ActeGestionDelai
        restActeGestionDelaiMockMvc.perform(post("/api/acte-gestion-delais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acteGestionDelai)))
            .andExpect(status().isCreated());

        // Validate the ActeGestionDelai in the database
        List<ActeGestionDelai> acteGestionDelaiList = acteGestionDelaiRepository.findAll();
        assertThat(acteGestionDelaiList).hasSize(databaseSizeBeforeCreate + 1);
        ActeGestionDelai testActeGestionDelai = acteGestionDelaiList.get(acteGestionDelaiList.size() - 1);
        assertThat(testActeGestionDelai.getrACCode()).isEqualTo(DEFAULT_R_AC_CODE);
        assertThat(testActeGestionDelai.getrAGCode()).isEqualTo(DEFAULT_R_AG_CODE);
        assertThat(testActeGestionDelai.getrGACode()).isEqualTo(DEFAULT_R_GA_CODE);
        assertThat(testActeGestionDelai.getaGDDelai()).isEqualTo(DEFAULT_A_GD_DELAI);
        assertThat(testActeGestionDelai.getaGDAttente()).isEqualTo(DEFAULT_A_GD_ATTENTE);
        assertThat(testActeGestionDelai.getaGDComm()).isEqualTo(DEFAULT_A_GD_COMM);
    }

    @Test
    @Transactional
    public void createActeGestionDelaiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = acteGestionDelaiRepository.findAll().size();

        // Create the ActeGestionDelai with an existing ID
        acteGestionDelai.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActeGestionDelaiMockMvc.perform(post("/api/acte-gestion-delais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acteGestionDelai)))
            .andExpect(status().isBadRequest());

        // Validate the ActeGestionDelai in the database
        List<ActeGestionDelai> acteGestionDelaiList = acteGestionDelaiRepository.findAll();
        assertThat(acteGestionDelaiList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllActeGestionDelais() throws Exception {
        // Initialize the database
        acteGestionDelaiRepository.saveAndFlush(acteGestionDelai);

        // Get all the acteGestionDelaiList
        restActeGestionDelaiMockMvc.perform(get("/api/acte-gestion-delais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(acteGestionDelai.getId().intValue())))
            .andExpect(jsonPath("$.[*].rACCode").value(hasItem(DEFAULT_R_AC_CODE.toString())))
            .andExpect(jsonPath("$.[*].rAGCode").value(hasItem(DEFAULT_R_AG_CODE.toString())))
            .andExpect(jsonPath("$.[*].rGACode").value(hasItem(DEFAULT_R_GA_CODE.toString())))
            .andExpect(jsonPath("$.[*].aGDDelai").value(hasItem(DEFAULT_A_GD_DELAI.intValue())))
            .andExpect(jsonPath("$.[*].aGDAttente").value(hasItem(DEFAULT_A_GD_ATTENTE.intValue())))
            .andExpect(jsonPath("$.[*].aGDComm").value(hasItem(DEFAULT_A_GD_COMM.toString())));
    }
    
    @Test
    @Transactional
    public void getActeGestionDelai() throws Exception {
        // Initialize the database
        acteGestionDelaiRepository.saveAndFlush(acteGestionDelai);

        // Get the acteGestionDelai
        restActeGestionDelaiMockMvc.perform(get("/api/acte-gestion-delais/{id}", acteGestionDelai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(acteGestionDelai.getId().intValue()))
            .andExpect(jsonPath("$.rACCode").value(DEFAULT_R_AC_CODE.toString()))
            .andExpect(jsonPath("$.rAGCode").value(DEFAULT_R_AG_CODE.toString()))
            .andExpect(jsonPath("$.rGACode").value(DEFAULT_R_GA_CODE.toString()))
            .andExpect(jsonPath("$.aGDDelai").value(DEFAULT_A_GD_DELAI.intValue()))
            .andExpect(jsonPath("$.aGDAttente").value(DEFAULT_A_GD_ATTENTE.intValue()))
            .andExpect(jsonPath("$.aGDComm").value(DEFAULT_A_GD_COMM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingActeGestionDelai() throws Exception {
        // Get the acteGestionDelai
        restActeGestionDelaiMockMvc.perform(get("/api/acte-gestion-delais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActeGestionDelai() throws Exception {
        // Initialize the database
        acteGestionDelaiService.save(acteGestionDelai);

        int databaseSizeBeforeUpdate = acteGestionDelaiRepository.findAll().size();

        // Update the acteGestionDelai
        ActeGestionDelai updatedActeGestionDelai = acteGestionDelaiRepository.findById(acteGestionDelai.getId()).get();
        // Disconnect from session so that the updates on updatedActeGestionDelai are not directly saved in db
        em.detach(updatedActeGestionDelai);
        updatedActeGestionDelai
            .rACCode(UPDATED_R_AC_CODE)
            .rAGCode(UPDATED_R_AG_CODE)
            .rGACode(UPDATED_R_GA_CODE)
            .aGDDelai(UPDATED_A_GD_DELAI)
            .aGDAttente(UPDATED_A_GD_ATTENTE)
            .aGDComm(UPDATED_A_GD_COMM);

        restActeGestionDelaiMockMvc.perform(put("/api/acte-gestion-delais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedActeGestionDelai)))
            .andExpect(status().isOk());

        // Validate the ActeGestionDelai in the database
        List<ActeGestionDelai> acteGestionDelaiList = acteGestionDelaiRepository.findAll();
        assertThat(acteGestionDelaiList).hasSize(databaseSizeBeforeUpdate);
        ActeGestionDelai testActeGestionDelai = acteGestionDelaiList.get(acteGestionDelaiList.size() - 1);
        assertThat(testActeGestionDelai.getrACCode()).isEqualTo(UPDATED_R_AC_CODE);
        assertThat(testActeGestionDelai.getrAGCode()).isEqualTo(UPDATED_R_AG_CODE);
        assertThat(testActeGestionDelai.getrGACode()).isEqualTo(UPDATED_R_GA_CODE);
        assertThat(testActeGestionDelai.getaGDDelai()).isEqualTo(UPDATED_A_GD_DELAI);
        assertThat(testActeGestionDelai.getaGDAttente()).isEqualTo(UPDATED_A_GD_ATTENTE);
        assertThat(testActeGestionDelai.getaGDComm()).isEqualTo(UPDATED_A_GD_COMM);
    }

    @Test
    @Transactional
    public void updateNonExistingActeGestionDelai() throws Exception {
        int databaseSizeBeforeUpdate = acteGestionDelaiRepository.findAll().size();

        // Create the ActeGestionDelai

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActeGestionDelaiMockMvc.perform(put("/api/acte-gestion-delais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(acteGestionDelai)))
            .andExpect(status().isBadRequest());

        // Validate the ActeGestionDelai in the database
        List<ActeGestionDelai> acteGestionDelaiList = acteGestionDelaiRepository.findAll();
        assertThat(acteGestionDelaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteActeGestionDelai() throws Exception {
        // Initialize the database
        acteGestionDelaiService.save(acteGestionDelai);

        int databaseSizeBeforeDelete = acteGestionDelaiRepository.findAll().size();

        // Delete the acteGestionDelai
        restActeGestionDelaiMockMvc.perform(delete("/api/acte-gestion-delais/{id}", acteGestionDelai.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ActeGestionDelai> acteGestionDelaiList = acteGestionDelaiRepository.findAll();
        assertThat(acteGestionDelaiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActeGestionDelai.class);
        ActeGestionDelai acteGestionDelai1 = new ActeGestionDelai();
        acteGestionDelai1.setId(1L);
        ActeGestionDelai acteGestionDelai2 = new ActeGestionDelai();
        acteGestionDelai2.setId(acteGestionDelai1.getId());
        assertThat(acteGestionDelai1).isEqualTo(acteGestionDelai2);
        acteGestionDelai2.setId(2L);
        assertThat(acteGestionDelai1).isNotEqualTo(acteGestionDelai2);
        acteGestionDelai1.setId(null);
        assertThat(acteGestionDelai1).isNotEqualTo(acteGestionDelai2);
    }
}
