/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.ProductEJB;
import ejb.SubcategoryEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Category;
import model.SubCategory;

/**
 *
 * @author DELL
 */
@Named(value = "subcategoryBean")
@ViewScoped
public class SubcategoryBean implements Serializable{

    @EJB
    private SubcategoryEJB ejb;
    
    @EJB
    private ProductEJB ejbProduct;
    
    List<SubCategory> listSubCat;
    SubCategory objSubCat;
    Category objCategory;
    String searchStr;
    String categoryId;
    String msg;
    @PostConstruct
    public void init(){
        objCategory = new Category();
        objSubCat = new SubCategory();
        msg = "";
        search();
    }

    public void search(){
        if(null==searchStr || searchStr.isEmpty()){
            List<SubCategory> rs = new ArrayList<>();
            rs = ejb.findAll();
            show(rs,"");
        }else{
            if(null!=ejb.findByName("%"+searchStr+"%")){
                List<SubCategory> ct = ejb.findByName("%"+searchStr+"%");
                List<SubCategory> rs = new ArrayList<>();
                rs.addAll(ct);
                msg = "";
                show(rs,msg);
            }
        }
    }
    
    public void show(List<SubCategory> rs, String msg){
        listSubCat = new ArrayList<>();
        if(rs.isEmpty() || rs==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
        }else{
            listSubCat.addAll(rs);
        }
    }
    
    public String addNewSubCategory(){
        updateCategory();
        objSubCat.setCategoryId(objCategory);
        if(null==objSubCat.getSubcatId()){
            if(checkValid()){
                ejb.addSubCat(objSubCat);
            }else{
                msg = "Subcategory already exists!";
                return null;
            }
        }else if(!checkValid()){
            if(ejb.findByName(objSubCat.getSubcatName()).get(0).getSubcatId()==objSubCat.getSubcatId()){
                ejb.updateSubCat(objSubCat);
            }else{
                msg = "Subcategory name already exists!";
                return null;
            }
        }else{
            ejb.updateSubCat(objSubCat);
        }
        return "subcategory.xhtml?faces-redirect=true";
    }
    
    public void updateCategory(){
        objCategory = new Category();
        objCategory.setCategoryId(Integer.parseInt(categoryId));
    }
    public boolean checkValid(){
        if(null==ejb.findByName(objSubCat.getSubcatName()) || ejb.findByName(objSubCat.getSubcatName()).isEmpty()){
            return true;
        }
        return false;
    }
    public void reset(){
        objSubCat = new SubCategory();
        msg = "";
    }
    
    public String remove(int id){
        try {
            if(ejbProduct.findBySubCat(id)==null || ejbProduct.findBySubCat(id).isEmpty()){
                ejb.delete(ejb.findById(id));
            }else{
                msg = "Cannot remove!";
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cannot remove!","Cannot remove!"));
        }
        return "subcategory.xhtml?faces-redirect=true";
    }
    
    public void loadSubCategory(int id){
        msg = "";
        objSubCat = ejb.findById(id);
        categoryId = objSubCat.getCategoryId().getCategoryId().toString();
    }
    
    public List<SubCategory> getListSubCat() {
        return listSubCat;
    }

    public void setListSubCat(List<SubCategory> listSubCat) {
        this.listSubCat = listSubCat;
    }

    public SubCategory getObjSubCat() {
        return objSubCat;
    }

    public void setObjSubCat(SubCategory objSubCat) {
        this.objSubCat = objSubCat;
    }

    public Category getObjCategory() {
        return objCategory;
    }

    public void setObjCategory(Category objCategory) {
        this.objCategory = objCategory;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    
}
