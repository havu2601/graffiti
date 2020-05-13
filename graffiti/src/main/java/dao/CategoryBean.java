/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.CategoryEJB;
import ejb.SubcategoryEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Category;


@Named(value = "categoryBean")
@ViewScoped
public class CategoryBean implements Serializable{

    @EJB
    private CategoryEJB ejbCate;
    
    @EJB
    private SubcategoryEJB ejbSubcat;
            
    List<Category> listCategory;
    Category objCategory;
    String searchStr;
    String msg;
    
    @PostConstruct
    public void init(){
        objCategory = new Category();
        msg = "";
        search();
    }
    
    public void search(){
        if(null==searchStr || searchStr.isEmpty()){
            List<Category> rs = new ArrayList<>();
            rs = ejbCate.findAll();
            show(rs,"");
        }else{
            if(null!=ejbCate.findByName("%"+searchStr+"%")){
                List<Category> ct = ejbCate.findByName("%"+searchStr+"%");
                List<Category> rs = new ArrayList<>();
                rs.addAll(ct);
                msg = "";
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
            if(checkValid()){
                ejbCate.addCategory(objCategory);
            }
            else{
                msg = "Category already exists!";
                return null;
            }
        }else if(!checkValid()){
            if(ejbCate.checkName(objCategory.getCategoryName()).get(0).getCategoryId()==objCategory.getCategoryId())
            {
                ejbCate.updateCategory(objCategory);
            }else{
                msg = "Category name already exists!";
                return null;
            }
        }else{
            ejbCate.updateCategory(objCategory);
        }
        return "category.xhtml?faces-redirect=true";
    }
    
    public boolean checkValid(){
        if(null==ejbCate.checkName(objCategory.getCategoryName()) || ejbCate.checkName(objCategory.getCategoryName()).isEmpty()){
            return true;
        }
        return false;
    }
    public void reset(){
        objCategory = new Category();
        msg = "";
    }
    
    public String remove(int id){
        msg = "";
        try {
            if(ejbSubcat.findByCategory(id)==null || ejbSubcat.findByCategory(id).isEmpty()){
            ejbCate.delete(ejbCate.findById(id));
            }else{
                msg = "Cannot remove!";
                return null;
            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
        }
        return "category.xhtml?faces-redirect=true";
    }
    
    public void loadCategory(int id){
        msg = "";
        objCategory = ejbCate.findById(id);
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    

}
