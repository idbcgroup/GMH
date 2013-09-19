package org.fourgeeks.gha.ejb.ess;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author alacret
 * 
 */
@Remote
public interface FunctionServiceRemote {

	/**
	 * @return get al the functions
	 */
	public List<Function> getAll() throws GHAEJBException;

}