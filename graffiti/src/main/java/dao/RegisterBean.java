/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.AccountEJB;
import ejb.UserRoleEJB;
import java.io.Serializable;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.UserAccount;
import model.UserRole;

/**
 *
 * @author havu2601
 */
@Named(value = "registerBean")
@ViewScoped
public class RegisterBean implements Serializable{

    @EJB
    AccountEJB accEJB;
    @EJB
    UserRoleEJB roleEJB;
    
    UserAccount acc;
    UserRole role;
    String name;
    String phone;
    String email;
    String password;
    String rePassword;
    String address;
    
    
    @PostConstruct
    public void init(){
        acc = new UserAccount();
        role = roleEJB.findByID(3);
        acc.setRoleId(role);
    }
    
    public String register() throws SQLException{
        try {
            if (null==accEJB.findByEmail(email.toLowerCase())){
                acc.setUserEmail(email.toLowerCase());
                acc.setUserName(name);
                acc.setUserContact(phone);
                acc.setUserAddress(address);
                acc.setPassword(password);
                accEJB.register(acc);
                return "index.xhtml?faces-redirect=true";
            }
            else {
                return "register.xhtml";
            }
        } catch(SQLException e){
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public UserAccount getAcc() {
        return acc;
    }

    public void setAcc(UserAccount acc) {
        this.acc = acc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
    
}
