/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ejb.ImageEJB;
import ejb.ProductEJB;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;
import model.Image;
import model.Product;

/**
 *
 * @author DELL
 */
@Named(value = "imageBean")
@ViewScoped
public class ImageBean implements Serializable{

    @EJB
    private ImageEJB ejbImage;
    
    @EJB
    private ProductEJB ejbProduct;
    
    List<Image> listImage;
    Product objProduct;
    Image objImage;
    String productId;
    Part image;
    
    @PostConstruct
    public void init(){
        objProduct = new Product();
        listImage = new ArrayList<>();
    }

    public void search(){
        objProduct = ejbProduct.findById(Integer.parseInt(productId));
        listImage = ejbImage.findByProduct(objProduct.getProductId());
    }
           
    public void addToList(){
        doUpload();
        objImage = new Image();
        objImage.setImagePath("assets/images/" + image.getSubmittedFileName());
        objImage.setProductId(objProduct);
        ejbImage.addImage(objImage);
        listImage = new ArrayList<>();
        listImage = ejbImage.findByProduct(objProduct.getProductId());
        objImage = new Image();
    }

    public void delete(int id){
        objImage = new Image();
        objImage = ejbImage.findById(id);
        listImage.remove(objImage);
        ejbImage.delete(objImage);
        objImage = new Image();
    }
    
    public void doUpload(){
        try {
            InputStream in = image.getInputStream();
            
            File f = new File("/Users/havu2601/Documents/graffiti/graffiti/src/main/webapp/assets/images/" + image.getSubmittedFileName());
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            
            byte[] buffer = new byte[1024];
            int length;
            
            while((length= in.read(buffer))>0){
                out.write(buffer, 0, length);
            }
            
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Image> getListImage() {
        return listImage;
    }

    public void setListImage(List<Image> listImage) {
        this.listImage = listImage;
    }

    public Image getObjImage() {
        return objImage;
    }

    public void setObjImage(Image objImage) {
        this.objImage = objImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public Product getObjProduct() {
        return objProduct;
    }

    public void setObjProduct(Product objProduct) {
        this.objProduct = objProduct;
    }
    
    
}
