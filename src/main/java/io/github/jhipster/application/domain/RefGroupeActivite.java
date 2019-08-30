package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A RefGroupeActivite.
 */
@Entity
@Table(name = "ref_groupe_activite")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RefGroupeActivite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "r_ga_code")
    private String rGACode;

    @Column(name = "r_ga_lib_court")
    private String rGALibCourt;

    @Column(name = "r_ga_lib_long")
    private String rGALibLong;

    @Column(name = "r_ga_comm")
    private String rGAComm;

    @OneToMany(mappedBy = "refGroupeActivite")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ActeGestionDelai> acteGestionDelais = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getrGACode() {
        return rGACode;
    }

    public RefGroupeActivite rGACode(String rGACode) {
        this.rGACode = rGACode;
        return this;
    }

    public void setrGACode(String rGACode) {
        this.rGACode = rGACode;
    }

    public String getrGALibCourt() {
        return rGALibCourt;
    }

    public RefGroupeActivite rGALibCourt(String rGALibCourt) {
        this.rGALibCourt = rGALibCourt;
        return this;
    }

    public void setrGALibCourt(String rGALibCourt) {
        this.rGALibCourt = rGALibCourt;
    }

    public String getrGALibLong() {
        return rGALibLong;
    }

    public RefGroupeActivite rGALibLong(String rGALibLong) {
        this.rGALibLong = rGALibLong;
        return this;
    }

    public void setrGALibLong(String rGALibLong) {
        this.rGALibLong = rGALibLong;
    }

    public String getrGAComm() {
        return rGAComm;
    }

    public RefGroupeActivite rGAComm(String rGAComm) {
        this.rGAComm = rGAComm;
        return this;
    }

    public void setrGAComm(String rGAComm) {
        this.rGAComm = rGAComm;
    }

    public Set<ActeGestionDelai> getActeGestionDelais() {
        return acteGestionDelais;
    }

    public RefGroupeActivite acteGestionDelais(Set<ActeGestionDelai> acteGestionDelais) {
        this.acteGestionDelais = acteGestionDelais;
        return this;
    }

    public RefGroupeActivite addActeGestionDelai(ActeGestionDelai acteGestionDelai) {
        this.acteGestionDelais.add(acteGestionDelai);
        acteGestionDelai.setRefGroupeActivite(this);
        return this;
    }

    public RefGroupeActivite removeActeGestionDelai(ActeGestionDelai acteGestionDelai) {
        this.acteGestionDelais.remove(acteGestionDelai);
        acteGestionDelai.setRefGroupeActivite(null);
        return this;
    }

    public void setActeGestionDelais(Set<ActeGestionDelai> acteGestionDelais) {
        this.acteGestionDelais = acteGestionDelais;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RefGroupeActivite)) {
            return false;
        }
        return id != null && id.equals(((RefGroupeActivite) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RefGroupeActivite{" +
            "id=" + getId() +
            ", rGACode='" + getrGACode() + "'" +
            ", rGALibCourt='" + getrGALibCourt() + "'" +
            ", rGALibLong='" + getrGALibLong() + "'" +
            ", rGAComm='" + getrGAComm() + "'" +
            "}";
    }
}
