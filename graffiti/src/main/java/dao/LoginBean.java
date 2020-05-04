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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.UserAccount;

/**
 *
 * @author havu2601
 */
@Named(value="loginBean")
@ViewScoped
public class LoginBean implements Serializable{
    @EJB
    private AccountEJB accEJB;
    protected String email;
    protected String password;
    
    
    @PostConstruct
    public void init(){
    }
    public String doLogin(){
        UserAccount acc = accEJB.findByEmail(email);
        if (null!=acc && acc.getPassword().equals(password)){
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("ID", acc.getUserId());
            return "index.xhtml";
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Error","Error"));
            return "login.xhtml";
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

    
        
}
