/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.AccountEJB;
import ejb.OrderDetailEJB;
import ejb.OrderEJB;
import ejb.ProductEJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.OrderDetail;
import model.Orders;
import model.Product;
import model.UserAccount;

/**
 *
 * @author havu2601
 */
@Named(value = "shoppingCart")
@SessionScoped
public class ShoppingCart implements Serializable {
    @EJB
    ProductEJB productEJB;
    @EJB
    OrderDetailEJB detailEJB;
    @EJB
    OrderEJB ejb;
    @EJB
    AccountEJB accEJB;
    private List<CartItem> itemInCart;
    private List<String> payment;
    private boolean isEmpty;
    private double total;
    private int cartQuantity;

    public ShoppingCart() {
        super();
        itemInCart = new ArrayList<>();
        productEJB = new ProductEJB();
        detailEJB = new OrderDetailEJB();
        ejb = new OrderEJB();
        accEJB = new AccountEJB();
        payment = new ArrayList<>();
        payment.add("COD");
        isEmpty = true;
        total = 0;
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("shoppingCart", this);
    }
    
    public String addToCart(Integer productId){
        CartItem item = new CartItem();
        Product product = productEJB.findById(productId);
        boolean isInCart = false;
        int i = 0;
        int size = itemInCart.size();
        while (i<size && !isInCart){
            if (itemInCart.get(i).getProduct().getProductId()==productId){
                isInCart = true;
            }else {
                i++;
            }
        }
        if (isInCart){
            itemInCart.get(i).setQuantity(itemInCart.get(i).getQuantity()+1);
        }
        else{
            item.setProduct(product);
            item.setQuantity(1);
            itemInCart.add(item);
        }
        isEmpty = false;
        return "product_detail.xhtml?pid="+productId+"&&faces-redirect=true";
    }
    
    public String reduce(CartItem item){
        item.setQuantity(item.getQuantity()-1);
        getTotal();
        if (item.getQuantity()<1){
            remove(item);
        }
        return null;
    }
    
    public String increase(CartItem item){
        item.setQuantity(item.getQuantity()+1);
        getTotal();
        return null;
    }
    public String remove(CartItem item){
        itemInCart.remove(item);
        total -= item.getQuantity()*item.getProduct().getProductPrice();
        return null;
    }
    
    public String placeOrder(Integer userId){
        UserAccount user = new UserAccount();
        user = accEJB.findByID(userId);
        Date date = Date.from(Instant.now());
        Orders order = new Orders();
        order.setUserId(user);
        order.setOrderDate(date);
        order.setStatus("Paid");
        ejb.createOrder(order);
        order =  ejb.getLatest(user.getUserId()).get(0);
        for (CartItem cartItem : itemInCart) {
            OrderDetail detail = new OrderDetail();
            detail.setOrderId(order);
            detail.setProductId(cartItem.getProduct());
            detail.setProductQty(cartItem.getQuantity());
            detailEJB.createOrderDetail(detail);
        }
        resetCart();
        return null;
    }
    
    public void resetCart(){
        itemInCart = new ArrayList<>();
        isEmpty = true;
        cartQuantity = 0;
        total = 0;
    }
    public List<CartItem> getItemInCart() {
        return itemInCart;
    }

    public void setItemInCart(List<CartItem> itemInCart) {
        this.itemInCart = itemInCart;
    }

    public boolean isIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public double getTotal() {
        total = 0;
        for (CartItem item : itemInCart) {
            total += item.getQuantity()*item.getProduct().getProductPrice();
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<String> getPayment() {
        return payment;
    }

    public void setPayment(List<String> payment) {
        this.payment = payment;
    }

    public int getCartQuantity() {
        cartQuantity = 0;
        for (CartItem item : itemInCart) {
            cartQuantity += item.getQuantity();
        }
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }   
}