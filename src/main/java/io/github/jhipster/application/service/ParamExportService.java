package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ParamExport;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ParamExport}.
 */
public interface ParamExportService {

    /**
     * Save a paramExport.
     *
     * @param paramExport the entity to save.
     * @return the persisted entity.
     */
    ParamExport save(ParamExport paramExport);

    /**
     * Get all the paramExports.
     *
     * @return the list of entities.
     */
    List<ParamExport> findAll();


    /**
     * Get the "id" paramExport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ParamExport> findOne(Long id);

    /**
     * Delete the "id" paramExport.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
