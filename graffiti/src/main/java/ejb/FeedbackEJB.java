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

/**
 *
 * @author Administrator
 */
@Stateless
public class FeedbackEJB{ 
    
    @PersistenceContext 
    private EntityManager em;
    
    public Feedback findById(int id){
        return em.find(Feedback.class, id);
    }
    public List<Feedback> findAll() {
         return em.createNamedQuery("Feedback.findAll").getResultList();
    }

    public void AddNew(Feedback newFeedback) {
       em.merge(newFeedback);
    }
    
    public List<Feedback> findByUser(int id){
        return em.createNamedQuery("Feedback.findByUserId").setParameter("userId", id).getResultList();
    }


}
