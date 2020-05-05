package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Brand;

@Stateless
public class BrandEJB {

    @PersistenceContext
    private EntityManager em;
    
    public List<Brand> findAll(){
        return em.createNamedQuery("Brand.findAll").getResultList();
    }
    
    public Brand findById(int id){
        return em.find(Brand.class, id);
    }
    
    public Brand findByName(String searchStr){
        return (Brand) em.createNamedQuery("Brand.findByBrandName").setParameter("brandName", searchStr).getSingleResult();
    }
    
    public void addBrand(Brand brand){
        em.merge(brand);
    }
    
    public void updateBrand(Brand brand){
        em.merge(brand);
    }
    
    public void delete(Brand brand){
        em.remove(em.merge(brand));
    }
}
