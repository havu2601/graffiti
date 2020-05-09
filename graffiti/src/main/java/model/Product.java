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
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId"),
    @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName LIKE :productName"),
    @NamedQuery(name = "Product.findByAny", query = "SELECT p FROM Product p WHERE p.productName LIKE :searchStr OR p.brandId.brandName LIKE :searchStr OR p.subcatId.subcatName LIKE :searchStr OR p.subcatId.categoryId.categoryName LIKE :searchStr OR p.colorId.colorName LIKE :searchStr"),
    @NamedQuery(name = "Product.findByColorId", query = "SELECT p FROM Product p WHERE p.colorId.colorId = :colorId"),
    @NamedQuery(name = "Product.findByBrandId", query = "SELECT p FROM Product p WHERE p.brandId.brandId = :brandId"),
    @NamedQuery(name = "Product.findBySubCatId", query = "SELECT p FROM Product p WHERE p.subcatId.subcatId = :subcatId"),
    @NamedQuery(name = "Product.findByProductStock", query = "SELECT p FROM Product p WHERE p.productStock = :productStock"),
    @NamedQuery(name = "Product.findByProductPrice", query = "SELECT p FROM Product p WHERE p.productPrice = :productPrice"),
    @NamedQuery(name = "Product.findByProductCapacity", query = "SELECT p FROM Product p WHERE p.productCapacity = :productCapacity"),
    @NamedQuery(name = "Product.findProductDescByName", query = "SELECT p FROM Product p ORDER BY p.productName DESC"),
    @NamedQuery(name = "Product.findProductAscByName", query = "SELECT p FROM Product p ORDER BY p.productName ASC"),
    @NamedQuery(name = "Product.findProductDescByPrice", query = "SELECT p FROM Product p ORDER BY p.productPrice DESC"),
    @NamedQuery(name = "Product.findProductAscByPrice", query = "SELECT p FROM Product p ORDER BY p.productPrice ASC"),
    @NamedQuery(name = "Product.findProductAscByCategory", query = "SELECT p FROM Product p ORDER BY p.subcatId.categoryId ASC"),
    @NamedQuery(name = "Product.findByProductDesc", query = "SELECT p FROM Product p WHERE p.productDesc = :productDesc")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_id")
    private Integer productId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "product_name")
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_stock")
    private int productStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_price")
    private double productPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_capacity")
    private int productCapacity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "product_desc")
    private String productDesc;
    @Basic(optional = false)
    @Column(name = "product_status")
    private int productStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<Image> imageList;
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
    @ManyToOne(optional = false)
    private Brand brandId;
    @JoinColumn(name = "color_id", referencedColumnName = "color_id")
    @ManyToOne(optional = false)
    private Color colorId;
    @JoinColumn(name = "subcat_id", referencedColumnName = "subcat_id")
    @ManyToOne(optional = false)
    private SubCategory subcatId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<ImportDetail> importDetailList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<OrderDetail> orderDetailList;

    public Product() {
    }

    public Product(Integer productId) {
        this.productId = productId;
    }

    public Product(Integer productId, String productName, int productStock, double productPrice, int productCapacity, String productDesc, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.productStock = productStock;
        this.productPrice = productPrice;
        this.productCapacity = productCapacity;
        this.productDesc = productDesc;
        this.productStatus = productStatus;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(int productCapacity) {
        this.productCapacity = productCapacity;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    @XmlTransient
    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public Brand getBrandId() {
        return brandId;
    }

    public void setBrandId(Brand brandId) {
        this.brandId = brandId;
    }

    public Color getColorId() {
        return colorId;
    }

    public void setColorId(Color colorId) {
        this.colorId = colorId;
    }

    public SubCategory getSubcatId() {
        return subcatId;
    }

    public void setSubcatId(SubCategory subcatId) {
        this.subcatId = subcatId;
    }

    @XmlTransient
    public List<ImportDetail> getImportDetailList() {
        return importDetailList;
    }

    public void setImportDetailList(List<ImportDetail> importDetailList) {
        this.importDetailList = importDetailList;
    }

    @XmlTransient
    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Product[ productId=" + productId + " ]";
    }
    
}
