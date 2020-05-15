/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Import;

/**
 *
 * @author Administrator
 */
@Stateless
public class ImportEJB {


   @PersistenceContext
   private EntityManager em;
    

    public List<Import> findAll() {
        return em.createNamedQuery("Import.findAll").getResultList();
    }

    public void AddNew(Import newImport) {
        em.merge(newImport);
    }
   

    public void updateImport(Import newImport) {
        em.merge(newImport);
    }

    public Import findById(int id) {
     return em.find(Import.class, id);
    }
    
    public List<Import> findBySupplier(int id){
        return em.createNamedQuery("Import.findBySupplier").setParameter("supplierId", id).getResultList();
    }

    public void delete(Import findById) {
       em.remove(em.merge(findById));
    }

    


}
