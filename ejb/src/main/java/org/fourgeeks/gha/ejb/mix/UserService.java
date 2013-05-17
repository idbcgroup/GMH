/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import javax.ejb.Stateless;

import org.fourgeeks.gha.domain.mix.LegalEntity;

/**
 * @author alacret
 * 
 */
@Stateless(name = "mix.UserService")
public class UserService implements UserServiceRemote {

	// @PersistenceContext(name = "gha")
	// EntityManager em;

	public boolean test() {
		return true;
	}

	public LegalEntity test2() {
		return new LegalEntity();
	}

}
