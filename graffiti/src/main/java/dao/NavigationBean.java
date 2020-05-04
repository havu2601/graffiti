/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author DELL
 */
@Named(value = "navigationBean")
@RequestScoped
public class NavigationBean implements Serializable{

//    @ManagedProperty(value = "#{param.pageName}")
//    private String pageName;
//    
    public String showPage(String pageName){
        if(null==pageName){
            return "admin_homepage";
        }
        else{
            return pageName;
        }
    }

//    public String getPageName() {
//        return pageName;
//    }
//
//    public void setPageName(String pageName) {
//        this.pageName = pageName;
//    }
//    
    
}
