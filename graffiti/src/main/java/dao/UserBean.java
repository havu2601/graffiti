/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.AccountEJB;
import java.io.Serializable;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.UserAccount;

/**
 *
 * @author havu2601
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable{

    @EJB
    private AccountEJB accEJB;
    
    boolean isLoggedin;
    boolean isAdmin;
    String email;
    String password;
    UserAccount acc;
   
    public UserBean() {
        super();
        isLoggedin = false;
        isAdmin = false;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("userSession", this);
    }
    
    public String doLogin() throws SQLException{
        try{
            acc = accEJB.findByEmail(email.toLowerCase());
            if (null!=acc && password.equals(acc.getPassword()) && acc.getRoleId().getRoleId()!=4){
                isLoggedin = true;
                if (acc.getRoleId().getRoleId()==1 || acc.getRoleId().getRoleId()==2)
                    isAdmin = true;
                if (isAdmin){
                    return "admin/orders.xhtml?faces-redirect=true";
                } else {
                    return "index.xhtml";
                }
            }
            else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Error","Error"));
                return "login.xhtml";
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public String doLogout(){
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(true)).invalidate();
        isAdmin = false;
        isLoggedin = false;
        acc = new UserAccount();
        return "index.xhtml?faces-redirect=true";
    }

    public UserAccount getAcc() {
        return acc;
    }

    public void setAcc(UserAccount acc) {
        this.acc = acc;
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

    public boolean getIsLoggedin() {
        return isLoggedin;
    }

    public void setIsLoggedin(boolean isLoggedin) {
        this.isLoggedin = isLoggedin;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    
}
