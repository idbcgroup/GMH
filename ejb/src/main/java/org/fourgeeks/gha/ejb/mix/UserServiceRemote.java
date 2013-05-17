/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.mix.LegalEntity;

/**
 * @author alacret
 * 
 */
@Remote
public interface UserServiceRemote {
	public boolean test();

	public LegalEntity test2();
}
