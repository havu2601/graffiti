/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.ImportDetail;

/**
 *
 * @author Administrator
 */
@Stateless
public class ImportDetailELB {
@PersistenceContext
private EntityManager em;

    public List<ImportDetail> findAll() {
         return em.createNamedQuery("ImportDetail.findAll").getResultList();
    }

    public void AddNewDetail(ImportDetail newImportDetail) {
        em.merge(newImportDetail);
    }

    public void updateImportDetail(ImportDetail newImportDetail) {
         em.merge(newImportDetail);
    }

    
}
