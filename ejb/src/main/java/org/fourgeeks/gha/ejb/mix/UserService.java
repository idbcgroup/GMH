/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

/**
 * @author alacret
 * 
 */
@Stateless(name = "mix.UserService")
public class UserService implements UserServiceRemote {

	@PersistenceContext(name = "gha")
	PersistenceContext persistenceUnit;

	public boolean test() {
		System.out.println(persistenceUnit.name());
		return true;
	}

}
