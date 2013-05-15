/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author alacret
 * 
 */
@Stateless(name = "mix.UserService")
public class UserService implements UserServiceRemote {

	@PersistenceContext
	// (name = "gha")
	EntityManager em;

	public boolean test() {
		System.out.println(em.toString());
		return true;
	}

}
