
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.SubCategory;


@Stateless
public class SubcategoryEJB {

    @PersistenceContext
    private EntityManager em;
    
    public List<SubCategory> findAll(){
        return em.createNamedQuery("SubCategory.findAll").getResultList();
    }
    
    public SubCategory findById(int id){
        return em.find(SubCategory.class, id);
    }
    
    public SubCategory findByName(String searchStr){
        return (SubCategory) em.createNamedQuery("SubCategory.findBySubcatName").setParameter("subcatName", searchStr).getSingleResult();
    }
    
    public void addSubCat(SubCategory sc){
        em.merge(sc);
    }
    
    public void updateSubCat(SubCategory sc){
        em.merge(sc);
    }
    
    public void delete(SubCategory sc){
        em.remove(em.merge(sc));
    }
}
