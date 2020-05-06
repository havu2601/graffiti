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
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import model.Brand;
import model.Color;
import model.Image;
import model.Product;
import model.SubCategory;



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
    Brand objBrand;
    SubCategory objSubCat;
    Color objColor;
    
    String brandId;
    String subcatId;
    String colorId;
    String capacity;
    String stock;
    String price;
    Part image;
    
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

    public void loadProduct(int id){
        objProduct = ejbProduct.findById(id);
        brandId = objProduct.getBrandId().getBrandId().toString();
        subcatId = objProduct.getSubcatId().getSubcatId().toString();
        colorId = objProduct.getColorId().getColorId().toString();
        capacity = String.valueOf(objProduct.getProductCapacity());
        price = String.valueOf(objProduct.getProductPrice());
        stock = String.valueOf(objProduct.getProductStock());
    }
    
    public void reset(){
        objProduct = new Product();
        listImage = new ArrayList<>();
        brandId = "";
        subcatId = "";
        colorId = "";
        capacity = "";
        price = "";
        stock = "";
    }
    
    public String addNewProduct(){
        if(null==objProduct.getProductId()){
            updateProduct();
            ejbProduct.addProduct(objProduct);
        }else{
            updateProduct();
            ejbProduct.updateProduct(objProduct);
            reset();
        }
        
        return "product.xhtml";
    }
    
    public void updateProduct(){
        objProduct.setProductPrice(Double.parseDouble(price));
        objProduct.setProductCapacity(Integer.parseInt(capacity));
        objProduct.setProductStock(Integer.parseInt(stock));
        updateBrand();
        objProduct.setBrandId(objBrand);
        updateColor();
        objProduct.setColorId(objColor);
        updateSubCat();
        objProduct.setSubcatId(objSubCat);
    }
    public void updateBrand(){
        objBrand = new Brand();
        objBrand.setBrandId(Integer.parseInt(brandId));
    }
    
    public void updateSubCat(){
        objSubCat = new SubCategory();
        objSubCat.setSubcatId(Integer.parseInt(subcatId));
    }
    
    public void updateColor(){
        objColor = new Color();
        objColor.setColorId(Integer.parseInt(colorId));
    }

    public String moveToImage(int id){
        return "image.xhtml?pid="+id+"&faces-redirect=true";
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

    public Brand getObjBrand() {
        return objBrand;
    }

    public void setObjBrand(Brand objBrand) {
        this.objBrand = objBrand;
    }

    public SubCategory getObjSubCat() {
        return objSubCat;
    }

    public void setObjSubCat(SubCategory objSubCat) {
        this.objSubCat = objSubCat;
    }

    public Color getObjColor() {
        return objColor;
    }

    public void setObjColor(Color objColor) {
        this.objColor = objColor;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getSubcatId() {
        return subcatId;
    }

    public void setSubcatId(String subcatId) {
        this.subcatId = subcatId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
    
    //IMAGE

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }
    
    
}
