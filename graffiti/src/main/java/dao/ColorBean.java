/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.ColorEJB;
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
import model.Color;

/**
 *
 * @author DELL
 */
@Named(value = "colorBean")
@ViewScoped
public class ColorBean implements Serializable{

    @EJB
    private ColorEJB ejb;
    @EJB
    private ProductEJB ejbProduct;
    
    List<Color> listColor;
    Color objColor;
    String searchStr;
    String msg;
    
    @PostConstruct
    public void init(){
        objColor = new Color();
        search();
    }

    public void search(){
        if(null==searchStr || searchStr.isEmpty()){
            List<Color> rs = new ArrayList<>();
            rs = ejb.findAll();
            msg = "";
            show(rs,"");
        }else{
            if(null!=ejb.findByAny("%"+searchStr+"%")){
                List<Color> ct = ejb.findByAny("%"+searchStr+"%");
                List<Color> rs = new ArrayList<>();
                rs.addAll(ct);
                msg = "";
                show(rs,msg);
            }
        }
    }
    
    public void show(List<Color> rs, String msg){
        listColor = new ArrayList<>();
        if(rs.isEmpty() || rs==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
        }else{
            listColor.addAll(rs);
        }
    }
    
    public String addNewColor(){
        if(null==objColor.getColorId()){
            if(checkValid()){
                ejb.addColor(objColor);
            }else{
                msg = "Color already exists!";
                return null;
            }
        }else if(!checkValid()){
            if(ejb.findByName(objColor.getColorName()).get(0).getColorId()==objColor.getColorId() && ejb.findByHexcode(objColor.getColorHexcode()).get(0).getColorId()==objColor.getColorId()){
                ejb.updateColor(objColor);
            }else{
                msg = "Color name or hexcode already exists!";
                return null;
            }
        }else{
            ejb.updateColor(objColor);
        }
        return "color.xhtml?faces-redirect=true";
    }
    
    public void reset(){
        objColor = new Color();
        msg = "";
    }
    
    public String remove(int id){
        try {
            if(null== ejbProduct.findByColor(id) || ejbProduct.findByColor(id).isEmpty()){
                ejb.delete(ejb.findById(id));
            }else{
                msg = "Cannot remove!";
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Cannot remove!","Cannot remove!"));
        }
        return "color.xhtml?faces-redirect=true";
    }
    
    public boolean checkValid(){
        if((ejb.findByName(objColor.getColorName()).isEmpty() || ejb.findByName(objColor.getColorName())==null)
                && (ejb.findByHexcode(objColor.getColorHexcode())==null || ejb.findByHexcode(objColor.getColorHexcode()).isEmpty())){
            return true;
        }else{
            return false;
        }
    }
    public void loadColor(int id){
        objColor = ejb.findById(id);
        msg = "";
    }

    public List<Color> getListColor() {
        return listColor;
    }

    public void setListColor(List<Color> listColor) {
        this.listColor = listColor;
    }

    public Color getObjColor() {
        return objColor;
    }

    public void setObjColor(Color objColor) {
        this.objColor = objColor;
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
