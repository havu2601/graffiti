/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author havu2601
 */
@Entity
@Table(name = "import_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImportDetail.findAll", query = "SELECT i FROM ImportDetail i"),
    @NamedQuery(name = "ImportDetail.findByImportDetailId", query = "SELECT i FROM ImportDetail i WHERE i.importDetailId = :importDetailId"),
    @NamedQuery(name = "ImportDetail.findByProductQty", query = "SELECT i FROM ImportDetail i WHERE i.productQty = :productQty")})
public class ImportDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "import_detail_id")
    private Integer importDetailId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_qty")
    private int productQty;
    @JoinColumn(name = "import_id", referencedColumnName = "import_id")
    @ManyToOne(optional = false)
    private Import importId;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private Product productId;

    public ImportDetail() {
    }

    public ImportDetail(Integer importDetailId) {
        this.importDetailId = importDetailId;
    }

    public ImportDetail(Integer importDetailId, int productQty) {
        this.importDetailId = importDetailId;
        this.productQty = productQty;
    }

    public Integer getImportDetailId() {
        return importDetailId;
    }

    public void setImportDetailId(Integer importDetailId) {
        this.importDetailId = importDetailId;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public Import getImportId() {
        return importId;
    }

    public void setImportId(Import importId) {
        this.importId = importId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (importDetailId != null ? importDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImportDetail)) {
            return false;
        }
        ImportDetail other = (ImportDetail) object;
        if ((this.importDetailId == null && other.importDetailId != null) || (this.importDetailId != null && !this.importDetailId.equals(other.importDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ImportDetail[ importDetailId=" + importDetailId + " ]";
    }
    
}
