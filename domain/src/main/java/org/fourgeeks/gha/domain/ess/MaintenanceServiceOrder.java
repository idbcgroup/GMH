package org.fourgeeks.gha.domain.ess;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.ServiceOderState;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.gmh.EiaMaintenance;

/**
 * Entity implementation class for Entity: MaintenanceServiceOrder (Orden
 * servicio de mantenimiento preventivo y correctivo de equipos)
 * 
 * @author naramirez
 */
@Entity
public class MaintenanceServiceOrder extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * Número ID que identifica inequívocamente la orden de servicio de
	 * mantenimiento.
	 */
	private String serviceOrderNumber;

	/** Fecha en la que se genera la orden de servicio de mantenimiento */
	private Timestamp openingTimestamp;

	/** Proveedor responsable de mantenimiento */
	@ManyToOne
	@JoinColumn(name = "maintenanceProviderFk")
	private Bsp maintenanceProvider;

	private ServiceOderState state;

	/** El mantenimiento asociado a la orden */
	@ManyToOne
	@JoinColumn(name = "maintenanceFk")
	private EiaMaintenance maintenance;

	/** */
	public MaintenanceServiceOrder() {
		super();
	}

	/**
	 * @return the serviceOrderNumber
	 */
	public String getServiceOrderNumber() {
		return serviceOrderNumber;
	}

	/**
	 * @param serviceOrderNumber
	 *            the serviceOrderNumber to set
	 */
	public void setServiceOrderNumber(String serviceOrderNumber) {
		this.serviceOrderNumber = serviceOrderNumber;
	}

	/**
	 * @return the openingTimestamp
	 */
	public Timestamp getOpeningTimestamp() {
		return openingTimestamp;
	}

	/**
	 * @param openingTimestamp
	 *            the openingTimestamp to set
	 */
	public void setOpeningTimestamp(Timestamp openingTimestamp) {
		this.openingTimestamp = openingTimestamp;
	}

	/**
	 * @return the maintenanceProvider
	 */
	public Bsp getMaintenanceProvider() {
		return maintenanceProvider;
	}

	/**
	 * @param maintenanceProvider
	 *            the maintenanceProvider to set
	 */
	public void setMaintenanceProvider(Bsp maintenanceProvider) {
		this.maintenanceProvider = maintenanceProvider;
	}

	/**
	 * @return the state
	 */
	public ServiceOderState getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(ServiceOderState state) {
		this.state = state;
	}

	/**
	 * @return the maintenance
	 */
	public EiaMaintenance getMaintenance() {
		return maintenance;
	}

	/**
	 * @param maintenance
	 *            the maintenance to set
	 */
	public void setMaintenance(EiaMaintenance maintenance) {
		this.maintenance = maintenance;
	}

}
