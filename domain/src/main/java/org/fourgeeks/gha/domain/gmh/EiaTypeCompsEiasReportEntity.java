package org.fourgeeks.gha.domain.gmh;

import java.io.Serializable;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;

public class EiaTypeCompsEiasReportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;
	private String serial;
	private EiaStateEnum edoEquipo;
	private String facilidad;
	private String areaTrabajo;
	private String nombreEiaType;
	private String nombreComponente;

	public EiaTypeCompsEiasReportEntity() {
		// TODO Auto-generated constructor stub
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getEdoEquipo() {
		return edoEquipo != null ? edoEquipo.toString() : null;
	}

	public void setEdoEquipo(EiaStateEnum edoEquipo) {
		this.edoEquipo = edoEquipo;
	}

	public String getFacilidad() {
		return facilidad;
	}

	public void setFacilidad(String facilidad) {
		this.facilidad = facilidad;
	}

	public String getAreaTrabajo() {
		return areaTrabajo;
	}

	public void setAreaTrabajo(String areaTrabajo) {
		this.areaTrabajo = areaTrabajo;
	}

	public String getNombreEiaType() {
		return nombreEiaType;
	}

	public void setNombreEiaType(String nombreEiaType) {
		this.nombreEiaType = nombreEiaType;
	}

	public String getNombreComponente() {
		return nombreComponente;
	}

	public void setNombreComponente(String nombreComponente) {
		this.nombreComponente = nombreComponente;
	}

	public void setEdoEquipoId(Integer edoEquipoId) {
		if (edoEquipoId != null)
			this.edoEquipo = EiaStateEnum.values()[edoEquipoId];
	}

}
