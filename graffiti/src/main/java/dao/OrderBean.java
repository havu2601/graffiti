/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.OrderEJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.OrderDetail;
import model.Orders;

/**
 *
 * @author havu2601
 */
@Named(value = "orderBean")
@ViewScoped
public class OrderBean implements Serializable{

    @EJB
    OrderEJB ejb;
    private Orders order;
    private List<Orders> list;
    private String orderID;
    private List<OrderDetail> listDetail;
    @PostConstruct
    public void init(){
        order = new Orders();
    }

    public void show(){
        list = new ArrayList<>();
        list = ejb.findAll();
    }
    
    public void getDetail(){
        listDetail = ejb.findDetail(Integer.parseInt(orderID));
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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
