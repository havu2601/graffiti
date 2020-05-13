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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.Brand;
import model.Color;
import model.Image;
import model.Product;
import model.SubCategory;


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
    Brand objBrand;
    SubCategory objSubCat;
    Color objColor;
    Image objImage;
    
    String productId;
    String brandId;
    String subcatId;
    String colorId;
    String capacity;
    String stock;
    String price;
    String status;
    
    String searchStr;
    String searchType;
    String msg;
    Boolean radState;
    
    private List<HistoryItem> listHisItem = new ArrayList<>();
    private List<HistoryItem> listShowHis = new ArrayList<>();
    private Boolean isEmpty;
    
    @PostConstruct
    public void init(){
        objProduct = new Product();
        msg = "";
        radState = true;
        status = "0";
        search();
    }
    
    public void search(){
        if(null==searchStr || searchStr.isEmpty()){
            List<Product> rs = new ArrayList<>();
            rs = ejbProduct.findAll();
            show(rs,"");
        }else{
            if(null!=ejbProduct.findByAny("%"+searchStr+"%")){
                List<Product> p = ejbProduct.findByAny("%"+searchStr+"%");
                List<Product> rs = new ArrayList<>();
                rs.addAll(p);
                msg = "Cannot find Product with keyword " + searchStr;
                show(rs,msg);
            }
        }
    }
    
    public void show(List<Product> rs, String msg){
        listProduct = new ArrayList<>();
        if(rs.isEmpty() || rs==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
        }else{
            listProduct.addAll(rs);
        }
    }

    public void showDetail(){
        objProduct = ejbProduct.findById(Integer.parseInt(productId));
        listImage = ejbImage.findByProduct(objProduct.getProductId());
        if(null!=listImage && !listImage.isEmpty()){
            objImage = listImage.get(0);
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
        status = String.valueOf(objProduct.getProductStatus());
        msg = "";
        radState = false;
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
        status = "0";
        msg = "";
        radState = true;
    }
    
    public String addNewProduct(){
        updateProduct();
        if(null==objProduct.getProductId()){
            if(checkProductExist()){
                ejbProduct.addProduct(objProduct);
            }else{
                msg = "Product already exists!";
                return null;
            }
        }else if(!checkProductExist()){
            Product check = ejbProduct.findCheckExist(objProduct.getProductName(), Integer.parseInt(brandId), Integer.parseInt(colorId)).get(0);
            if(check.getProductId() == objProduct.getProductId()){
                if(!checkHaveImage() && objProduct.getProductStatus()==1){
                    objProduct.setProductStatus(0);
                    msg = "Product have no image for active!";
                    return null;
                }
                ejbProduct.updateProduct(objProduct);
            }else{
                msg = "Product already exists!";
            }
        }else{
            if(!checkHaveImage() && objProduct.getProductStatus()==1){
                objProduct.setProductStatus(0);
                msg = "Product have no image for active!";
                return null;
            }
            ejbProduct.updateProduct(objProduct);
        }
        return "product.xhtml";
    }
    
    public boolean checkHaveImage(){
        if(ejbImage.findByProduct(objProduct.getProductId()).isEmpty() || ejbImage.findByProduct(objProduct.getProductId())==null){
            return false;
        }else{
            return true;
        }
    }
    
    public void updateProduct(){
        objProduct.setProductPrice(Double.parseDouble(price));
        objProduct.setProductCapacity(Integer.parseInt(capacity));
        objProduct.setProductStock(Integer.parseInt(stock));
        objProduct.setProductStatus(Integer.parseInt(status));
        updateBrand();
        objProduct.setBrandId(objBrand);
        updateColor();
        objProduct.setColorId(objColor);
        updateSubCat();
        objProduct.setSubcatId(objSubCat);
    }
    public boolean checkProductExist(){
        if(ejbProduct.findCheckExist(objProduct.getProductName(), Integer.parseInt(brandId), Integer.parseInt(colorId))==null || 
                ejbProduct.findCheckExist(objProduct.getProductName(), Integer.parseInt(brandId), Integer.parseInt(colorId)).isEmpty()){
            return true;
        }
        return false;
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
    //SORT
    public void sortNameAsc(){
        listProduct = ejbProduct.findProductAscByName();
    }
    public void sortNameDesc(){
        listProduct = ejbProduct.findProductDescByName();
    }
    public void sortPriceAsc(){
        listProduct = ejbProduct.findProductAscByPrice();
    }
    public void sortPriceDesc(){
        listProduct = ejbProduct.findProductDescByPrice();
    }
    public void sortcategory(){
        listProduct = ejbProduct.findProductAscByCategory();
    }
        
    //GETTER SETTER
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Image getObjImage() {
        return objImage;
    }

    public void setObjImage(Image objImage) {
        this.objImage = objImage;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getRadState() {
        return radState;
    }

    public void setRadState(Boolean radState) {
        this.radState = radState;
    }
    
    public String addToHisItem(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session.getAttribute("historyproduct")!=null){
        listHisItem = (List<HistoryItem>) session.getAttribute("historyproduct");}
        if(listHisItem.size()>=5){
            listHisItem.remove(0);
        }
        HistoryItem item = new HistoryItem();
        Product pItem = ejbProduct.findById(Integer.parseInt(productId));
        item.setProduct(pItem);
        listHisItem.add(item);
        session.setAttribute("historyproduct", listHisItem);
        for (int i = 0; i < listHisItem.size()-1; i++) {
            listShowHis.add(listHisItem.get(i));
        }
        return null;
    }

    public List<HistoryItem> getListHisItem() {
        return listHisItem;
    }

    public void setListHisItem(List<HistoryItem> listHisItem) {
        this.listHisItem = listHisItem;
    }

    public Boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(Boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public List<HistoryItem> getListShowHis() {
        return listShowHis;
    }

    public void setListShowHis(List<HistoryItem> listShowHis) {
        this.listShowHis = listShowHis;
    }
    
}
