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
import model.UserAccount;

/**
 *
 * @author havu2601
 */
@Stateless
public class AccountEJB {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<UserAccount> findAll(){
        return em.createNamedQuery("UserAccount.findAll").getResultList();
    }
    
    public UserAccount findByID(int id){
        return em.find(UserAccount.class, id);
    }
    
    public List<UserAccount> findByEmail(String email){
        return em.createQuery("select a from UserAccount a where a.userEmail like :email", UserAccount.class)
                .setParameter("email", "%" + email + "%").getResultList();
    }
    
    public void register(UserAccount acc){
        em.merge(acc);
    }
}