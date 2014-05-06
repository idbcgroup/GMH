/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification;

import org.fourgeeks.gha.domain.enu.MaintenancePlanificationState;
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
	private final EiaPlanificationEntity entity;

	private final Eia eiaEntity;
	private final EiaMaintenancePlanification planificationEntity;

	/**
	 * 
	 * @param entity
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
		if (eiaEntity.getResponsibleRole() != null)
			setAttribute("role", eiaEntity.getResponsibleRole().getName());
		if (eiaEntity.getProvider() != null)
			setAttribute("provider", eiaEntity.getMaintenanceProvider()
					.getObu().getName());
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

		if (entity.getEmp() == null) {
			EiaMaintenancePlanification eiaPlan = new EiaMaintenancePlanification();
			entity.setEmp(eiaPlan);
		}

		if (getAttributeAsBoolean("statusPlanification").booleanValue())
			entity.getEmp().setPlanificationState(
					MaintenancePlanificationState.ACTIVE);
		else
			entity.getEmp().setPlanificationState(
					MaintenancePlanificationState.INACTIVE);

		return entity;
	}
}
