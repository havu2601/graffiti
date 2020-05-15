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
    private String productQty;
    private String importId;
    String searchStr;
    
    @PostConstruct
    public void init() {
        newImportDetail = new ImportDetail();
        newImport = new Import();
        newProduct = new Product();
        listImportDetail = new ArrayList<>();
        listProduct = productEJB.findAll();
    }
    
     public void search(){
        if(null==searchStr || searchStr.isEmpty()){
            List<Product> rs = new ArrayList<>();
            rs = productEJB.findAll();
            listProduct = new ArrayList<>();
            listProduct.addAll(rs);
        }else{
            if(null!=productEJB.findByAny("%"+searchStr+"%")){
                List<Product> p = productEJB.findByAny("%"+searchStr+"%");
                List<Product> rs = new ArrayList<>();
                rs.addAll(p);
                listProduct = new ArrayList<>();
                listProduct.addAll(rs);
            }
        }
    }
     
     
     public void addToListDetail(int id) {
       newProduct = productEJB.findById(id);
       newImportDetail.setProductId(newProduct);
//       newImportDetail.setProductQty(Integer.parseInt(qty));
       listImportDetail.add(newImportDetail);
    }

     public void removeItem(int id){
          newProduct = productEJB.findById(id);
          newImportDetail.setProductId(newProduct);
          listImportDetail.remove(newImportDetail);
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

    public Integer getImportDetailId() {
        return importDetailId;
    }

    public void setImportDetailId(Integer importDetailId) {
        this.importDetailId = importDetailId;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getImportId() {
        return importId;
    }

    public void setImportId(String importId) {
        this.importId = importId;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }
   

    
}
