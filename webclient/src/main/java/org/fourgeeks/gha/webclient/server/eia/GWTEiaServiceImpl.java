package org.fourgeeks.gha.webclient.server.eia;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;
import org.fourgeeks.gha.webclient.client.eia.GWTEiaService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GWTEiaServiceImpl extends RemoteServiceServlet implements GWTEiaService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB(name = "gmh.EiaService")
	EiaServiceRemote eServiceRemote;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.equipment.GWTEquipmentService#save(org.fourgeeks.gha.domain.gmh.Equipment)
	 */
	@Override
	public void save(Eia equipment) {
		eServiceRemote.save(equipment);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.equipment.GWTEquipmentService#find(long)
	 */
	@Override
	public Eia find(long Id) {
		return eServiceRemote.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.equipment.GWTEquipmentService#find(org.fourgeeks.gha.domain.gmh.Equipment)
	 */
	@Override
	public List<Eia> find(Eia equipment) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.equipment.GWTEquipmentService#find(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<Eia> find(EiaType eiaType) {
		return eServiceRemote.find(eiaType);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.equipment.GWTEquipmentService#getAll()
	 */
	@Override
	public List<Eia> getAll() {
		return eServiceRemote.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.equipment.GWTEquipmentService#getAll(int, int)
	 */
	@Override
	public List<Eia> getAll(int offset, int size) {
		return eServiceRemote.getAll(offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.equipment.GWTEquipmentService#delete(long)
	 */
	@Override
	public void delete(long Id) {
		eServiceRemote.delete(Id);		
	}

}
