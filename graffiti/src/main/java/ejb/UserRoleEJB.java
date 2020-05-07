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
import model.UserRole;

/**
 *
 * @author havu2601
 */
@Stateless
public class UserRoleEJB {

    @PersistenceContext
    private EntityManager em;
    
    public UserRole findByID(Integer ID){
        return em.find(UserRole.class, ID);
    }
    
    public List<UserRole> findAll(){
        return em.createNamedQuery("UserRole.findAll").getResultList();
    }
}
