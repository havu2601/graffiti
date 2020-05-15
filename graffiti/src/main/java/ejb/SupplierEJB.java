
package ejb;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Import;
import model.Supplier;

@Stateless
public class SupplierEJB implements Serializable{
    @PersistenceContext
    private EntityManager em;
    
public  List<Supplier> findAll(){
        return em.createNamedQuery("Supplier.findAll").getResultList();       
    }
    
    public void addSupplier(Supplier supplier) {
        em.persist(supplier);
    }
     public void edit(Supplier supplier){
        em.merge(supplier);
    }
   
    public void delete(Supplier supplier) {
         em.remove(em.merge(supplier));
    }

    public Supplier findById(int id) {
        return em.find(Supplier.class, id);
    }
       public void AddImport(Import newImport) {
           em.merge(newImport);
       }

   
}



    

