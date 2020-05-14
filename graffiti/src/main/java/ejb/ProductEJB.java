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
import model.Product;

/**
 *
 * @author DELL
 */
@Stateless
public class ProductEJB {

    @PersistenceContext
    private EntityManager em;
    
    public List<Product> findAll(){
        return em.createNamedQuery("Product.findAll").getResultList();
    }
    
    public Product findById(int id){
        return em.find(Product.class, id);
    }
    
    public Product findByName(String searchStr){
        return (Product) em.createNamedQuery("Product.findByProductName").setParameter("productName", searchStr).getSingleResult();
    }
    
    public List<Product> findByBrand(int id){
        return em.createNamedQuery("Product.findByBrandId").setParameter("brandId", id).getResultList();
    }
    
    public List<Product> findByCate(int id){
        return em.createNamedQuery("Product.findByCateId").setParameter("categoryId", id).getResultList();
    }
    
    public List<Product> findByAny(String searchStr){
        return em.createNamedQuery("Product.findByAny").setParameter("searchStr", searchStr).getResultList();
    }
    
    public List<Product> findBySubCat(int id){
        return em.createNamedQuery("Product.findBySubCatId").setParameter("subcatId", id).getResultList();
    }
    
    public List<Product> findByColor(int id){
        return em.createNamedQuery("Product.findByColorId").setParameter("colorId", id).getResultList();
    }
    
    
    
    public void addProduct(Product product){
        em.merge(product);
    }
    
    public void updateProduct(Product product){
        em.merge(product);
    }
    
    public void delete(Product product){
        em.remove(em.merge(product));
    }
    
    //Client
    public List<Product> findProductAscByName(){
        return em.createNamedQuery("Product.findProductAscByName").getResultList();
    }
    
    public List<Product> findProductDescByName(){
        return em.createNamedQuery("Product.findProductDescByName").getResultList();
    }
    
    public List<Product> findProductAscByPrice(){
        return em.createNamedQuery("Product.findProductAscByPrice").getResultList();
    }
    
    public List<Product> findProductDescByPrice(){
        return em.createNamedQuery("Product.findProductDescByPrice").getResultList();
    }
    
    public List<Product> findProductAscByCategory(){
        return em.createNamedQuery("Product.findProductAscByCategory").getResultList();
    }
    public List<Product> findByAnyClient(String searchStr){
        return em.createNamedQuery("Product.findByAnyClient").setParameter("searchStr", searchStr).getResultList();
    }
    
    public List<Product> findBySubCatClient(int id){
        return em.createNamedQuery("Product.findBySubCatClient").setParameter("subcatId", id).getResultList();
    }
    
    public List<Product> findByColorClient(int id){
        return em.createNamedQuery("Product.findByColorClient").setParameter("colorId", id).getResultList();
    }
    
    public List<Product> findByBrandClient(int id){
        return em.createNamedQuery("Product.findByBrandClient").setParameter("brandId", id).getResultList();
    }
    
    public List<Product> findByCateClient(int id){
        return em.createNamedQuery("Product.findByCateClient").setParameter("categoryId", id).getResultList();
    }
    
    //Find to check exist
    public List<Product> findCheckExist(String name, int bid, int cid, int capacity){
        return em.createNamedQuery("Product.findExist").setParameter("productName", name).setParameter("brandId", bid).setParameter("colorId", cid).setParameter("capacity", capacity).getResultList();
    }
}
