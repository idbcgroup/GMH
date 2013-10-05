package org.fourgeeks.gha.webclient.server.eia.reports;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;

public class EiaDataSource implements JRDataSource {
	private List<Eia> data;
	private Class<? extends Object> groupType;
	private int pos;

	/**
	 * @param data
	 *            Lista con los datos a mostrar enel reporte
	 */
	public EiaDataSource(List<Eia> data) {
		this.data = data;
		this.groupType = null;
		pos = -1;
	}

	/**
	 * @param data
	 *            Lista con los datos a mostrar enel reporte
	 * @param groupTypeField
	 *            String con el tipo de agrupacion si es que se van a agrupar
	 *            los datos
	 * @throws ClassNotFoundException
	 *             Debe ser el nombre de una clase existente
	 */
	public EiaDataSource(List<Eia> data, String groupTypeField)
			throws ClassNotFoundException {
		this(data);
		this.groupType = Class.forName(groupTypeField);
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		if (data != null) {
			if (field.getName().equals("groupField")) {
				if (groupType == EiaStateEnum.class) {
					EiaStateEnum state = data.get(pos).getState();
					return state != null ? state.toString() : "Sin estado";
				}
				if (groupType == Facility.class) {
					Facility facility = data.get(pos).getFacility();
					return facility != null ? facility.getName()
							: "Sin servicio/instalación";
				}
				if (groupType == WorkingArea.class) {
					WorkingArea workingArea = data.get(pos).getWorkingArea();
					return workingArea != null ? workingArea.getName()
							: "Sin area de trabajo";
				}
			}

			if (field.getName().equals("code"))
				return data.get(pos).getCode();

			if (field.getName().equals("serial"))
				return data.get(pos).getSerialNumber();

			if (field.getName().equals("eiaType")) {
				EiaType eiaType = data.get(pos).getEiaType();
				return eiaType != null ? eiaType.getName() : null;
			}

			if (field.getName().equals("brandEiaType")) {
				EiaType eiaType = data.get(pos).getEiaType();
				return eiaType != null ? eiaType.getBrand().getName() : null;
			}

			if (field.getName().equals("departamento")) {
				Obu obu = data.get(pos).getObu();
				return obu != null ? obu.getName() : null;
			}

			if (field.getName().equals("responsable")) {
				Role role = data.get(pos).getResponsibleRole();
				return role != null ? role.getName() : null;
			}
		}

		return null;
	}

	@Override
	public boolean next() throws JRException {
		pos++;
		if (data == null)
			return false;
		return (pos < data.size());
	}

}
