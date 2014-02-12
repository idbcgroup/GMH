/**
 * 
 */
package org.fourgeeks.gha.webclient.client.user;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;

/**
 * @author emiliot
 * 
 */
public class UserUtil {
	private UserUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param ssoUser
	 * @return
	 */
	public static UserRecord toGridRecord(SSOUser ssoUser) {
		return new UserRecord(ssoUser);
	}

	/**
	 * @param ssoUsers
	 * @return
	 */
	public static List<UserRecord> toGridRecords(List<SSOUser> ssoUsers) {
		final List<UserRecord> list = new ArrayList<UserRecord>();
		for (final SSOUser ssoUser : ssoUsers) {
			list.add(new UserRecord(ssoUser));
		}

		return list;
	}
}
