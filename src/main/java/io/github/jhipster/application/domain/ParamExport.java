package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A ParamExport.
 */
@Entity
@Table(name = "param_export")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ParamExport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "p_ex_publish")
    private Integer pEXPublish;

    @Column(name = "p_ex_dtlastexport")
    private LocalDate pEXDtlastexport;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getpEXPublish() {
        return pEXPublish;
    }

    public ParamExport pEXPublish(Integer pEXPublish) {
        this.pEXPublish = pEXPublish;
        return this;
    }

    public void setpEXPublish(Integer pEXPublish) {
        this.pEXPublish = pEXPublish;
    }

    public LocalDate getpEXDtlastexport() {
        return pEXDtlastexport;
    }

    public ParamExport pEXDtlastexport(LocalDate pEXDtlastexport) {
        this.pEXDtlastexport = pEXDtlastexport;
        return this;
    }

    public void setpEXDtlastexport(LocalDate pEXDtlastexport) {
        this.pEXDtlastexport = pEXDtlastexport;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParamExport)) {
            return false;
        }
        return id != null && id.equals(((ParamExport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ParamExport{" +
            "id=" + getId() +
            ", pEXPublish=" + getpEXPublish() +
            ", pEXDtlastexport='" + getpEXDtlastexport() + "'" +
            "}";
    }
}
