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
import model.Category;

/**
 *
 * @author DELL
 */
@Stateless
public class CategoryEJB {

    @PersistenceContext
    private EntityManager em;
    
    public List<Category> findAll(){
        return em.createNamedQuery("Category.findAll").getResultList();
    }
    
    public Category findById(int id){
        return em.find(Category.class, id);
    }
    
    public Category findByName(String searchStr){
        return (Category) em.createNamedQuery("Category.findByCategoryName").setParameter("categoryName", searchStr).getSingleResult();
    }
    
    public void addCategory(Category category){
        em.merge(category);
    }
    
    public void updateCategory(Category category){
        em.merge(category);
    }
    
    public void delete(Category category){
        em.remove(em.merge(category));
    }
}
