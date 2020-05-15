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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    @EJB
    OrderDetailEJB detailEJB;
    
    @EJB
    AccountEJB ejbAccount;
    
    private Orders order;
    private List<Orders> list;
    private String oID;
    private String uID;
    private String searchStr;
    private List<OrderDetail> listDetail;
    private List<String> status;
    private String orderStatus;
    ShoppingCart cart = new ShoppingCart();

    
    @PostConstruct
    public void init(){
        order = new Orders();
        status = new ArrayList<>();
        status.add("Pending");
        status.add("On Delivery");
        status.add("Complete");
        status.add("Denined");
        search();
    }
    public void search(){
        if (null==searchStr || searchStr.isEmpty()){
            List<Orders> rs = ejb.findAll();
            show(rs);
        } else {
            if (null!=ejb.findBy("%"+searchStr+"%")){
                List<Orders> p = ejb.findBy("%"+searchStr+"%");
                show(p);
            }
        }
    }
    
    public void show(List<Orders> rs){
        list = new ArrayList<>();
        if (rs==null || rs.isEmpty()){
            String msg = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
        }else{
            list.addAll(rs);
        }
    }
    
    public String setStatus(){
        order = ejb.findByID(Integer.parseInt(oID));
        order.setStatus(orderStatus);
        ejb.updateOrder(order);
        return null;
    }
    public void getByUID(){
        list = new ArrayList<>();
        list = ejb.findByUserID(uID);
    }
    
    public void getDetail(){
        listDetail = new ArrayList<>();
        listDetail = detailEJB.findDetail(oID);
        order = ejb.findByID(Integer.parseInt(oID));
        orderStatus = order.getStatus();
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

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
}
