package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.AjeterApp;
import io.github.jhipster.application.domain.ParamExport;
import io.github.jhipster.application.repository.ParamExportRepository;
import io.github.jhipster.application.service.ParamExportService;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ParamExportResource} REST controller.
 */
@SpringBootTest(classes = AjeterApp.class)
public class ParamExportResourceIT {

    private static final Integer DEFAULT_P_EX_PUBLISH = 1;
    private static final Integer UPDATED_P_EX_PUBLISH = 2;
    private static final Integer SMALLER_P_EX_PUBLISH = 1 - 1;

    private static final LocalDate DEFAULT_P_EX_DTLASTEXPORT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_P_EX_DTLASTEXPORT = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_P_EX_DTLASTEXPORT = LocalDate.ofEpochDay(-1L);

    @Autowired
    private ParamExportRepository paramExportRepository;

    @Autowired
    private ParamExportService paramExportService;

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

    private MockMvc restParamExportMockMvc;

    private ParamExport paramExport;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ParamExportResource paramExportResource = new ParamExportResource(paramExportService);
        this.restParamExportMockMvc = MockMvcBuilders.standaloneSetup(paramExportResource)
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
    public static ParamExport createEntity(EntityManager em) {
        ParamExport paramExport = new ParamExport()
            .pEXPublish(DEFAULT_P_EX_PUBLISH)
            .pEXDtlastexport(DEFAULT_P_EX_DTLASTEXPORT);
        return paramExport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParamExport createUpdatedEntity(EntityManager em) {
        ParamExport paramExport = new ParamExport()
            .pEXPublish(UPDATED_P_EX_PUBLISH)
            .pEXDtlastexport(UPDATED_P_EX_DTLASTEXPORT);
        return paramExport;
    }

    @BeforeEach
    public void initTest() {
        paramExport = createEntity(em);
    }

    @Test
    @Transactional
    public void createParamExport() throws Exception {
        int databaseSizeBeforeCreate = paramExportRepository.findAll().size();

        // Create the ParamExport
        restParamExportMockMvc.perform(post("/api/param-exports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paramExport)))
            .andExpect(status().isCreated());

        // Validate the ParamExport in the database
        List<ParamExport> paramExportList = paramExportRepository.findAll();
        assertThat(paramExportList).hasSize(databaseSizeBeforeCreate + 1);
        ParamExport testParamExport = paramExportList.get(paramExportList.size() - 1);
        assertThat(testParamExport.getpEXPublish()).isEqualTo(DEFAULT_P_EX_PUBLISH);
        assertThat(testParamExport.getpEXDtlastexport()).isEqualTo(DEFAULT_P_EX_DTLASTEXPORT);
    }

    @Test
    @Transactional
    public void createParamExportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paramExportRepository.findAll().size();

        // Create the ParamExport with an existing ID
        paramExport.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParamExportMockMvc.perform(post("/api/param-exports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paramExport)))
            .andExpect(status().isBadRequest());

        // Validate the ParamExport in the database
        List<ParamExport> paramExportList = paramExportRepository.findAll();
        assertThat(paramExportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllParamExports() throws Exception {
        // Initialize the database
        paramExportRepository.saveAndFlush(paramExport);

        // Get all the paramExportList
        restParamExportMockMvc.perform(get("/api/param-exports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paramExport.getId().intValue())))
            .andExpect(jsonPath("$.[*].pEXPublish").value(hasItem(DEFAULT_P_EX_PUBLISH)))
            .andExpect(jsonPath("$.[*].pEXDtlastexport").value(hasItem(DEFAULT_P_EX_DTLASTEXPORT.toString())));
    }
    
    @Test
    @Transactional
    public void getParamExport() throws Exception {
        // Initialize the database
        paramExportRepository.saveAndFlush(paramExport);

        // Get the paramExport
        restParamExportMockMvc.perform(get("/api/param-exports/{id}", paramExport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(paramExport.getId().intValue()))
            .andExpect(jsonPath("$.pEXPublish").value(DEFAULT_P_EX_PUBLISH))
            .andExpect(jsonPath("$.pEXDtlastexport").value(DEFAULT_P_EX_DTLASTEXPORT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingParamExport() throws Exception {
        // Get the paramExport
        restParamExportMockMvc.perform(get("/api/param-exports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParamExport() throws Exception {
        // Initialize the database
        paramExportService.save(paramExport);

        int databaseSizeBeforeUpdate = paramExportRepository.findAll().size();

        // Update the paramExport
        ParamExport updatedParamExport = paramExportRepository.findById(paramExport.getId()).get();
        // Disconnect from session so that the updates on updatedParamExport are not directly saved in db
        em.detach(updatedParamExport);
        updatedParamExport
            .pEXPublish(UPDATED_P_EX_PUBLISH)
            .pEXDtlastexport(UPDATED_P_EX_DTLASTEXPORT);

        restParamExportMockMvc.perform(put("/api/param-exports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedParamExport)))
            .andExpect(status().isOk());

        // Validate the ParamExport in the database
        List<ParamExport> paramExportList = paramExportRepository.findAll();
        assertThat(paramExportList).hasSize(databaseSizeBeforeUpdate);
        ParamExport testParamExport = paramExportList.get(paramExportList.size() - 1);
        assertThat(testParamExport.getpEXPublish()).isEqualTo(UPDATED_P_EX_PUBLISH);
        assertThat(testParamExport.getpEXDtlastexport()).isEqualTo(UPDATED_P_EX_DTLASTEXPORT);
    }

    @Test
    @Transactional
    public void updateNonExistingParamExport() throws Exception {
        int databaseSizeBeforeUpdate = paramExportRepository.findAll().size();

        // Create the ParamExport

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParamExportMockMvc.perform(put("/api/param-exports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paramExport)))
            .andExpect(status().isBadRequest());

        // Validate the ParamExport in the database
        List<ParamExport> paramExportList = paramExportRepository.findAll();
        assertThat(paramExportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParamExport() throws Exception {
        // Initialize the database
        paramExportService.save(paramExport);

        int databaseSizeBeforeDelete = paramExportRepository.findAll().size();

        // Delete the paramExport
        restParamExportMockMvc.perform(delete("/api/param-exports/{id}", paramExport.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParamExport> paramExportList = paramExportRepository.findAll();
        assertThat(paramExportList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParamExport.class);
        ParamExport paramExport1 = new ParamExport();
        paramExport1.setId(1L);
        ParamExport paramExport2 = new ParamExport();
        paramExport2.setId(paramExport1.getId());
        assertThat(paramExport1).isEqualTo(paramExport2);
        paramExport2.setId(2L);
        assertThat(paramExport1).isNotEqualTo(paramExport2);
        paramExport1.setId(null);
        assertThat(paramExport1).isNotEqualTo(paramExport2);
    }
}
