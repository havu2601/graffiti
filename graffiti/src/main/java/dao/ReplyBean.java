/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.FeedbackEJB;
import ejb.ReplyEJB;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import model.Feedback;
import model.RepFeedback;
import model.UserAccount;

/**
 *
 * @author Administrator
 */
@Named(value = "replyBean")
@ViewScoped
public class ReplyBean implements Serializable{

    @EJB
    private ReplyEJB replyEJB;
    
    @EJB private FeedbackEJB feedbackEJB;
            
    List<RepFeedback> listReply;
    List<Feedback> listFeedback;
    
    RepFeedback newReply;
    Feedback newFeedback;
    
    private Date feedbackDate;
    private String repContent;
    private String userId; 

    UserAccount newUserAccount;

    @PostConstruct
    public void init() {
        getUserSession();
        newReply = new RepFeedback();
        newUserAccount = new UserAccount();
        listFeedback = feedbackEJB.findByUser(Integer.parseInt(userId));
    }

     public void show(List<RepFeedback> rs, String msg) {
        listReply = new ArrayList<>();
        if (rs.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } else {
            listReply.addAll(rs);
        }
    }
     
      public void loadFeedbackContent(int id){
         newFeedback = feedbackEJB.findById(id);
         if(replyEJB.findByFeedback(id)!=null && !replyEJB.findByFeedback(id).isEmpty()){
             repContent = replyEJB.findByFeedback(id).get(0).getRepContent();
         }else{
             repContent = "";
         }
     }
      
     public void getUserSession(){
        FacesContext req = FacesContext.getCurrentInstance();
         HttpSession session = (HttpSession) req.getExternalContext().getSession(false);
        UserBean userSession = (UserBean) session.getAttribute("userSession");
        userId = userSession.getAcc().getUserId().toString();
        newUserAccount =  new UserAccount();
        newUserAccount.setUserId(Integer.parseInt(userId));
    }
     

    public String addNewFeedback() {
       getUserSession();
        if (null == newFeedback.getFeedbackId()) {     
            feedbackDate = Date.from(Instant.now());
            newFeedback.setFeedbackDate(feedbackDate);
            newFeedback.setUserId(newUserAccount);
            feedbackEJB.AddNew(newFeedback);
            
        } else {
            newFeedback.setUserId(newUserAccount);
            feedbackEJB.AddNew(newFeedback);
        }

        return "feedback.xhtml?faces-redirect=true";
    }

    public void reset() {
        newReply = new RepFeedback();
    }

    public List<RepFeedback> getListReply() {
        return listReply;
    }

    public void setListReply(List<RepFeedback> listReply) {
        this.listReply = listReply;
    }

    public List<Feedback> getListFeedback() {
        return listFeedback;
    }

    public void setListFeedback(List<Feedback> listFeedback) {
        this.listFeedback = listFeedback;
    }

    public RepFeedback getNewReply() {
        return newReply;
    }

    public void setNewReply(RepFeedback newReply) {
        this.newReply = newReply;
    }

    public Feedback getNewFeedback() {
        return newFeedback;
    }

    public void setNewFeedback(Feedback newFeedback) {
        this.newFeedback = newFeedback;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getRepContent() {
        return repContent;
    }

    public void setRepContent(String repContent) {
        this.repContent = repContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserAccount getNewUserAccount() {
        return newUserAccount;
    }

    public void setNewUserAccount(UserAccount newUserAccount) {
        this.newUserAccount = newUserAccount;
    }
    
    
}
