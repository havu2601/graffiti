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
    
    public List<Orders> findByID(Integer userID){
        return em.createQuery("Select o from Orders where o.user_id = :id")
                .setParameter("id", userID).getResultList();
    }
    
    public List<OrderDetail> findDetail(Integer orderID){
        return em.createQuery("Select detail from OrderDetail where detail.order_id = :id")
                .setParameter("id", orderID).getResultList();
    }
    
    public void createOrder(Orders order){
        em.merge(order);
    }
    
    public void createOrderDetail(OrderDetail detail){
        em.merge(detail);
    }
    
    public void updateOrder(Orders order){
        em.merge(order);
    }
    
    public void updateDetail(OrderDetail detail){
        em.merge(detail);
    }
    
    public void deleteDetail(OrderDetail detail){
        em.remove(em.merge(detail));
    }

}
