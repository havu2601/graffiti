/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author havu2601
 */
@Entity
@Table(name = "import")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Import.findAll", query = "SELECT i FROM Import i"),
    @NamedQuery(name = "Import.findByImportId", query = "SELECT i FROM Import i WHERE i.importId = :importId"),
    @NamedQuery(name = "Import.findByImportDate", query = "SELECT i FROM Import i WHERE i.importDate = :importDate"),
    @NamedQuery(name = "Import.findByImportStatus", query = "SELECT i FROM Import i WHERE i.importStatus = :importStatus")})
public class Import implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "import_id")
    private Integer importId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "import_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date importDate;
    @Size(max = 50)
    @Column(name = "import_status")
    private String importStatus;
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    @ManyToOne(optional = false)
    private Supplier supplierId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private UserAccount userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "importId")
    private List<ImportDetail> importDetailList;

    public Import() {
    }

    public Import(Integer importId) {
        this.importId = importId;
    }

    public Import(Integer importId, Date importDate) {
        this.importId = importId;
        this.importDate = importDate;
    }

    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }

    public Supplier getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Supplier supplierId) {
        this.supplierId = supplierId;
    }

    public UserAccount getUserId() {
        return userId;
    }

    public void setUserId(UserAccount userId) {
        this.userId = userId;
    }

    @XmlTransient
    public List<ImportDetail> getImportDetailList() {
        return importDetailList;
    }

    public void setImportDetailList(List<ImportDetail> importDetailList) {
        this.importDetailList = importDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (importId != null ? importId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Import)) {
            return false;
        }
        Import other = (Import) object;
        if ((this.importId == null && other.importId != null) || (this.importId != null && !this.importId.equals(other.importId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Import[ importId=" + importId + " ]";
    }
    
}
