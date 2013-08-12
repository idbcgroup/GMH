package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeSpare;

@Remote
public interface EiaTypeSpareServiceRemote {

	public EiaTypeSpare save(EiaTypeSpare eiaTypeSpare) throws EJBException;

	public List<EiaTypeSpare> find(EiaType eiaType) throws EJBException;

	public EiaTypeSpare find(long id) throws EJBException;

	public void delete(long id) throws EJBException;

	public List<EiaTypeSpare> getAll() throws EJBException;

	public EiaTypeSpare update(EiaTypeSpare entity) throws EJBException;

}
