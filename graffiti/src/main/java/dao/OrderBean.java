/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.AccountEJB;
import ejb.OrderDetailEJB;
import ejb.OrderEJB;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.OrderDetail;
import model.Orders;
import model.UserAccount;

/**
 *
 * @author havu2601
 */
@Named(value = "orderBean")
@ViewScoped
public class OrderBean implements Serializable{

    @EJB
    OrderEJB ejb;
    @EJB
    OrderDetailEJB detailEJB;
    
    @EJB
    AccountEJB ejbAccount;
    
    private Orders order;
    private List<Orders> list;
    private String oID;
    private List<OrderDetail> listDetail;
    
    @PostConstruct
    public void init(){
        order = new Orders();
        list = new ArrayList<>();
        list = ejb.findAll();
    }
    
    public void getDetail(){
        listDetail = new ArrayList<>();
        listDetail = detailEJB.findDetail(oID);
        order = ejb.findByID(Integer.parseInt(oID));
    }

    public String placeOrder(Integer id){
        UserAccount user = new UserAccount();
        user.setUserId(id);
        ShoppingCart cart = new ShoppingCart();
        Date date = Date.from(Instant.now());
        order = new Orders();
        order.setUserId(user);
        order.setOrderDate(date);
        order.setStatus("Paid");
        ejb.createOrder(order);
        order =  ejb.getLatest(id).get(0);
        for (CartItem item : cart.getItemInCart()) {
            OrderDetail detail = new OrderDetail();
            detail.setOrderId(order);
            detail.setProductId(item.getProduct());
            detail.setProductQty(item.getQuantity());
            detailEJB.createOrderDetail(detail);
        }
        return null;
    }
    public String getoID() {
        return oID;
    }

    public void setoID(String oID) {
        this.oID = oID;
    }


    public List<OrderDetail> getListDetail() {
        return listDetail;
    }

    public void setListDetail(List<OrderDetail> listDetail) {
        this.listDetail = listDetail;
    }
    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public List<Orders> getList() {
        return list;
    }

    public void setList(List<Orders> list) {
        this.list = list;
    }

}
