package org.fourgeeks.gha.ejb.gmh;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.enu.EiaReportOrderByEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;

@Stateless(name = "gmh.EiaReportsService")
public class EiaReportsService implements EiaReportsServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(EiaReportsService.class.getName());

	/**
	 * @param query
	 *            El query
	 * @param elem
	 *            El elemento que se desea asignar a la clausula where
	 * @param campo
	 *            el camp que representa el emento dentro de la consulta
	 * @param op
	 *            la operacion comparacion
	 * @param param
	 *            el parametro contra el que se va a realziar la operacion de
	 *            comparacion
	 * @return String de la consulta con el parametro deseado en la clausula
	 *         where
	 */
	private String buildWherePred(String query, Object elem, String campo, String op, String param) {

		if (elem != null) {
			query += query.isEmpty() ? " where " : " and ";
			query += campo + " " + op + " " + param;
		}
		return query;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaReportsServiceRemote#findAllEias(java.util
	 * .List, java.util.List, org.fourgeeks.gha.domain.enu.EiaStateEnum,
	 * boolean)
	 */
	@Override
	public List<Eia> findAllEias(List<Long> facilityIds, List<Long> workAreaIds,
			EiaStateEnum eiaState, EiaReportOrderByEnum orderBy) throws GHAEJBException {

		String queryStr = "select eia from Eia eia join eia.eiaType as eiatype "
				+ " left join eia.workingArea as workingarea "
				+ " left join eia.facility as facility ";

		String whereStr = "";
		whereStr = buildWherePred(whereStr, facilityIds, "facility.id", "in", ":facilityIds");
		whereStr = buildWherePred(whereStr, workAreaIds, "workingarea.id", "in", ":workAreaIds");
		whereStr = buildWherePred(whereStr, eiaState, "eia.state", "=", ":eiaState");

		queryStr += whereStr;

		if (orderBy == EiaReportOrderByEnum.UBIC_EIATYPE)
			queryStr += " order by facility.name, workingarea.name, eiatype.name ";
		else if (orderBy == EiaReportOrderByEnum.EIATYPE_UBIC)
			queryStr += " order by eiatype.name, facility.name, workingarea.name ";
		else if (orderBy == EiaReportOrderByEnum.UBIC_EIA)
			queryStr += " order by facility.name, workingarea.name, eia.code ";
		else if (orderBy == EiaReportOrderByEnum.EIA_UBIC)
			queryStr += " order by eia.code, facility.name, workingarea.name ";

		// EJECUTANDO QUERY
		try {
			TypedQuery<Eia> query = em.createQuery(queryStr, Eia.class);
			if (facilityIds != null)
				query.setParameter("facilityIds", facilityIds);
			if (workAreaIds != null)
				query.setParameter("workAreaIds", workAreaIds);
			if (eiaState != null)
				query.setParameter("eiaState", eiaState);

			return query.getResultList();

		} catch (NoResultException ex) {
			logger.log(Level.INFO, "No results", ex);
		} catch (Exception ex) {
			logger.log(Level.INFO, "Error en metodo EJB findAllEias", ex);
			throw new GHAEJBException("Error en metodo EJB findAllEias: "
					+ ex.getCause().getMessage());
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaReportsServiceRemote#findEias(java.util.
	 * List, boolean)
	 */
	@Override
	public List<Eia> findEias(List<Long> eiaIds, EiaReportOrderByEnum orderBy)
			throws GHAEJBException {

		String queryStr = "select eia from Eia eia join eia.eiaType as eiatype "
				+ " left join eia.workingArea as workingarea "
				+ " left join eia.facility as facility ";

		String whereStr = "";
		whereStr = buildWherePred(whereStr, eiaIds, "eia.id", "in", ":eiaIds");

		queryStr += whereStr;

		if (orderBy == EiaReportOrderByEnum.UBIC_EIATYPE)
			queryStr += " order by facility.name, workingarea.name, eiatype.name ";
		else if (orderBy == EiaReportOrderByEnum.EIATYPE_UBIC)
			queryStr += " order by eiatype.name, facility.name, workingarea.name ";
		else if (orderBy == EiaReportOrderByEnum.UBIC_EIA)
			queryStr += " order by facility.name, workingarea.name, eia.code ";
		else if (orderBy == EiaReportOrderByEnum.EIA_UBIC)
			queryStr += " order by eia.code, facility.name, workingarea.name ";

		// EJECUTANDO QUERY
		try {
			TypedQuery<Eia> query = em.createQuery(queryStr, Eia.class);
			if (eiaIds != null)
				query.setParameter("eiaIds", eiaIds);

			return query.getResultList();

		} catch (NoResultException ex) {
			logger.log(Level.INFO, "No results", ex);
		} catch (Exception ex) {
			logger.log(Level.INFO, "Error en metodo EJB findEias", ex);
			throw new GHAEJBException("Error en metodo EJB findEias: " + ex.getCause().getMessage());
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaReportsServiceRemote#findEiasByEiaType(java
	 * .lang.String, java.util.List, java.util.List,
	 * org.fourgeeks.gha.domain.enu.EiaStateEnum,
	 * org.fourgeeks.gha.domain.enu.EiaReportOrderByEnum)
	 */
	@Override
	public List<Eia> findEiasByEiaType(String eiaTypeCode, List<Long> facilityIds,
			List<Long> workAreaIds, EiaStateEnum eiaState, EiaReportOrderByEnum orderBy)
			throws GHAEJBException {
		// CONSTRUYENDO QUERY
		String queryStr = "select eia from Eia eia join eia.eiaType as eiatype "
				+ " left join eia.workingArea as workingarea "
				+ " left join eia.facility as facility ";

		String whereStr = "";
		whereStr = buildWherePred(whereStr, eiaTypeCode, "eiatype.code", "=", ":eiaTypeCode");
		whereStr = buildWherePred(whereStr, facilityIds, "facility.id", "in", ":facilityIds");
		whereStr = buildWherePred(whereStr, workAreaIds, "workingarea.id", "in", ":workAreaIds");
		whereStr = buildWherePred(whereStr, eiaState, "eia.state", "=", ":eiaState");

		queryStr += whereStr;

		if (orderBy == EiaReportOrderByEnum.UBIC_EIATYPE)
			queryStr += " order by facility.name, workingarea.name, eiatype.name ";
		else if (orderBy == EiaReportOrderByEnum.EIATYPE_UBIC)
			queryStr += " order by eiatype.name, facility.name, workingarea.name ";
		else if (orderBy == EiaReportOrderByEnum.UBIC_EIA)
			queryStr += " order by facility.name, workingarea.name, eia.code ";
		else if (orderBy == EiaReportOrderByEnum.EIA_UBIC)
			queryStr += " order by eia.code, facility.name, workingarea.name ";

		// EJECUTANDO QUERY
		try {
			// creo el query y le asigno los parametros
			TypedQuery<Eia> query = em.createQuery(queryStr, Eia.class);
			if (eiaTypeCode != null)
				query.setParameter("eiaTypeCode", eiaTypeCode);
			if (facilityIds != null)
				query.setParameter("facilityIds", facilityIds);
			if (workAreaIds != null)
				query.setParameter("workAreaIds", workAreaIds);
			if (eiaState != null)
				query.setParameter("eiaState", eiaState);

			// devuelvo la lista de eia
			return query.getResultList();

		} catch (NoResultException ex) {
			logger.log(Level.INFO, "No results", ex);
		} catch (Exception ex) {
			logger.log(Level.INFO, "Error en metodo EJB findEiasByEiaType", ex);
			throw new GHAEJBException("Error en metodo EJB findEiasByEiaType: "
					+ ex.getCause().getMessage());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaReportsServiceRemote#findAllEias(java.util
	 * .List, java.util.List, org.fourgeeks.gha.domain.enu.EiaStateEnum,
	 * boolean)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Eia> findEiasByEiaTypes(List<String> eiaTypeIds, List<Long> facilityIds,
			List<Long> workAreaIds, EiaStateEnum eiaState) throws GHAEJBException {

		// CONSTRUYENDO QUERY
		String queryStr = "select eia, eiatype from Eia eia "
				+ " right join eia.eiaType as eiatype "
				+ " left join eia.workingArea as workingarea "
				+ " left join eia.facility as facility ";

		String whereStr = "";
		whereStr = buildWherePred(whereStr, eiaTypeIds, "eiatype.code", "in", ":eiaTypeIds");
		whereStr = buildWherePred(whereStr, facilityIds, "facility.id", "in", ":facilityIds");
		whereStr = buildWherePred(whereStr, workAreaIds, "workingarea.id", "in", ":workAreaIds");
		whereStr = buildWherePred(whereStr, eiaState, "eia.state", "=", ":eiaState");

		queryStr += whereStr;

		// order by
		queryStr += " order by eiatype.name, facility.name, workingarea.name ";

		// EJECUTANDO QUERY
		try {
			// creo el query y le asigno los parametros
			Query query = em.createQuery(queryStr);
			if (eiaTypeIds != null)
				query.setParameter("eiaTypeIds", eiaTypeIds);
			if (facilityIds != null)
				query.setParameter("facilityIds", facilityIds);
			if (workAreaIds != null)
				query.setParameter("workAreaIds", workAreaIds);
			if (eiaState != null)
				query.setParameter("eiaState", eiaState);

			// obtengo la lista de resultados
			List<Object> resultList = query.getResultList();

			// creo la lista de eia con los resultados devueltos por el query
			List<Eia> resultListEia = new ArrayList<Eia>();
			for (Object record : resultList) {
				Object[] values = (Object[]) record;
				Eia eia = (Eia) values[0];
				EiaType eiaType = (EiaType) values[1];

				if (eia == null)
					eia = new Eia(null, eiaType, null, null, null, null, null);
				resultListEia.add(eia);
			}

			// devuelvo la lista de eia
			return resultListEia;

		} catch (NoResultException ex) {
			logger.log(Level.INFO, "No results", ex);
		} catch (Exception ex) {
			logger.log(Level.INFO, "Error en metodo EJB findEiasByEiaTypes", ex);
			throw new GHAEJBException("Error en metodo EJB findEiasByEiaTypes: "
					+ ex.getCause().getMessage());
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaReportsServiceRemote#findEiaTypes(java.util
	 * .List)
	 */
	@Override
	public List<EiaType> findEiaTypes(List<String> eiaTypeCodes) throws GHAEJBException {
		try {
			if (eiaTypeCodes == null) {
				String stringQuery = "SELECT e from EiaType e order by e.name";
				return em.createQuery(stringQuery, EiaType.class).getResultList();
			} else {
				String queryString = "SELECT e from EiaType e where e.code in :codeList order by e.name";
				return em.createQuery(queryString, EiaType.class)
						.setParameter("codeList", eiaTypeCodes).getResultList();
			}
		} catch (NoResultException ex) {
			logger.log(Level.INFO, "No results", ex);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error en metodo EJB findEiasByEiaTypes", ex);
			throw new GHAEJBException("Error obteniendo todos los eiaTypes:"
					+ ex.getCause().getMessage());
		}

		return null;
	}

}
