package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.AjeterApp;
import io.github.jhipster.application.domain.Audit;
import io.github.jhipster.application.repository.AuditRepository;
import io.github.jhipster.application.service.AuditService;
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
 * Integration tests for the {@link AuditResource} REST controller.
 */
@SpringBootTest(classes = AjeterApp.class)
public class AuditResourceIT {

    private static final Integer DEFAULT_A_UD_ID = 1;
    private static final Integer UPDATED_A_UD_ID = 2;
    private static final Integer SMALLER_A_UD_ID = 1 - 1;

    private static final String DEFAULT_A_UD_UTILISATEUR = "AAAAAAAAAA";
    private static final String UPDATED_A_UD_UTILISATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_A_UD_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_A_UD_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_A_UD_DATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_A_UD_DATETIME = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_A_UD_DATETIME = LocalDate.ofEpochDay(-1L);

    @Autowired
    private AuditRepository auditRepository;

    @Autowired
    private AuditService auditService;

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

    private MockMvc restAuditMockMvc;

    private Audit audit;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AuditResource auditResource = new AuditResource(auditService);
        this.restAuditMockMvc = MockMvcBuilders.standaloneSetup(auditResource)
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
    public static Audit createEntity(EntityManager em) {
        Audit audit = new Audit()
            .aUDId(DEFAULT_A_UD_ID)
            .aUDUtilisateur(DEFAULT_A_UD_UTILISATEUR)
            .aUDDescription(DEFAULT_A_UD_DESCRIPTION)
            .aUDDatetime(DEFAULT_A_UD_DATETIME);
        return audit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Audit createUpdatedEntity(EntityManager em) {
        Audit audit = new Audit()
            .aUDId(UPDATED_A_UD_ID)
            .aUDUtilisateur(UPDATED_A_UD_UTILISATEUR)
            .aUDDescription(UPDATED_A_UD_DESCRIPTION)
            .aUDDatetime(UPDATED_A_UD_DATETIME);
        return audit;
    }

    @BeforeEach
    public void initTest() {
        audit = createEntity(em);
    }

    @Test
    @Transactional
    public void createAudit() throws Exception {
        int databaseSizeBeforeCreate = auditRepository.findAll().size();

        // Create the Audit
        restAuditMockMvc.perform(post("/api/audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(audit)))
            .andExpect(status().isCreated());

        // Validate the Audit in the database
        List<Audit> auditList = auditRepository.findAll();
        assertThat(auditList).hasSize(databaseSizeBeforeCreate + 1);
        Audit testAudit = auditList.get(auditList.size() - 1);
        assertThat(testAudit.getaUDId()).isEqualTo(DEFAULT_A_UD_ID);
        assertThat(testAudit.getaUDUtilisateur()).isEqualTo(DEFAULT_A_UD_UTILISATEUR);
        assertThat(testAudit.getaUDDescription()).isEqualTo(DEFAULT_A_UD_DESCRIPTION);
        assertThat(testAudit.getaUDDatetime()).isEqualTo(DEFAULT_A_UD_DATETIME);
    }

    @Test
    @Transactional
    public void createAuditWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = auditRepository.findAll().size();

        // Create the Audit with an existing ID
        audit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuditMockMvc.perform(post("/api/audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(audit)))
            .andExpect(status().isBadRequest());

        // Validate the Audit in the database
        List<Audit> auditList = auditRepository.findAll();
        assertThat(auditList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAudits() throws Exception {
        // Initialize the database
        auditRepository.saveAndFlush(audit);

        // Get all the auditList
        restAuditMockMvc.perform(get("/api/audits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(audit.getId().intValue())))
            .andExpect(jsonPath("$.[*].aUDId").value(hasItem(DEFAULT_A_UD_ID)))
            .andExpect(jsonPath("$.[*].aUDUtilisateur").value(hasItem(DEFAULT_A_UD_UTILISATEUR.toString())))
            .andExpect(jsonPath("$.[*].aUDDescription").value(hasItem(DEFAULT_A_UD_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].aUDDatetime").value(hasItem(DEFAULT_A_UD_DATETIME.toString())));
    }
    
    @Test
    @Transactional
    public void getAudit() throws Exception {
        // Initialize the database
        auditRepository.saveAndFlush(audit);

        // Get the audit
        restAuditMockMvc.perform(get("/api/audits/{id}", audit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(audit.getId().intValue()))
            .andExpect(jsonPath("$.aUDId").value(DEFAULT_A_UD_ID))
            .andExpect(jsonPath("$.aUDUtilisateur").value(DEFAULT_A_UD_UTILISATEUR.toString()))
            .andExpect(jsonPath("$.aUDDescription").value(DEFAULT_A_UD_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.aUDDatetime").value(DEFAULT_A_UD_DATETIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAudit() throws Exception {
        // Get the audit
        restAuditMockMvc.perform(get("/api/audits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAudit() throws Exception {
        // Initialize the database
        auditService.save(audit);

        int databaseSizeBeforeUpdate = auditRepository.findAll().size();

        // Update the audit
        Audit updatedAudit = auditRepository.findById(audit.getId()).get();
        // Disconnect from session so that the updates on updatedAudit are not directly saved in db
        em.detach(updatedAudit);
        updatedAudit
            .aUDId(UPDATED_A_UD_ID)
            .aUDUtilisateur(UPDATED_A_UD_UTILISATEUR)
            .aUDDescription(UPDATED_A_UD_DESCRIPTION)
            .aUDDatetime(UPDATED_A_UD_DATETIME);

        restAuditMockMvc.perform(put("/api/audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAudit)))
            .andExpect(status().isOk());

        // Validate the Audit in the database
        List<Audit> auditList = auditRepository.findAll();
        assertThat(auditList).hasSize(databaseSizeBeforeUpdate);
        Audit testAudit = auditList.get(auditList.size() - 1);
        assertThat(testAudit.getaUDId()).isEqualTo(UPDATED_A_UD_ID);
        assertThat(testAudit.getaUDUtilisateur()).isEqualTo(UPDATED_A_UD_UTILISATEUR);
        assertThat(testAudit.getaUDDescription()).isEqualTo(UPDATED_A_UD_DESCRIPTION);
        assertThat(testAudit.getaUDDatetime()).isEqualTo(UPDATED_A_UD_DATETIME);
    }

    @Test
    @Transactional
    public void updateNonExistingAudit() throws Exception {
        int databaseSizeBeforeUpdate = auditRepository.findAll().size();

        // Create the Audit

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuditMockMvc.perform(put("/api/audits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(audit)))
            .andExpect(status().isBadRequest());

        // Validate the Audit in the database
        List<Audit> auditList = auditRepository.findAll();
        assertThat(auditList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAudit() throws Exception {
        // Initialize the database
        auditService.save(audit);

        int databaseSizeBeforeDelete = auditRepository.findAll().size();

        // Delete the audit
        restAuditMockMvc.perform(delete("/api/audits/{id}", audit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Audit> auditList = auditRepository.findAll();
        assertThat(auditList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Audit.class);
        Audit audit1 = new Audit();
        audit1.setId(1L);
        Audit audit2 = new Audit();
        audit2.setId(audit1.getId());
        assertThat(audit1).isEqualTo(audit2);
        audit2.setId(2L);
        assertThat(audit1).isNotEqualTo(audit2);
        audit1.setId(null);
        assertThat(audit1).isNotEqualTo(audit2);
    }
}
