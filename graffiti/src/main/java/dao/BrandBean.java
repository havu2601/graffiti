/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.BrandEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Brand;

/**
 *
 * @author DELL
 */
@Named(value = "brandBean")
@ViewScoped
public class BrandBean implements Serializable{

    @EJB
    private BrandEJB ejb;
    
    List<Brand> listBrand;
    Brand objBrand;
    int id;
    String name;
    String description;
    String searchStr;

    @PostConstruct
    public void init(){
        objBrand = new Brand();
        search();
    }
    
    public void search(){
        if(null==searchStr || searchStr.isEmpty()){
            List<Brand> rs = new ArrayList<>();
            rs = ejb.findAll();
            show(rs,"");
        }else{
            Brand br = ejb.findByName(searchStr);
            List<Brand> rs = new ArrayList<>();
            rs.add(br);
            String msg = "Cannot find Brand with name " + searchStr;
            show(rs,msg);
        }
    }
    
    public void show(List<Brand> rs, String msg){
        listBrand = new ArrayList<>();
        if(rs.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
        }else{
            listBrand.addAll(rs);
        }
    }
    
    public String addNewBrand(){
        ejb.addBrand(objBrand);
        return "brand.xhtml?faces-redirect=true";
    }
    
    public List<Brand> getListBrand() {
        return listBrand;
    }

    public void setListBrand(List<Brand> listBrand) {
        this.listBrand = listBrand;
    }

    public Brand getObjBrand() {
        return objBrand;
    }

    public void setObjBrand(Brand objBrand) {
        this.objBrand = objBrand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }
    
    
}
