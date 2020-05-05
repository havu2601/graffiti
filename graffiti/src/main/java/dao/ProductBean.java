/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.ImageEJB;
import ejb.ProductEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Image;
import model.Product;

/**
 *
 * @author DELL
 */
@Named(value = "productBean")
@ViewScoped
public class ProductBean implements Serializable{

    @EJB
    private ProductEJB ejbProduct;
    
    @EJB
    private ImageEJB ejbImage;
    
    List<Product> listProduct;
    List<Image> listImage;
    
    Product objProduct;
    Image objImage;
    
    String searchStr;
    String searchType;
    
    @PostConstruct
    public void init(){
        objProduct = new Product();
        objImage = new Image();
        listProduct = ejbProduct.findAll();
    }
    
    public void search(){
        switch(searchType){
            case "subcat":
                break;
            case "brand":
                break;
            case "color":
                break;
            case "":
                break;
            default:
                break;
        }
    }

    public void loadProduct(){
        
    }
    
    public void reset(){
        objProduct = new Product();
        listImage = new ArrayList<>();
    }
    
    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public List<Image> getListImage() {
        return listImage;
    }

    public void setListImage(List<Image> listImage) {
        this.listImage = listImage;
    }

    public Product getObjProduct() {
        return objProduct;
    }

    public void setObjProduct(Product objProduct) {
        this.objProduct = objProduct;
    }

    public Image getObjImage() {
        return objImage;
    }

    public void setObjImage(Image objImage) {
        this.objImage = objImage;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    
    
    
}
