/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.CategoryEJB;
import ejb.ImageEJB;
import ejb.ProductEJB;
import ejb.SubcategoryEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Category;
import model.Image;
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
    
    @EJB
    private ImageEJB ejbImage;
    
    List<Product> listProduct;
    List<SubCategory> listSubcat;
    List<Category> listCate;
    List<Image> objImage;
    Category objCategory;
    
    String cateId;
    
    
    @PostConstruct
    public void init(){
        
//        listCate = ejbCate.findAll();
    }
    
    public List<Image> getTwoImage(int id){
        List<Image> rs = new ArrayList<>();
        objImage = ejbImage.findByProduct(id);
        for (int i = 0; i < 1; i++) {
            rs.add(objImage.get(i));
        }
        return rs;
    }
    
    public void loadSubCat(){
        listSubcat = ejbSubcat.findByCategory(Integer.parseInt(cateId));
        listProduct = ejbProduct.findByCateClient(Integer.parseInt(cateId));
        objCategory = ejbCate.findById(Integer.parseInt(cateId));
    }
    
    public String moveToPage(int id){
        return "subcat_product.xhtml?cid=" +id+"&faces-redirect=true"; 
    }
    
    public void loadProductBySubcat(int scId){
        listProduct = ejbProduct.findBySubCatClient(scId);
    }
    
    public void loadProductByColor(int cId){
        listProduct = ejbProduct.findByColorClient(cId);
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

    public List<Image> getObjImage() {
        return objImage;
    }

    public void setObjImage(List<Image> objImage) {
        this.objImage = objImage;
    }
    
    
}
