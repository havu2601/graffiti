/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.AccountEJB;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import model.UserAccount;

/**
 *
 * @author havu2601
 */
@Named(value = "adminLoginBean")
@ViewScoped
public class AdminLoginBean implements Serializable{

    @EJB
    private AccountEJB accEJB;
    
    String email;
    String password;
    UserAccount acc;
    
    @PostConstruct
    public void init(){
        acc = new UserAccount();
    }
    public String doLogin(){
        acc = accEJB.findByEmail(email.toLowerCase());
        if (null!=acc && password.equals(acc.getPassword()) && (acc.getRoleId().getRoleId()==1 || acc.getRoleId().getRoleId()==2)){
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("ID", acc.getUserId());
            session.setAttribute("role", acc.getRoleId().getRoleId());
            return "admin/admin_homepage.xhtml?faces-redirect=true";
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Error","Error"));
            return "/adminlogin.xhtml";
        }
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

    public UserAccount getAcc() {
        return acc;
    }

    public void setAcc(UserAccount acc) {
        this.acc = acc;
    }

}
