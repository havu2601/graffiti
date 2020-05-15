/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.AccountEJB;
import ejb.UserRoleEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.UserAccount;
import model.UserRole;

/**
 *
 * @author havu2601
 */
@Named(value = "manageUserBean")
@ViewScoped
public class ManageUserBean implements Serializable{

    @EJB
    AccountEJB accEJB;
    @EJB
    UserRoleEJB roleEJB;
    private List<UserAccount> userList;
    private List<UserRole> roleList;
    private UserAccount account;
    private UserRole role;
    private String uID;
    private Integer roleID;
    private String searchStr;

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }
    
    @PostConstruct
    public void init(){
        userList = new ArrayList<>();
        userList = accEJB.findAll();
        account = new UserAccount();
        roleList = roleEJB.findAll();
        search();
    }
    
    public void findByID(){
        account = accEJB.findByID(Integer.parseInt(uID));
        roleID = account.getRoleId().getRoleId();
    }

    public void search(){
        if (null == searchStr || searchStr.isEmpty()){
            List<UserAccount> rs = new ArrayList<>();
            rs = accEJB.findAll();
            show(rs);
        } else {
            if (null!=accEJB.findBy("%"+searchStr+"%"))
            {
                List<UserAccount> p = accEJB.findBy("%"+searchStr+"%");
                show(p);
            }
        }
    }
    
    public void show(List<UserAccount> rs){
        userList = new ArrayList<>();
        if(rs.isEmpty() || rs==null){
            String msg = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
        }else{
            userList.addAll(rs);
        }
    }
    public String doSetRole(){
        role = new UserRole();
        role = roleEJB.findByID(roleID);
        account.setRoleId(role);
        accEJB.editProfile(account);
        return "userdetail.xhtml?uID="+account.getUserId()+"&&faces-redirect=true";
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
    
    public List<UserRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserRole> roleList) {
        this.roleList = roleList;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

}
