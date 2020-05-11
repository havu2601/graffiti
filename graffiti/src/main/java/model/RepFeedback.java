/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author havu2601
 */
@Entity
@Table(name = "rep_feedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepFeedback.findAll", query = "SELECT r FROM RepFeedback r"),
    @NamedQuery(name = "RepFeedback.findByRepId", query = "SELECT r FROM RepFeedback r WHERE r.repId = :repId"),
    @NamedQuery(name = "RepFeedback.findByFeedbackId", query = "SELECT r FROM RepFeedback r WHERE r.feedbackId.feedbackId = :feedbackId"),
    @NamedQuery(name = "RepFeedback.findByRepDate", query = "SELECT r FROM RepFeedback r WHERE r.repDate = :repDate"),
    @NamedQuery(name = "RepFeedback.findByRepContent", query = "SELECT r FROM RepFeedback r WHERE r.repContent = :repContent")})
public class RepFeedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rep_id")
    private Integer repId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rep_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date repDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "rep_content")
    private String repContent;
    @JoinColumn(name = "feedback_id", referencedColumnName = "feedback_id")
    @ManyToOne(optional = false)
    private Feedback feedbackId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private UserAccount userId;

    public RepFeedback() {
    }

    public RepFeedback(Integer repId) {
        this.repId = repId;
    }

    public RepFeedback(Integer repId, Date repDate, String repContent) {
        this.repId = repId;
        this.repDate = repDate;
        this.repContent = repContent;
    }

    public Integer getRepId() {
        return repId;
    }

    public void setRepId(Integer repId) {
        this.repId = repId;
    }

    public Date getRepDate() {
        return repDate;
    }

    public void setRepDate(Date repDate) {
        this.repDate = repDate;
    }

    public String getRepContent() {
        return repContent;
    }

    public void setRepContent(String repContent) {
        this.repContent = repContent;
    }

    public Feedback getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Feedback feedbackId) {
        this.feedbackId = feedbackId;
    }

    public UserAccount getUserId() {
        return userId;
    }

    public void setUserId(UserAccount userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (repId != null ? repId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepFeedback)) {
            return false;
        }
        RepFeedback other = (RepFeedback) object;
        if ((this.repId == null && other.repId != null) || (this.repId != null && !this.repId.equals(other.repId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.RepFeedback[ repId=" + repId + " ]";
    }
    
}
