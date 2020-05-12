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
    private boolean isEmpty;

    public ShoppingCart() {
        super();
        itemInCart = new ArrayList<>();
        productEJB = new ProductEJB();
        detailEJB = new OrderDetailEJB();
        isEmpty = true;
    }
    
    public String addToCart(int productId){
//        Map<String, String> params = FacesContext.getCurrentInstance()
//				.getExternalContext().getRequestParameterMap();
//        Integer productId = Integer.parseInt(params.get("productId"));
        CartItem item = new CartItem();
        Product product = productEJB.findById(productId);
        item.setProduct(product);
        item.setQuantity(1);
        itemInCart.add(item);
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
    
}
