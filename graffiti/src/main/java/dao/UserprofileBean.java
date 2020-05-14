/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.AccountEJB;
import ejb.UserProfileEJB;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import model.UserAccount;
import org.eclipse.persistence.tools.profiler.Profile;


/**
 *
 * @author Administrator
 */
@Named(value = "userprofileBean")
@ViewScoped
public class UserprofileBean implements Serializable{

     
   
   @EJB
   private AccountEJB ejbAccount;
           
   private UserAccount newUserAccount;
   Profile newProfile;
   
   UserAccount acc;
   private String userId;
   private String name;
   private String phone;
   private String email;
   private String password;
   private String rePassword;
   private String address;
   
    @PostConstruct
    public void init(){

        FacesContext req = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) req.getExternalContext().getSession(false);
        UserBean userSession = (UserBean) session.getAttribute("userSession");
        userId = userSession.getAcc().getUserId().toString();

    }
     
    public void editProfile(){
        ejbAccount.editProfile(newUserAccount);
    }

    public void loadProfile(){
       newUserAccount = ejbAccount.findByID(Integer.parseInt(userId)); 
    }
     
    
    public Profile getNewProfile() {
        return newProfile;
    }

    public void setNewProfile(Profile newProfile) {
        this.newProfile = newProfile;
    }

    public UserAccount getAcc() {
        return acc;
    }

    public void setAcc(UserAccount acc) {
        this.acc = acc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserAccount getNewUserAccount() {
        return newUserAccount;
    }

    public void setNewUserAccount(UserAccount newUserAccount) {
        this.newUserAccount = newUserAccount;
    }


}