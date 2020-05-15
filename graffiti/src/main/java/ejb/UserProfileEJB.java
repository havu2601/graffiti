/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eclipse.persistence.tools.profiler.Profile;

/**
 *
 * @author Administrator
 */
@Stateless
public class UserProfileEJB {
@PersistenceContext
EntityManager em;

 public void updateImport(Profile newProfile) {
        em.merge(newProfile);
    }
}
