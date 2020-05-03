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
    private Integer id = 0;
    private String email;
    private String password;
    private UserAccount acc;

    @PostConstruct
    public void init(){
        acc = new UserAccount();
    }
    
    public String doLogin(){
        acc = accEJB.findByID(id);
        if (null!=acc && acc.getPassword().equals(password)){
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("ID", id);
            return "index.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Error","Error"));
            return "login.xhtml";
        }
    }
    public UserAccount getAcc() {
        return acc;
    }

    public void setAcc(UserAccount acc) {
        this.acc = acc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
