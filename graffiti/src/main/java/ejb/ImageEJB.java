
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Image;

@Stateless
public class ImageEJB {

    @PersistenceContext
    private EntityManager em;
    
    public List<Image> findAll(){
        return em.createNamedQuery("Image.findAll").getResultList();
    }
    
    public Image findById(int id){
        return em.find(Image.class, id);
    }
    
    public List<Image> findByProduct(int id){
        return em.createNamedQuery("Image.findByProductId").setParameter("productId", id).getResultList();
    }

    public void addImage(Image image){
        em.merge(image);
    }
    
    public void updateImage(Image image){
        em.merge(image);
    }
    
    public void delete(Image image){
        em.remove(em.merge(image));
    }
    
}
