/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author havu2601
 */
@Entity
@Table(name = "sub_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubCategory.findAll", query = "SELECT s FROM SubCategory s"),
    @NamedQuery(name = "SubCategory.findBySubcatId", query = "SELECT s FROM SubCategory s WHERE s.subcatId = :subcatId"),
    @NamedQuery(name = "SubCategory.findBySubcatName", query = "SELECT s FROM SubCategory s WHERE s.subcatName = :subcatName")})
public class SubCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "subcat_id")
    private Integer subcatId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "subcat_name")
    private String subcatName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subcatId")
    private List<Product> productList;
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne(optional = false)
    private Category categoryId;

    public SubCategory() {
    }

    public SubCategory(Integer subcatId) {
        this.subcatId = subcatId;
    }

    public SubCategory(Integer subcatId, String subcatName) {
        this.subcatId = subcatId;
        this.subcatName = subcatName;
    }

    public Integer getSubcatId() {
        return subcatId;
    }

    public void setSubcatId(Integer subcatId) {
        this.subcatId = subcatId;
    }

    public String getSubcatName() {
        return subcatName;
    }

    public void setSubcatName(String subcatName) {
        this.subcatName = subcatName;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subcatId != null ? subcatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubCategory)) {
            return false;
        }
        SubCategory other = (SubCategory) object;
        if ((this.subcatId == null && other.subcatId != null) || (this.subcatId != null && !this.subcatId.equals(other.subcatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SubCategory[ subcatId=" + subcatId + " ]";
    }
    
}
