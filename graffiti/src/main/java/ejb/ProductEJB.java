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
    
    public Product findByBrand(String searchStr){
        return (Product) em.createNamedQuery("Product.findByBrandId").setParameter("brandId", searchStr).getSingleResult();
    }
    
    public List<Product> findByAny(String searchStr){
        return em.createNamedQuery("Product.findByAny").setParameter("searchStr", searchStr).getResultList();
    }
    
    public Product findBySubCat(String searchStr){
        return (Product) em.createNamedQuery("Product.findBySubCatId").setParameter("subcatId", searchStr).getSingleResult();
    }
    
    public Product findByColor(String searchStr){
        return (Product) em.createNamedQuery("Product.findByColorId").setParameter("colorId", searchStr).getSingleResult();
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
    
}
