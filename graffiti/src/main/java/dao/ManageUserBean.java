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
@Named(value = "manageUserBean")
@ViewScoped
public class ManageUserBean implements Serializable{

    @EJB
    AccountEJB accEJB;
    private List<UserAccount> userList;
    private UserAccount account;
    private String uID;
    
    @PostConstruct
    public void init(){
        userList = new ArrayList<>();
        userList = accEJB.findAll();
        account = new UserAccount();
    }
    
    public void findByID(){
        account = accEJB.findByID(Integer.parseInt(uID));
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    
    
    public List<UserAccount> getUserList() {
        return userList;
    }

    public void setUserList(List<UserAccount> userList) {
        this.userList = userList;
    }
    
    
}
