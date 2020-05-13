/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.OrderDetailEJB;
import ejb.ProductEJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Product;

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
