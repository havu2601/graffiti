/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.ImportDetailELB;
import ejb.ImportEJB;
import ejb.ProductEJB;
import ejb.SupplierEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Import;
import model.ImportDetail;
import model.Product;
import model.Supplier;

/**
 *
 * @author Administrator
 */
@Named(value = "importDetailBean")
@ViewScoped
public class ImportDetailBean implements Serializable{

    @EJB
    private ImportDetailELB importDetailELB;
    List<ImportDetail> listImportDetail;
    ImportDetail newImportDetail;
    
    @EJB 
    private ProductEJB productEJB;
    List<Product> listProduct;
    Product newProduct; 
   
    @EJB
    private ImportEJB importEJB;
    List<Import> listImport;
    Import newImport;
            
    private Integer importDetailId;
    private int productQty;
    private String importId;
    private String productId;
    
    
    @PostConstruct
    public void init() {
        newImportDetail = new ImportDetail();
        newImport = new Import();
        newProduct = new Product();
        listImportDetail = importDetailELB.findAll();
    }
    
     public void showDetail(List<ImportDetail> rs, String msg) {
        listImportDetail = new ArrayList<>();
        if (rs.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } else {
            listImportDetail.add((ImportDetail) rs);
        }
    }
     
     public String addNewImportDetail() {

        if (null == newImportDetail.getImportDetailId()) {
            updateImportDetail();
            newImportDetail.setImportDetailId(importDetailId);
            newImportDetail.setImportId(newImport);
            newImportDetail.setProductId(newProduct);           
            importDetailELB.AddNewDetail(newImportDetail);
        } else {
            updateImportDetail();
            newImportDetail.setImportDetailId(importDetailId);
            newImportDetail.setImportId(newImport);
            newImportDetail.setProductId(newProduct);
            importDetailELB.updateImportDetail(newImportDetail);
        }
        importDetailELB.AddNewDetail(newImportDetail);

        return "importdetail.xhtml?faces-redirect=true";
    }
       private void updateImportDetail() {
        newProduct = new Product();
        newProduct.setProductId(productQty);
        newImport = new Import();
        newImport.setImportId(importDetailId);
    }
    
       public void reset(){
           newImportDetail = new ImportDetail();
       }
    public ImportDetailELB getImportDetailELB() {
        return importDetailELB;
    }

    public void setImportDetailELB(ImportDetailELB importDetailELB) {
        this.importDetailELB = importDetailELB;
    }

    public List<ImportDetail> getListImportDetail() {
        return listImportDetail;
    }

    public void setListImportDetail(List<ImportDetail> listImportDetail) {
        this.listImportDetail = listImportDetail;
    }

    public ImportDetail getNewImportDetail() {
        return newImportDetail;
    }

    public void setNewImportDetail(ImportDetail newImportDetail) {
        this.newImportDetail = newImportDetail;
    }

    public ProductEJB getProductEJB() {
        return productEJB;
    }

    public void setProductEJB(ProductEJB productEJB) {
        this.productEJB = productEJB;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public Product getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Product newProduct) {
        this.newProduct = newProduct;
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

    public String getImportId() {
        return importId;
    }

    public void setImportId(String importId) {
        this.importId = importId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

  
    public ImportEJB getImportEJB() {
        return importEJB;
    }

    public void setImportEJB(ImportEJB importEJB) {
        this.importEJB = importEJB;
    }

    public List<Import> getListImport() {
        return listImport;
    }

    public void setListImport(List<Import> listImport) {
        this.listImport = listImport;
    }

    public Import getNewImport() {
        return newImport;
    }

    public void setNewImport(Import newImport) {
        this.newImport = newImport;
    }
    
  
    
}
