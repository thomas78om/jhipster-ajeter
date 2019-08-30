package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Audit.
 */
@Entity
@Table(name = "audit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Audit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "a_ud_id")
    private Integer aUDId;

    @Column(name = "a_ud_utilisateur")
    private String aUDUtilisateur;

    @Column(name = "a_ud_description")
    private String aUDDescription;

    @Column(name = "a_ud_datetime")
    private LocalDate aUDDatetime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getaUDId() {
        return aUDId;
    }

    public Audit aUDId(Integer aUDId) {
        this.aUDId = aUDId;
        return this;
    }

    public void setaUDId(Integer aUDId) {
        this.aUDId = aUDId;
    }

    public String getaUDUtilisateur() {
        return aUDUtilisateur;
    }

    public Audit aUDUtilisateur(String aUDUtilisateur) {
        this.aUDUtilisateur = aUDUtilisateur;
        return this;
    }

    public void setaUDUtilisateur(String aUDUtilisateur) {
        this.aUDUtilisateur = aUDUtilisateur;
    }

    public String getaUDDescription() {
        return aUDDescription;
    }

    public Audit aUDDescription(String aUDDescription) {
        this.aUDDescription = aUDDescription;
        return this;
    }

    public void setaUDDescription(String aUDDescription) {
        this.aUDDescription = aUDDescription;
    }

    public LocalDate getaUDDatetime() {
        return aUDDatetime;
    }

    public Audit aUDDatetime(LocalDate aUDDatetime) {
        this.aUDDatetime = aUDDatetime;
        return this;
    }

    public void setaUDDatetime(LocalDate aUDDatetime) {
        this.aUDDatetime = aUDDatetime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Audit)) {
            return false;
        }
        return id != null && id.equals(((Audit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Audit{" +
            "id=" + getId() +
            ", aUDId=" + getaUDId() +
            ", aUDUtilisateur='" + getaUDUtilisateur() + "'" +
            ", aUDDescription='" + getaUDDescription() + "'" +
            ", aUDDatetime='" + getaUDDatetime() + "'" +
            "}";
    }
}
