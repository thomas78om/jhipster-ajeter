package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.RefActivite;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RefActivite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefActiviteRepository extends JpaRepository<RefActivite, Long> {

}
