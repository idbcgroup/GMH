package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaType;

@Remote
public interface EiaDamageReportServiceRemote {
	/**
	 * @param Id
	 * @return a boolean with the result of the operation
	 * @throws GHAEJBException
	 *             Delete an entity from database using its id
	 */
	public boolean delete(long Id) throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return a List with {@link EiaDamageReport} searching by EiaType
	 * @throws GHAEJBException
	 *             Find all the eias BASED on the eiaType ID
	 */
	public List<EiaDamageReport> findByEiaType(EiaType eiaType)
			throws GHAEJBException;

	/**
	 * @return a List with all the EiaDamageReport
	 * @throws GHAEJBException
	 */
	public List<EiaDamageReport> getAll() throws GHAEJBException;

	/**
	 * @param eiaDamageReport
	 * @return the persisted entity
	 * @throws GHAEJBException
	 *             Persist an EiaDamageReport to database
	 */
	public EiaDamageReport save(EiaDamageReport eiaDamageReport)
			throws GHAEJBException;

	/**
	 * @param eiaDamageReport
	 * @return the updated entity
	 * @throws GHAEJBException
	 *             Update the EiaDamageReport
	 */
	public EiaDamageReport update(EiaDamageReport eiaDamageReport)
			throws GHAEJBException;
}
