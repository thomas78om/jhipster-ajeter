package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.AjeterApp;
import io.github.jhipster.application.domain.RefActivite;
import io.github.jhipster.application.repository.RefActiviteRepository;
import io.github.jhipster.application.service.RefActiviteService;
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
 * Integration tests for the {@link RefActiviteResource} REST controller.
 */
@SpringBootTest(classes = AjeterApp.class)
public class RefActiviteResourceIT {

    private static final String DEFAULT_R_AC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_R_AC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_R_AC_LIB_COURT = "AAAAAAAAAA";
    private static final String UPDATED_R_AC_LIB_COURT = "BBBBBBBBBB";

    private static final String DEFAULT_R_AC_LIB_LONG = "AAAAAAAAAA";
    private static final String UPDATED_R_AC_LIB_LONG = "BBBBBBBBBB";

    private static final String DEFAULT_R_AC_COMM = "AAAAAAAAAA";
    private static final String UPDATED_R_AC_COMM = "BBBBBBBBBB";

    @Autowired
    private RefActiviteRepository refActiviteRepository;

    @Autowired
    private RefActiviteService refActiviteService;

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

    private MockMvc restRefActiviteMockMvc;

    private RefActivite refActivite;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RefActiviteResource refActiviteResource = new RefActiviteResource(refActiviteService);
        this.restRefActiviteMockMvc = MockMvcBuilders.standaloneSetup(refActiviteResource)
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
    public static RefActivite createEntity(EntityManager em) {
        RefActivite refActivite = new RefActivite()
            .rACCode(DEFAULT_R_AC_CODE)
            .rACLibCourt(DEFAULT_R_AC_LIB_COURT)
            .rACLibLong(DEFAULT_R_AC_LIB_LONG)
            .rACComm(DEFAULT_R_AC_COMM);
        return refActivite;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefActivite createUpdatedEntity(EntityManager em) {
        RefActivite refActivite = new RefActivite()
            .rACCode(UPDATED_R_AC_CODE)
            .rACLibCourt(UPDATED_R_AC_LIB_COURT)
            .rACLibLong(UPDATED_R_AC_LIB_LONG)
            .rACComm(UPDATED_R_AC_COMM);
        return refActivite;
    }

    @BeforeEach
    public void initTest() {
        refActivite = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefActivite() throws Exception {
        int databaseSizeBeforeCreate = refActiviteRepository.findAll().size();

        // Create the RefActivite
        restRefActiviteMockMvc.perform(post("/api/ref-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refActivite)))
            .andExpect(status().isCreated());

        // Validate the RefActivite in the database
        List<RefActivite> refActiviteList = refActiviteRepository.findAll();
        assertThat(refActiviteList).hasSize(databaseSizeBeforeCreate + 1);
        RefActivite testRefActivite = refActiviteList.get(refActiviteList.size() - 1);
        assertThat(testRefActivite.getrACCode()).isEqualTo(DEFAULT_R_AC_CODE);
        assertThat(testRefActivite.getrACLibCourt()).isEqualTo(DEFAULT_R_AC_LIB_COURT);
        assertThat(testRefActivite.getrACLibLong()).isEqualTo(DEFAULT_R_AC_LIB_LONG);
        assertThat(testRefActivite.getrACComm()).isEqualTo(DEFAULT_R_AC_COMM);
    }

    @Test
    @Transactional
    public void createRefActiviteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refActiviteRepository.findAll().size();

        // Create the RefActivite with an existing ID
        refActivite.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefActiviteMockMvc.perform(post("/api/ref-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refActivite)))
            .andExpect(status().isBadRequest());

        // Validate the RefActivite in the database
        List<RefActivite> refActiviteList = refActiviteRepository.findAll();
        assertThat(refActiviteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRefActivites() throws Exception {
        // Initialize the database
        refActiviteRepository.saveAndFlush(refActivite);

        // Get all the refActiviteList
        restRefActiviteMockMvc.perform(get("/api/ref-activites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refActivite.getId().intValue())))
            .andExpect(jsonPath("$.[*].rACCode").value(hasItem(DEFAULT_R_AC_CODE.toString())))
            .andExpect(jsonPath("$.[*].rACLibCourt").value(hasItem(DEFAULT_R_AC_LIB_COURT.toString())))
            .andExpect(jsonPath("$.[*].rACLibLong").value(hasItem(DEFAULT_R_AC_LIB_LONG.toString())))
            .andExpect(jsonPath("$.[*].rACComm").value(hasItem(DEFAULT_R_AC_COMM.toString())));
    }
    
    @Test
    @Transactional
    public void getRefActivite() throws Exception {
        // Initialize the database
        refActiviteRepository.saveAndFlush(refActivite);

        // Get the refActivite
        restRefActiviteMockMvc.perform(get("/api/ref-activites/{id}", refActivite.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refActivite.getId().intValue()))
            .andExpect(jsonPath("$.rACCode").value(DEFAULT_R_AC_CODE.toString()))
            .andExpect(jsonPath("$.rACLibCourt").value(DEFAULT_R_AC_LIB_COURT.toString()))
            .andExpect(jsonPath("$.rACLibLong").value(DEFAULT_R_AC_LIB_LONG.toString()))
            .andExpect(jsonPath("$.rACComm").value(DEFAULT_R_AC_COMM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefActivite() throws Exception {
        // Get the refActivite
        restRefActiviteMockMvc.perform(get("/api/ref-activites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefActivite() throws Exception {
        // Initialize the database
        refActiviteService.save(refActivite);

        int databaseSizeBeforeUpdate = refActiviteRepository.findAll().size();

        // Update the refActivite
        RefActivite updatedRefActivite = refActiviteRepository.findById(refActivite.getId()).get();
        // Disconnect from session so that the updates on updatedRefActivite are not directly saved in db
        em.detach(updatedRefActivite);
        updatedRefActivite
            .rACCode(UPDATED_R_AC_CODE)
            .rACLibCourt(UPDATED_R_AC_LIB_COURT)
            .rACLibLong(UPDATED_R_AC_LIB_LONG)
            .rACComm(UPDATED_R_AC_COMM);

        restRefActiviteMockMvc.perform(put("/api/ref-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefActivite)))
            .andExpect(status().isOk());

        // Validate the RefActivite in the database
        List<RefActivite> refActiviteList = refActiviteRepository.findAll();
        assertThat(refActiviteList).hasSize(databaseSizeBeforeUpdate);
        RefActivite testRefActivite = refActiviteList.get(refActiviteList.size() - 1);
        assertThat(testRefActivite.getrACCode()).isEqualTo(UPDATED_R_AC_CODE);
        assertThat(testRefActivite.getrACLibCourt()).isEqualTo(UPDATED_R_AC_LIB_COURT);
        assertThat(testRefActivite.getrACLibLong()).isEqualTo(UPDATED_R_AC_LIB_LONG);
        assertThat(testRefActivite.getrACComm()).isEqualTo(UPDATED_R_AC_COMM);
    }

    @Test
    @Transactional
    public void updateNonExistingRefActivite() throws Exception {
        int databaseSizeBeforeUpdate = refActiviteRepository.findAll().size();

        // Create the RefActivite

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRefActiviteMockMvc.perform(put("/api/ref-activites")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refActivite)))
            .andExpect(status().isBadRequest());

        // Validate the RefActivite in the database
        List<RefActivite> refActiviteList = refActiviteRepository.findAll();
        assertThat(refActiviteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRefActivite() throws Exception {
        // Initialize the database
        refActiviteService.save(refActivite);

        int databaseSizeBeforeDelete = refActiviteRepository.findAll().size();

        // Delete the refActivite
        restRefActiviteMockMvc.perform(delete("/api/ref-activites/{id}", refActivite.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RefActivite> refActiviteList = refActiviteRepository.findAll();
        assertThat(refActiviteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefActivite.class);
        RefActivite refActivite1 = new RefActivite();
        refActivite1.setId(1L);
        RefActivite refActivite2 = new RefActivite();
        refActivite2.setId(refActivite1.getId());
        assertThat(refActivite1).isEqualTo(refActivite2);
        refActivite2.setId(2L);
        assertThat(refActivite1).isNotEqualTo(refActivite2);
        refActivite1.setId(null);
        assertThat(refActivite1).isNotEqualTo(refActivite2);
    }
}
