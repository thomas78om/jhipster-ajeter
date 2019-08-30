package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A RefActivite.
 */
@Entity
@Table(name = "ref_activite")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RefActivite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "r_ac_code")
    private String rACCode;

    @Column(name = "r_ac_lib_court")
    private String rACLibCourt;

    @Column(name = "r_ac_lib_long")
    private String rACLibLong;

    @Column(name = "r_ac_comm")
    private String rACComm;

    @OneToMany(mappedBy = "refActivite")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ActeGestionDelai> acteGestionDelais = new HashSet<>();

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

    public RefActivite rACCode(String rACCode) {
        this.rACCode = rACCode;
        return this;
    }

    public void setrACCode(String rACCode) {
        this.rACCode = rACCode;
    }

    public String getrACLibCourt() {
        return rACLibCourt;
    }

    public RefActivite rACLibCourt(String rACLibCourt) {
        this.rACLibCourt = rACLibCourt;
        return this;
    }

    public void setrACLibCourt(String rACLibCourt) {
        this.rACLibCourt = rACLibCourt;
    }

    public String getrACLibLong() {
        return rACLibLong;
    }

    public RefActivite rACLibLong(String rACLibLong) {
        this.rACLibLong = rACLibLong;
        return this;
    }

    public void setrACLibLong(String rACLibLong) {
        this.rACLibLong = rACLibLong;
    }

    public String getrACComm() {
        return rACComm;
    }

    public RefActivite rACComm(String rACComm) {
        this.rACComm = rACComm;
        return this;
    }

    public void setrACComm(String rACComm) {
        this.rACComm = rACComm;
    }

    public Set<ActeGestionDelai> getActeGestionDelais() {
        return acteGestionDelais;
    }

    public RefActivite acteGestionDelais(Set<ActeGestionDelai> acteGestionDelais) {
        this.acteGestionDelais = acteGestionDelais;
        return this;
    }

    public RefActivite addActeGestionDelai(ActeGestionDelai acteGestionDelai) {
        this.acteGestionDelais.add(acteGestionDelai);
        acteGestionDelai.setRefActivite(this);
        return this;
    }

    public RefActivite removeActeGestionDelai(ActeGestionDelai acteGestionDelai) {
        this.acteGestionDelais.remove(acteGestionDelai);
        acteGestionDelai.setRefActivite(null);
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
        if (!(o instanceof RefActivite)) {
            return false;
        }
        return id != null && id.equals(((RefActivite) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RefActivite{" +
            "id=" + getId() +
            ", rACCode='" + getrACCode() + "'" +
            ", rACLibCourt='" + getrACLibCourt() + "'" +
            ", rACLibLong='" + getrACLibLong() + "'" +
            ", rACComm='" + getrACComm() + "'" +
            "}";
    }
}
