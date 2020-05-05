/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.AccountEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.UserAccount;

/**
 *
 * @author havu2601
 */
@Named(value = "userAdminBean")
@ViewScoped
public class UserAdminBean implements Serializable{

    @EJB
    AccountEJB accEJB;
    private List<UserAccount> userList;
    
    @PostConstruct
    public void init(){
        userList = new ArrayList<>();
        userList = accEJB.findAll();
    }

    public List<UserAccount> getUserList() {
        return userList;
    }

    public void setUserList(List<UserAccount> userList) {
        this.userList = userList;
    }
    
    
}
