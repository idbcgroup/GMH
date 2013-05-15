/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import javax.ejb.Remote;

/**
 * @author alacret
 * 
 */
@Remote
public interface UserServiceRemote {
	public boolean test();
}
