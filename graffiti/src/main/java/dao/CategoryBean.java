/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.CategoryEJB;
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

/**
 *
 * @author DELL
 */
@Named(value = "categoryBean")
@ViewScoped
public class CategoryBean implements Serializable{

    @EJB
    private CategoryEJB ejb;
    
    List<Category> listCategory;
    Category objCategory;
    String searchStr;
    
    @PostConstruct
    public void init(){
        objCategory = new Category();
        search();
    }
    
    public void search(){
        if(null==searchStr || searchStr.isEmpty()){
            List<Category> rs = new ArrayList<>();
            rs = ejb.findAll();
            show(rs,"");
        }else{
            if(null!=ejb.findByName(searchStr)){
                Category ct = ejb.findByName(searchStr);
                List<Category> rs = new ArrayList<>();
                rs.add(ct);
                String msg = "Cannot find Brand with name " + searchStr;
                show(rs,msg);
            }
        }
    }
    
    public void show(List<Category> rs, String msg){
        listCategory = new ArrayList<>();
        if(rs.isEmpty() || rs==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
        }else{
            listCategory.addAll(rs);
        }
    }
    
    public String addNewCategory(){
        if(null==objCategory.getCategoryId()){
            ejb.addCategory(objCategory);
        }else{
            ejb.updateCategory(objCategory);
        }
        return "category.xhtml?faces-redirect=true";
    }
    
    public void reset(){
        objCategory = new Category();
    }
    
    public String remove(int id){
        try {
            ejb.delete(ejb.findById(id));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cannot remove!","Cannot remove!"));
        }
        return "category.xhtml?faces-redirect=true";
    }
    
    public void loadCategory(int id){
        objCategory = ejb.findById(id);
    }

    public List<Category> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<Category> listCategory) {
        this.listCategory = listCategory;
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
    

    
}
