/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Feedback;
import model.RepFeedback;

/**
 *
 * @author Administrator
 */
@Stateless
public class ReplyEJB {

    @PersistenceContext 
    private EntityManager em;
    
    public List<RepFeedback> findAll() {
         return em.createNamedQuery("RepFeedback.findAll").getResultList();
    }
    
    public List<RepFeedback> findByFeedback(int id){
        return em.createNamedQuery("RepFeedback.findByFeedbackId").setParameter("feedbackId", id).getResultList();
    } 

    public void AddNew(RepFeedback obj) {
       em.merge(obj);
    }
    
    public void UpdateNew(RepFeedback obj) {
       em.merge(obj);
    }
}
