package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.AjeterApp;
import io.github.jhipster.application.domain.RefActeGestion;
import io.github.jhipster.application.repository.RefActeGestionRepository;
import io.github.jhipster.application.service.RefActeGestionService;
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
 * Integration tests for the {@link RefActeGestionResource} REST controller.
 */
@SpringBootTest(classes = AjeterApp.class)
public class RefActeGestionResourceIT {

    private static final String DEFAULT_R_AG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_R_AG_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_R_AG_LIB_COURT = "AAAAAAAAAA";
    private static final String UPDATED_R_AG_LIB_COURT = "BBBBBBBBBB";

    private static final String DEFAULT_R_AG_LIB_LONG = "AAAAAAAAAA";
    private static final String UPDATED_R_AG_LIB_LONG = "BBBBBBBBBB";

    private static final String DEFAULT_R_AG_COMM = "AAAAAAAAAA";
    private static final String UPDATED_R_AG_COMM = "BBBBBBBBBB";

    @Autowired
    private RefActeGestionRepository refActeGestionRepository;

    @Autowired
    private RefActeGestionService refActeGestionService;

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

    private MockMvc restRefActeGestionMockMvc;

    private RefActeGestion refActeGestion;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefActeGestionResource refActeGestionResource = new RefActeGestionResource(refActeGestionService);
        this.restRefActeGestionMockMvc = MockMvcBuilders.standaloneSetup(refActeGestionResource)
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
    public static RefActeGestion createEntity(EntityManager em) {
        RefActeGestion refActeGestion = new RefActeGestion()
            .rAGCode(DEFAULT_R_AG_CODE)
            .rAGLibCourt(DEFAULT_R_AG_LIB_COURT)
            .rAGLibLong(DEFAULT_R_AG_LIB_LONG)
            .rAGComm(DEFAULT_R_AG_COMM);
        return refActeGestion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefActeGestion createUpdatedEntity(EntityManager em) {
        RefActeGestion refActeGestion = new RefActeGestion()
            .rAGCode(UPDATED_R_AG_CODE)
            .rAGLibCourt(UPDATED_R_AG_LIB_COURT)
            .rAGLibLong(UPDATED_R_AG_LIB_LONG)
            .rAGComm(UPDATED_R_AG_COMM);
        return refActeGestion;
    }

    @BeforeEach
    public void initTest() {
        refActeGestion = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefActeGestion() throws Exception {
        int databaseSizeBeforeCreate = refActeGestionRepository.findAll().size();

        // Create the RefActeGestion
        restRefActeGestionMockMvc.perform(post("/api/ref-acte-gestions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refActeGestion)))
            .andExpect(status().isCreated());

        // Validate the RefActeGestion in the database
        List<RefActeGestion> refActeGestionList = refActeGestionRepository.findAll();
        assertThat(refActeGestionList).hasSize(databaseSizeBeforeCreate + 1);
        RefActeGestion testRefActeGestion = refActeGestionList.get(refActeGestionList.size() - 1);
        assertThat(testRefActeGestion.getrAGCode()).isEqualTo(DEFAULT_R_AG_CODE);
        assertThat(testRefActeGestion.getrAGLibCourt()).isEqualTo(DEFAULT_R_AG_LIB_COURT);
        assertThat(testRefActeGestion.getrAGLibLong()).isEqualTo(DEFAULT_R_AG_LIB_LONG);
        assertThat(testRefActeGestion.getrAGComm()).isEqualTo(DEFAULT_R_AG_COMM);
    }

    @Test
    @Transactional
    public void createRefActeGestionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refActeGestionRepository.findAll().size();

        // Create the RefActeGestion with an existing ID
        refActeGestion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefActeGestionMockMvc.perform(post("/api/ref-acte-gestions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refActeGestion)))
            .andExpect(status().isBadRequest());

        // Validate the RefActeGestion in the database
        List<RefActeGestion> refActeGestionList = refActeGestionRepository.findAll();
        assertThat(refActeGestionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRefActeGestions() throws Exception {
        // Initialize the database
        refActeGestionRepository.saveAndFlush(refActeGestion);

        // Get all the refActeGestionList
        restRefActeGestionMockMvc.perform(get("/api/ref-acte-gestions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refActeGestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].rAGCode").value(hasItem(DEFAULT_R_AG_CODE.toString())))
            .andExpect(jsonPath("$.[*].rAGLibCourt").value(hasItem(DEFAULT_R_AG_LIB_COURT.toString())))
            .andExpect(jsonPath("$.[*].rAGLibLong").value(hasItem(DEFAULT_R_AG_LIB_LONG.toString())))
            .andExpect(jsonPath("$.[*].rAGComm").value(hasItem(DEFAULT_R_AG_COMM.toString())));
    }
    
    @Test
    @Transactional
    public void getRefActeGestion() throws Exception {
        // Initialize the database
        refActeGestionRepository.saveAndFlush(refActeGestion);

        // Get the refActeGestion
        restRefActeGestionMockMvc.perform(get("/api/ref-acte-gestions/{id}", refActeGestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refActeGestion.getId().intValue()))
            .andExpect(jsonPath("$.rAGCode").value(DEFAULT_R_AG_CODE.toString()))
            .andExpect(jsonPath("$.rAGLibCourt").value(DEFAULT_R_AG_LIB_COURT.toString()))
            .andExpect(jsonPath("$.rAGLibLong").value(DEFAULT_R_AG_LIB_LONG.toString()))
            .andExpect(jsonPath("$.rAGComm").value(DEFAULT_R_AG_COMM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefActeGestion() throws Exception {
        // Get the refActeGestion
        restRefActeGestionMockMvc.perform(get("/api/ref-acte-gestions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefActeGestion() throws Exception {
        // Initialize the database
        refActeGestionService.save(refActeGestion);

        int databaseSizeBeforeUpdate = refActeGestionRepository.findAll().size();

        // Update the refActeGestion
        RefActeGestion updatedRefActeGestion = refActeGestionRepository.findById(refActeGestion.getId()).get();
        // Disconnect from session so that the updates on updatedRefActeGestion are not directly saved in db
        em.detach(updatedRefActeGestion);
        updatedRefActeGestion
            .rAGCode(UPDATED_R_AG_CODE)
            .rAGLibCourt(UPDATED_R_AG_LIB_COURT)
            .rAGLibLong(UPDATED_R_AG_LIB_LONG)
            .rAGComm(UPDATED_R_AG_COMM);

        restRefActeGestionMockMvc.perform(put("/api/ref-acte-gestions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefActeGestion)))
            .andExpect(status().isOk());

        // Validate the RefActeGestion in the database
        List<RefActeGestion> refActeGestionList = refActeGestionRepository.findAll();
        assertThat(refActeGestionList).hasSize(databaseSizeBeforeUpdate);
        RefActeGestion testRefActeGestion = refActeGestionList.get(refActeGestionList.size() - 1);
        assertThat(testRefActeGestion.getrAGCode()).isEqualTo(UPDATED_R_AG_CODE);
        assertThat(testRefActeGestion.getrAGLibCourt()).isEqualTo(UPDATED_R_AG_LIB_COURT);
        assertThat(testRefActeGestion.getrAGLibLong()).isEqualTo(UPDATED_R_AG_LIB_LONG);
        assertThat(testRefActeGestion.getrAGComm()).isEqualTo(UPDATED_R_AG_COMM);
    }

    @Test
    @Transactional
    public void updateNonExistingRefActeGestion() throws Exception {
        int databaseSizeBeforeUpdate = refActeGestionRepository.findAll().size();

        // Create the RefActeGestion

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefActeGestionMockMvc.perform(put("/api/ref-acte-gestions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refActeGestion)))
            .andExpect(status().isBadRequest());

        // Validate the RefActeGestion in the database
        List<RefActeGestion> refActeGestionList = refActeGestionRepository.findAll();
        assertThat(refActeGestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefActeGestion() throws Exception {
        // Initialize the database
        refActeGestionService.save(refActeGestion);

        int databaseSizeBeforeDelete = refActeGestionRepository.findAll().size();

        // Delete the refActeGestion
        restRefActeGestionMockMvc.perform(delete("/api/ref-acte-gestions/{id}", refActeGestion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RefActeGestion> refActeGestionList = refActeGestionRepository.findAll();
        assertThat(refActeGestionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefActeGestion.class);
        RefActeGestion refActeGestion1 = new RefActeGestion();
        refActeGestion1.setId(1L);
        RefActeGestion refActeGestion2 = new RefActeGestion();
        refActeGestion2.setId(refActeGestion1.getId());
        assertThat(refActeGestion1).isEqualTo(refActeGestion2);
        refActeGestion2.setId(2L);
        assertThat(refActeGestion1).isNotEqualTo(refActeGestion2);
        refActeGestion1.setId(null);
        assertThat(refActeGestion1).isNotEqualTo(refActeGestion2);
    }
}
