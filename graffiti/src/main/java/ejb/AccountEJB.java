/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.sql.SQLException;
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
    
    public UserAccount findByEmail(String email)throws SQLException{
        try {
            return (UserAccount)em.createNamedQuery("UserAccount.findByUserEmail").setParameter("userEmail", email).getSingleResult();
        } catch (Exception e){
            return null;
        }
        
    }
    
    public List<UserAccount> findBy(String searchStr){
        return em.createNamedQuery("UserAccount.findBy").setParameter("searchStr", searchStr).getResultList();
    }
    public void register(UserAccount acc){
        em.merge(acc);
    }
    
    public void editProfile(UserAccount acc){
        em.merge(acc);
    }
}
