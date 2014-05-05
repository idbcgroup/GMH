/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author caparicio
 * 
 */
public class EiaListMaintenanceRecord extends
		GHAGridRecord<EiaPlanificationEntity> {
	private EiaPlanificationEntity entity;
	private Eia eiaEntity;
	private EiaMaintenancePlanification planificationEntity;

	/**
	 * @param eiaEntity
	 * @param planificationEntity
	 */
	public EiaListMaintenanceRecord(EiaPlanificationEntity entity) {
		this.entity = entity;
		this.eiaEntity = entity.getEia();
		this.planificationEntity = entity.getEmp();

		setEiaMaintenancePlanAttributes();
	}

	/**
	 * Method for populate the records
	 */
	private void setEiaMaintenancePlanAttributes() {
		setAttribute("id", eiaEntity.getId());
		if (eiaEntity.getSerialNumber() != null)
			setAttribute("serialNumber", eiaEntity.getSerialNumber());
		if (eiaEntity.getFixedAssetIdentifier() != null)
			setAttribute("fai", eiaEntity.getFixedAssetIdentifier());
		if (eiaEntity.getState() != null)
			setAttribute("state", eiaEntity.getState());
		if (planificationEntity == null)
			setAttribute("statusPlanification", false);
		else {
			if (planificationEntity.getPlanificationState() != null)
				setAttribute("statusPlanification",
						planificationEntity.getPlanificationState());
			else
				setAttribute("statusPlanification", false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public EiaPlanificationEntity toEntity() {
		return entity;
	}
}
