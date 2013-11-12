package org.fourgeeks.gha.ejb.ess;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunction;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author alacret
 * 
 */
@Remote
public interface AppFormViewFunctionServiceRemote {

	/**
	 * @return get all the functions
	 * @throws GHAEJBException
	 */
	public List<AppFormViewFunction> getAll() throws GHAEJBException;

}