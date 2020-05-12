/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.BrandEJB;
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
    
    @EJB 
    private ProductEJB ejbproduct;
    
    List<Brand> listBrand;
    Brand objBrand;
    int id;
    String name;
    String description;
    String searchStr;
    String msg;
    @PostConstruct
    public void init(){
        objBrand = new Brand();
        msg = "";
        search();
    }
    
    public void search(){
        if(null==searchStr || searchStr.isEmpty()){
            List<Brand> rs = new ArrayList<>();
            rs = ejb.findAll();
            show(rs,"");
        }else{
            if(null!=ejb.findByName("%"+searchStr+"%")){
                List<Brand> br = ejb.findByName("%"+searchStr+"%");
                List<Brand> rs = new ArrayList<>();
                rs.addAll(br);
                msg = "Cannot find Brand with name " + searchStr;
                show(rs,msg);
            }
        }
    }
    
    public void show(List<Brand> rs, String msg){
        listBrand = new ArrayList<>();
        if(rs.isEmpty() || rs==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
        }else{
            listBrand.addAll(rs);
        }
    }
    
    public String addNewBrand(){
        if(objBrand.getBrandId()==null){
            if(checkValid()){
                ejb.addBrand(objBrand);
            }else{
                msg = "Brand already exists!";
                return null;
            }
        }else if(!checkValid()){
            if(ejb.findByName(objBrand.getBrandName()).get(0).getBrandId()==objBrand.getBrandId()){
                ejb.updateBrand(objBrand);
            }else{
                msg = "Brand name already exists!";
                return null;
            }
        }else{
            ejb.updateBrand(objBrand);
        }
        return "brand.xhtml?faces-redirect=true";
    }
    public boolean checkValid(){
        if(null==ejb.findByName(objBrand.getBrandName()) || ejb.findByName(objBrand.getBrandName()).isEmpty()){
            return true;
        }
        return false;
    }
    public void reset(){
        objBrand = new Brand();
        msg = "";
    }
    public String remove(int id){
        try {
            if(ejbproduct.findByBrand(id)==null || ejbproduct.findByBrand(id).isEmpty()){
                ejb.delete(ejb.findById(id));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cannot remove!","Cannot remove!"));
            }else{
                msg = "Cannot remove!";
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cannot remove!","Cannot remove!"));
        }
        return "brand.xhtml?faces-redirect=true";
    }
    
    public void loadBrand(int id){
        msg = "";
        objBrand = ejb.findById(id);
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
}
