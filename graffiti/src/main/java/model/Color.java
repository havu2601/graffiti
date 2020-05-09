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
@Table(name = "color")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Color.findAll", query = "SELECT c FROM Color c"),
    @NamedQuery(name = "Color.findAllSort", query = "SELECT c FROM Color c ORDER BY c.colorHexcode DESC"),
    @NamedQuery(name = "Color.findByColorId", query = "SELECT c FROM Color c WHERE c.colorId = :colorId"),
    @NamedQuery(name = "Color.findByColorHexcode", query = "SELECT c FROM Color c WHERE c.colorHexcode = :colorHexcode"),
    @NamedQuery(name = "Color.findByColorName", query = "SELECT c FROM Color c WHERE c.colorName LIKE :colorName"),
    @NamedQuery(name = "Color.findByAny", query = "SELECT c FROM Color c WHERE c.colorHexcode LIKE :searchStr OR c.colorName LIKE :searchStr"),
    @NamedQuery(name = "Color.findByColorPigment", query = "SELECT c FROM Color c WHERE c.colorPigment = :colorPigment"),
    @NamedQuery(name = "Color.findByColorLightfastness", query = "SELECT c FROM Color c WHERE c.colorLightfastness = :colorLightfastness"),
    @NamedQuery(name = "Color.findByColorCoverage", query = "SELECT c FROM Color c WHERE c.colorCoverage = :colorCoverage"),
    @NamedQuery(name = "Color.findByColorRGB", query = "SELECT c FROM Color c WHERE c.colorRGB = :colorRGB")})
public class Color implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "color_id")
    private Integer colorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "color_hexcode")
    private String colorHexcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "color_name")
    private String colorName;
    @Size(max = 30)
    @Column(name = "color_pigment")
    private String colorPigment;
    @Size(max = 30)
    @Column(name = "color_lightfastness")
    private String colorLightfastness;
    @Size(max = 30)
    @Column(name = "color_coverage")
    private String colorCoverage;
    @Size(max = 30)
    @Column(name = "color_RGB")
    private String colorRGB;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colorId")
    private List<Product> productList;

    public Color() {
    }

    public Color(Integer colorId) {
        this.colorId = colorId;
    }

    public Color(Integer colorId, String colorHexcode, String colorName) {
        this.colorId = colorId;
        this.colorHexcode = colorHexcode;
        this.colorName = colorName;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getColorHexcode() {
        return colorHexcode;
    }

    public void setColorHexcode(String colorHexcode) {
        this.colorHexcode = colorHexcode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorPigment() {
        return colorPigment;
    }

    public void setColorPigment(String colorPigment) {
        this.colorPigment = colorPigment;
    }

    public String getColorLightfastness() {
        return colorLightfastness;
    }

    public void setColorLightfastness(String colorLightfastness) {
        this.colorLightfastness = colorLightfastness;
    }

    public String getColorCoverage() {
        return colorCoverage;
    }

    public void setColorCoverage(String colorCoverage) {
        this.colorCoverage = colorCoverage;
    }

    public String getColorRGB() {
        return colorRGB;
    }

    public void setColorRGB(String colorRGB) {
        this.colorRGB = colorRGB;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (colorId != null ? colorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Color)) {
            return false;
        }
        Color other = (Color) object;
        if ((this.colorId == null && other.colorId != null) || (this.colorId != null && !this.colorId.equals(other.colorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Color[ colorId=" + colorId + " ]";
    }
    
}
