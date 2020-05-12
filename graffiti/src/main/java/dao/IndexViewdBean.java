/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.ProductEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Product;

/**
 *
 * @author DELL
 */
@Named(value = "indexViewdBean")
@ViewScoped
public class IndexViewdBean implements Serializable{

    @EJB
    private ProductEJB ejbProduct;
    
    List<Product> listProduct;
    String searchStr;
    
    @PostConstruct
    public void init(){
        listProduct = ejbProduct.findProductAscByCategory();
    }

    public void search(){
        if(null==searchStr || searchStr.isEmpty()){
            List<Product> rs = new ArrayList<>();
            rs = ejbProduct.findAll();
            show(rs,"");
        }else{
            if(null!=ejbProduct.findByAnyClient("%"+searchStr+"%")){
                List<Product> p = ejbProduct.findByAnyClient("%"+searchStr+"%");
                List<Product> rs = new ArrayList<>();
                rs.addAll(p);
                String msg = "Cannot find Product with name " + searchStr;
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
    
    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }
    
}
