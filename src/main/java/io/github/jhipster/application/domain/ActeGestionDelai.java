package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ActeGestionDelai.
 */
@Entity
@Table(name = "acte_gestion_delai")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ActeGestionDelai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "r_ac_code")
    private String rACCode;

    @Column(name = "r_ag_code")
    private String rAGCode;

    @Column(name = "r_ga_code")
    private String rGACode;

    @Column(name = "a_gd_delai")
    private Long aGDDelai;

    @Column(name = "a_gd_attente")
    private Long aGDAttente;

    @Column(name = "a_gd_comm")
    private String aGDComm;

    @ManyToOne
    @JsonIgnoreProperties("acteGestionDelais")
    private RefGroupeActivite refGroupeActivite;

    @ManyToOne
    @JsonIgnoreProperties("acteGestionDelais")
    private RefActeGestion refActeGestion;

    @ManyToOne
    @JsonIgnoreProperties("acteGestionDelais")
    private RefActivite refActivite;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getrACCode() {
        return rACCode;
    }

    public ActeGestionDelai rACCode(String rACCode) {
        this.rACCode = rACCode;
        return this;
    }

    public void setrACCode(String rACCode) {
        this.rACCode = rACCode;
    }

    public String getrAGCode() {
        return rAGCode;
    }

    public ActeGestionDelai rAGCode(String rAGCode) {
        this.rAGCode = rAGCode;
        return this;
    }

    public void setrAGCode(String rAGCode) {
        this.rAGCode = rAGCode;
    }

    public String getrGACode() {
        return rGACode;
    }

    public ActeGestionDelai rGACode(String rGACode) {
        this.rGACode = rGACode;
        return this;
    }

    public void setrGACode(String rGACode) {
        this.rGACode = rGACode;
    }

    public Long getaGDDelai() {
        return aGDDelai;
    }

    public ActeGestionDelai aGDDelai(Long aGDDelai) {
        this.aGDDelai = aGDDelai;
        return this;
    }

    public void setaGDDelai(Long aGDDelai) {
        this.aGDDelai = aGDDelai;
    }

    public Long getaGDAttente() {
        return aGDAttente;
    }

    public ActeGestionDelai aGDAttente(Long aGDAttente) {
        this.aGDAttente = aGDAttente;
        return this;
    }

    public void setaGDAttente(Long aGDAttente) {
        this.aGDAttente = aGDAttente;
    }

    public String getaGDComm() {
        return aGDComm;
    }

    public ActeGestionDelai aGDComm(String aGDComm) {
        this.aGDComm = aGDComm;
        return this;
    }

    public void setaGDComm(String aGDComm) {
        this.aGDComm = aGDComm;
    }

    public RefGroupeActivite getRefGroupeActivite() {
        return refGroupeActivite;
    }

    public ActeGestionDelai refGroupeActivite(RefGroupeActivite refGroupeActivite) {
        this.refGroupeActivite = refGroupeActivite;
        return this;
    }

    public void setRefGroupeActivite(RefGroupeActivite refGroupeActivite) {
        this.refGroupeActivite = refGroupeActivite;
    }

    public RefActeGestion getRefActeGestion() {
        return refActeGestion;
    }

    public ActeGestionDelai refActeGestion(RefActeGestion refActeGestion) {
        this.refActeGestion = refActeGestion;
        return this;
    }

    public void setRefActeGestion(RefActeGestion refActeGestion) {
        this.refActeGestion = refActeGestion;
    }

    public RefActivite getRefActivite() {
        return refActivite;
    }

    public ActeGestionDelai refActivite(RefActivite refActivite) {
        this.refActivite = refActivite;
        return this;
    }

    public void setRefActivite(RefActivite refActivite) {
        this.refActivite = refActivite;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActeGestionDelai)) {
            return false;
        }
        return id != null && id.equals(((ActeGestionDelai) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ActeGestionDelai{" +
            "id=" + getId() +
            ", rACCode='" + getrACCode() + "'" +
            ", rAGCode='" + getrAGCode() + "'" +
            ", rGACode='" + getrGACode() + "'" +
            ", aGDDelai=" + getaGDDelai() +
            ", aGDAttente=" + getaGDAttente() +
            ", aGDComm='" + getaGDComm() + "'" +
            "}";
    }
}
