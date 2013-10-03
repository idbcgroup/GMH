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
	private UserUtil(){
		throw new UnsupportedOperationException("Esta clase no debe ser instanciada");
	}
	
	public static UserRecord toGridRecord(SSOUser ssoUser){
		return new UserRecord(ssoUser);
	}
	
	public static List<UserRecord> toGridRecords(List<SSOUser> ssoUsers){
		List<UserRecord> list = new ArrayList<UserRecord>();
		for(SSOUser ssoUser : ssoUsers){
			list.add(new UserRecord(ssoUser));
		}
		
		return list;
	}
}
