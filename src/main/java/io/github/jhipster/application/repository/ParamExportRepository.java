package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ParamExport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ParamExport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParamExportRepository extends JpaRepository<ParamExport, Long> {

}
