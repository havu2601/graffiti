/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.FeedbackEJB;
import ejb.ReplyEJB;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import model.Feedback;
import model.UserAccount;
import java.time.Instant;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import model.RepFeedback;

/**
 *
 * @author Administrator
 */
@Named(value = "feedBackBean")
@ViewScoped
public class FeedBackBean implements Serializable{
    
    @EJB
    private FeedbackEJB feedbackEJB;
    
    @EJB
    private ReplyEJB replyEJB;
    
    List<Feedback> listFeedback;
    
    Feedback newFeedback;
    RepFeedback objReply;
    private Integer feedbackId;
    private Date feedbackDate;
    private String feedbackContent;
    private String userId; 
    private String repContent;
    private Date repDate;
    UserAccount newUserAccount;

    @PostConstruct
    public void init() {
        objReply = new RepFeedback();
        newFeedback = new Feedback();
        newUserAccount = new UserAccount();
        listFeedback = feedbackEJB.findAll();
        
    }

     public void show(List<Feedback> rs, String msg) {
        listFeedback = new ArrayList<>();
        if (rs.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
        } else {
            listFeedback.addAll(rs);
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
     
     public void addNewRep(){
         getUserSession();
         objReply = new RepFeedback();
         repDate = Date.from(Instant.now());
         objReply.setFeedbackId(newFeedback);
         objReply.setUserId(newUserAccount);
         objReply.setRepDate(repDate);
         objReply.setRepContent(repContent);
         replyEJB.AddNew(objReply);
         
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
        newFeedback = new Feedback();
    }

    public List<Feedback> getListFeedback() {
        return listFeedback;
    }

    public void setListFeedback(List<Feedback> listFeedback) {
        this.listFeedback = listFeedback;
    }

    public Feedback getNewFeedback() {
        return newFeedback;
    }

    public void setNewFeedback(Feedback newFeedback) {
        this.newFeedback = newFeedback;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
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

    public RepFeedback getObjReply() {
        return objReply;
    }

    public void setObjReply(RepFeedback objReply) {
        this.objReply = objReply;
    }

    public String getRepContent() {
        return repContent;
    }

    public void setRepContent(String repContent) {
        this.repContent = repContent;
    }

    public Date getRepDate() {
        return repDate;
    }

    public void setRepDate(Date repDate) {
        this.repDate = repDate;
    }
    
    
}
