/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.CategoryEJB;
import ejb.ProductEJB;
import ejb.SubcategoryEJB;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Category;
import model.Product;
import model.SubCategory;

/**
 *
 * @author DELL
 */
@Named(value = "subproductBean")
@ViewScoped
public class SubProductBean implements Serializable{

    @EJB
    private ProductEJB ejbProduct;
    
    @EJB
    private SubcategoryEJB ejbSubcat;
    
    @EJB
    private CategoryEJB ejbCate;
    
    List<Product> listProduct;
    List<SubCategory> listSubcat;
    List<Category> listCate;
    
    Category objCategory;
    
    String cateId;
    
    
    @PostConstruct
    public void init(){
        
//        listCate = ejbCate.findAll();
    }
    
    public void loadSubCat(){
        listSubcat = ejbSubcat.findByCategory(Integer.parseInt(cateId));
        listProduct = ejbProduct.findByCate(Integer.parseInt(cateId));
        objCategory = ejbCate.findById(Integer.parseInt(cateId));
    }
    
    public String moveToPage(int id){
        return "subcat_product.xhtml?cid=" +id+"&faces-redirect=true"; 
    }
    
    public void loadProduct(int scId){
        listProduct = ejbProduct.findBySubCat(scId);
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public List<SubCategory> getListSubcat() {
        return listSubcat;
    }

    public void setListSubcat(List<SubCategory> listSubcat) {
        this.listSubcat = listSubcat;
    }

    public List<Category> getListCate() {
        return listCate;
    }

    public void setListCate(List<Category> listCate) {
        this.listCate = listCate;
    }

    public Category getObjCategory() {
        return objCategory;
    }

    public void setObjCategory(Category objCategory) {
        this.objCategory = objCategory;
    }
    
    
}
