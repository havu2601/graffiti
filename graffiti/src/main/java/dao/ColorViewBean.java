/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.ColorEJB;
import ejb.ProductEJB;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Color;
import model.Product;

/**
 *
 * @author DELL
 */
@Named(value = "colorViewBean")
@ViewScoped
public class ColorViewBean implements Serializable{

    @EJB
    private ColorEJB ejbColor;
    
    @EJB
    private ProductEJB ejbProduct;
    
    List<Color> listColor;
    List<Product> listProduct;
    
    @PostConstruct
    public void init(){
        listColor = ejbColor.findSortedColor();
    }

    public void loadListProduct(int id){
        listProduct = ejbProduct.findByColorClient(id);
    }
    
    public List<Color> getListColor() {
        return listColor;
    }

    public void setListColor(List<Color> listColor) {
        this.listColor = listColor;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
    
    
    
    
}
