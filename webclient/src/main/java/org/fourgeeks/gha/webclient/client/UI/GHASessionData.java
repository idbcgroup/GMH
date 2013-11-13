package org.fourgeeks.gha.webclient.client.UI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunctionBpu;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.exceptions.LoginNeededException;

/**
 * @author alacret
 * 
 */
public abstract class GHASessionData {

	private static Bpu loggedUser;
	private static TreeSet<String> viewTreeSet;
	private static TreeSet<String> functionTreeSet;
	private static Map<String, String> permissionMap;

	/**
	 * @return the logged User
	 */
	public static Bpu getLoggedUser() {
		return loggedUser;
	}

	/**
	 * @return if the user is logged
	 */
	public static boolean userisLogged() {
		return !(loggedUser == null);
	}

	/**
	 * @param loggedUser
	 */
	public static void setLoggedUser(Bpu loggedUser) {
		GHASessionData.loggedUser = loggedUser;
		viewTreeSet = new TreeSet<String>();
		functionTreeSet = new TreeSet<String>();
		permissionMap = new HashMap<String, String>();
		List<AppFormViewFunctionBpu> permissions = loggedUser.getPermissions();
		for (AppFormViewFunctionBpu permission : permissions) {
			viewTreeSet.add(permission.getView().getCode());
			functionTreeSet.add(permission.getFunction().getCode());
			permissionMap.put(permission.getAppForm().getToken(), permission
					.getAppForm().getName());
		}
	}

	/**
	 * @return the permissionMap
	 * @throws LoginNeededException
	 */
	public static Map<String, String> getPermissionMap()
			throws LoginNeededException {
		if (permissionMap == null)
			throw new LoginNeededException();
		return permissionMap;
	}

	/**
	 * @param code
	 * @return if it has permission for this AppForm
	 * @throws LoginNeededException
	 */
	public static boolean hasAppFormPermission(String code)
			throws LoginNeededException {
		if (permissionMap == null)
			throw new LoginNeededException();
		return permissionMap.containsKey(code);
	}

	/**
	 * @param code
	 * @return wheter this code is present on the permissions
	 * @throws LoginNeededException
	 */
	public static boolean hasViewPermission(String code)
			throws LoginNeededException {
		if (viewTreeSet == null)
			throw new LoginNeededException();
		return viewTreeSet.contains(code);
	}

	/**
	 * @param code
	 * @return wheter this code is present on the permissions
	 * @throws LoginNeededException
	 */
	public static boolean hasFunctionPermission(String code)
			throws LoginNeededException {
		if (functionTreeSet == null)
			throw new LoginNeededException();
		return functionTreeSet.contains(code);
	}

}
