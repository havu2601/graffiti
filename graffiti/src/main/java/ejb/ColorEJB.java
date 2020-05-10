
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Color;

@Stateless
public class ColorEJB {

    @PersistenceContext
    private EntityManager em;
    
    public List<Color> findAll(){
        return em.createNamedQuery("Color.findAll").getResultList();
    }
    
    public Color findById(int id){
        return em.find(Color.class, id);
    }
    
    public List<Color> findByName(String searchStr){
        return em.createNamedQuery("Color.findByColorName").setParameter("colorName", searchStr).getResultList();
    }
    
    public List<Color> findSortedColor(){
        return em.createNamedQuery("Color.findAllSort").getResultList();
    }
    
    public List<Color> findByAny(String searchStr){
        return em.createNamedQuery("Color.findByAny").setParameter("searchStr", searchStr).getResultList();
    }
    
    public void addColor(Color color){
        em.merge(color);
    }
    
    public void updateColor(Color color){
        em.merge(color);
    }
    
    public void delete(Color color){
        em.remove(em.merge(color));
    }
}
