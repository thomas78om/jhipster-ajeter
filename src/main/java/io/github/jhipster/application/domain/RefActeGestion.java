package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A RefActeGestion.
 */
@Entity
@Table(name = "ref_acte_gestion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RefActeGestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "r_ag_code")
    private String rAGCode;

    @Column(name = "r_ag_lib_court")
    private String rAGLibCourt;

    @Column(name = "r_ag_lib_long")
    private String rAGLibLong;

    @Column(name = "r_ag_comm")
    private String rAGComm;

    @OneToMany(mappedBy = "refActeGestion")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ActeGestionDelai> acteGestionDelais = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getrAGCode() {
        return rAGCode;
    }

    public RefActeGestion rAGCode(String rAGCode) {
        this.rAGCode = rAGCode;
        return this;
    }

    public void setrAGCode(String rAGCode) {
        this.rAGCode = rAGCode;
    }

    public String getrAGLibCourt() {
        return rAGLibCourt;
    }

    public RefActeGestion rAGLibCourt(String rAGLibCourt) {
        this.rAGLibCourt = rAGLibCourt;
        return this;
    }

    public void setrAGLibCourt(String rAGLibCourt) {
        this.rAGLibCourt = rAGLibCourt;
    }

    public String getrAGLibLong() {
        return rAGLibLong;
    }

    public RefActeGestion rAGLibLong(String rAGLibLong) {
        this.rAGLibLong = rAGLibLong;
        return this;
    }

    public void setrAGLibLong(String rAGLibLong) {
        this.rAGLibLong = rAGLibLong;
    }

    public String getrAGComm() {
        return rAGComm;
    }

    public RefActeGestion rAGComm(String rAGComm) {
        this.rAGComm = rAGComm;
        return this;
    }

    public void setrAGComm(String rAGComm) {
        this.rAGComm = rAGComm;
    }

    public Set<ActeGestionDelai> getActeGestionDelais() {
        return acteGestionDelais;
    }

    public RefActeGestion acteGestionDelais(Set<ActeGestionDelai> acteGestionDelais) {
        this.acteGestionDelais = acteGestionDelais;
        return this;
    }

    public RefActeGestion addActeGestionDelai(ActeGestionDelai acteGestionDelai) {
        this.acteGestionDelais.add(acteGestionDelai);
        acteGestionDelai.setRefActeGestion(this);
        return this;
    }

    public RefActeGestion removeActeGestionDelai(ActeGestionDelai acteGestionDelai) {
        this.acteGestionDelais.remove(acteGestionDelai);
        acteGestionDelai.setRefActeGestion(null);
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
        if (!(o instanceof RefActeGestion)) {
            return false;
        }
        return id != null && id.equals(((RefActeGestion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RefActeGestion{" +
            "id=" + getId() +
            ", rAGCode='" + getrAGCode() + "'" +
            ", rAGLibCourt='" + getrAGLibCourt() + "'" +
            ", rAGLibLong='" + getrAGLibLong() + "'" +
            ", rAGComm='" + getrAGComm() + "'" +
            "}";
    }
}
