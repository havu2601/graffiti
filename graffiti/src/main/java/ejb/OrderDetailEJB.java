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
import model.OrderDetail;

/**
 *
 * @author havu2601
 */
@Stateless
public class OrderDetailEJB {

    @PersistenceContext
    EntityManager em;
    
    
    public List<OrderDetail> findDetail(String orderID){
        return em.createQuery("SELECT o FROM OrderDetail o WHERE o.orderId.orderId = :id")
                .setParameter("id", Integer.parseInt(orderID)).getResultList();
    }
    
    public void createOrderDetail(OrderDetail detail){
        em.merge(detail);
    }
    
    
    public void updateDetail(OrderDetail detail){
        em.merge(detail);
    }
    
    public void deleteDetail(OrderDetail detail){
        em.remove(em.merge(detail));
    }
}
