package org.fourgeeks.gha.webclient.server.eia.reports;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.fourgeeks.gha.domain.gmh.Eia;

public class EiaDataSource implements JRDataSource {
	private List<Eia> data;
	private String groupField;
	private int pos;

	public EiaDataSource(List<Eia> data, String groupField) {
		this.data = data;
		this.groupField = groupField;
		pos = -1;
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		if (data != null) {
			if (field.getName().equals("groupField")) {
				if (groupField.equals("edoEquipo"))
					return data.get(pos).getState().toString();
				if (groupField.equals("facility"))
					return data.get(pos).getFacility().getName();
				if (groupField.equals("workingArea"))
					return data.get(pos).getWorkingArea().getName();
			}

			if (field.getName().equals("code"))
				return data.get(pos).getCode();

			if (field.getName().equals("serial"))
				return data.get(pos).getSerialNumber();

			if (field.getName().equals("eiaType"))
				return data.get(pos).getEiaType().getName();

			if (field.getName().equals("brandEiaType"))
				return data.get(pos).getEiaType().getBrand().getName();

			if (field.getName().equals("departamento"))
				return data.get(pos).getObu().getName();

			if (field.getName().equals("responsable"))
				return data.get(pos).getResponsibleRole().getName();
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
