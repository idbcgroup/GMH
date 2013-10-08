/**
 * 
 */
package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class UserRecord extends GHAGridRecord<SSOUser> {
	private SSOUser ssoUser;

	/**
	 * @param ssoUser
	 */
	public UserRecord(SSOUser ssoUser) {
		this.ssoUser = ssoUser;
		setAttribute("user", this.ssoUser.getUserName());
		// setAttribute("pass", this.ssoUser.getPassword());
		setAttribute("block", this.ssoUser.getUserLogonStatus());

		if (this.ssoUser.getBpu() != null
				&& this.ssoUser.getBpu().getCitizen() != null) {
			setAttribute("fname", this.ssoUser.getBpu().getCitizen()
					.getFirstName());
			setAttribute("sname", this.ssoUser.getBpu().getCitizen()
					.getSecondName());
			setAttribute("lname", this.ssoUser.getBpu().getCitizen()
					.getFirstLastName());
			setAttribute("slname", this.ssoUser.getBpu().getCitizen()
					.getSecondLastName());
			setAttribute("id", this.ssoUser.getBpu().getCitizen().getIdNumber());
			setAttribute("gender", this.ssoUser.getBpu().getCitizen()
					.getGender());
			setAttribute("nac", this.ssoUser.getBpu().getCitizen()
					.getNationality());
			setAttribute("bday", this.ssoUser.getBpu().getCitizen()
					.getBirthDate());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public SSOUser toEntity() {
		return this.ssoUser;
	}

}
