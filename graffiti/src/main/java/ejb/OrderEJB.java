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
import model.Orders;

/**
 *
 * @author havu2601
 */
@Stateless
public class OrderEJB {

    @PersistenceContext
    EntityManager em;
    
    public List<Orders> findAll(){
        return em.createNamedQuery("Orders.findAll").getResultList();
    }
    
    public Orders findByID(Integer ID){
        return em.find(Orders.class, ID);
    }
    
    public List<Orders> findByUserID(String userID){
        return em.createNamedQuery("Orders.findByUserId")
                .setParameter("userId", Integer.parseInt(userID)).getResultList();
    }
    public List<Orders> findBy(String searchStr){
        return em.createNamedQuery("Orders.findBy").setParameter("searchStr", searchStr).getResultList();
    }
    public void createOrder(Orders order){
        em.merge(order);
    }
    public void updateOrder(Orders order){
        em.merge(order);
    }
    public List<Orders> getLatest(Integer id){
        return em.createNamedQuery("Orders.findLastest").setParameter("userId", id).getResultList();
    }
}
