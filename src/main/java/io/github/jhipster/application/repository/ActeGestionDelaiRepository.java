package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ActeGestionDelai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ActeGestionDelai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActeGestionDelaiRepository extends JpaRepository<ActeGestionDelai, Long> {

}
