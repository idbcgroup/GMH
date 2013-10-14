package org.fourgeeks.gha.webclient.server.eia.reports;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;

public class EiaTypeDataSource implements JRDataSource {

	private List<EiaType> data;
	private int pos;

	/**
	 * @param data
	 *            Lista con los datos a mostrar enel reporte
	 */
	public EiaTypeDataSource(List<EiaType> data) {
		this.data = data;
		pos = -1;

		if (this.data.isEmpty()) {
			EiaType e = new EiaType();
			this.data.add(e);
		}
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		if (data != null) {
			final EiaType eiaType = data.get(pos);

			if (field.getName().equals("code"))
				return eiaType.getCode();

			if (field.getName().equals("eiaType"))
				return eiaType.getName();

			if (field.getName().equals("marca")) {
				Brand brand = eiaType.getBrand();
				return brand != null ? brand.getName() : null;
			}

			if (field.getName().equals("modelo"))
				return eiaType.getModel();

			if (field.getName().equals("fabricante")) {
				Brand brand = eiaType.getBrand();
				return brand != null ? brand.getManufacturer() : null;
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
