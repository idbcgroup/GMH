package org.fourgeeks.gha.webclient.server.eia.reports;

import java.math.BigDecimal;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.DepreciationMethodEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
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

		if (this.data.isEmpty())
			this.data.add(new Eia());
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
			final Eia eia = data.get(pos);

			if (field.getName().equals("groupField")) {
				if (groupType == EiaStateEnum.class) {
					EiaStateEnum state = eia.getState();
					return state != null ? state.toString() : "Sin estado";
				}
				if (groupType == Facility.class) {
					Facility facility = eia.getFacility();
					return facility != null ? facility.getName()
							: "Sin servicio/instalación";
				}
				if (groupType == WorkingArea.class) {
					WorkingArea workingArea = eia.getWorkingArea();
					return workingArea != null ? workingArea.getName()
							: "Sin area de trabajo";
				}
			}

			if (field.getName().equals("eiaId"))
				return eia.getId();

			if (field.getName().equals("eiaType")) {
				EiaType eiaType = eia.getEiaType();
				return eiaType != null ? eiaType.getName() : null;
			}

			if (field.getName().equals("code"))
				return eia.getCode();

			if (field.getName().equals("serial"))
				return eia.getSerialNumber();

			if (field.getName().equals("identificador"))
				return eia.getFixedAssetIdentifier();

			if (field.getName().equals("departamento")) {
				Obu obu = eia.getObu();
				return obu != null ? obu.getName() : null;
			}

			if (field.getName().equals("responsable")) {
				Role role = eia.getResponsibleRole();
				return role != null ? role.getName() : null;
			}

			if (field.getName().equals("edoEquipo")) {
				EiaStateEnum edoEquipo = eia.getState();
				return edoEquipo != null ? edoEquipo.toString() : null;
			}

			if (field.getName().equals("fechaAceptacion"))
				return eia.getAcceptationDate();

			if (field.getName().equals("fechaCompra"))
				return eia.getPurchaseDate();

			if (field.getName().equals("fechaRecepcion"))
				return eia.getReceptionDate();

			if (field.getName().equals("fechaInstalacion"))
				return eia.getInstallationDate();

			if (field.getName().equals("proveedor")) {
				ExternalProvider prov = eia.getProvider();
				return prov != null ? prov.getInstitution().getName() : null;
			}

			if (field.getName().equals("numOrdenCompra"))
				return eia.getPurchaseOrderNumber();

			if (field.getName().equals("numFactura"))
				return eia.getPurchaseInvoiceNumber();

			if (field.getName().equals("fechaFactura"))
				return eia.getPurchaseInvoiceDate();

			if (field.getName().equals("fechaOrdenFactura"))
				return eia.getPurchaseDate();

			if (field.getName().equals("proveedorInstalacion")) {
				ExternalProvider prov = eia.getInstallationProvider();
				return prov != null ? prov.getInstitution().getName() : null;
			}

			if (field.getName().equals("areaTrabajo")) {
				WorkingArea workingArea = eia.getWorkingArea();
				return workingArea != null ? workingArea.getName() : null;
			}

			if (field.getName().equals("facilidad")) {
				Facility facility = eia.getFacility();
				return facility != null ? facility.getName() : null;
			}

			if (field.getName().equals("costoEquipo")) {
				BigDecimal cost = eia.getAdquisitionCost();
				CurrencyTypeEnum currency = eia.getAdquisitionCostCurrency();

				return cost != null && currency != null ? cost.toString() + " "
						+ currency.toString() : null;
			}

			if (field.getName().equals("fechaContabilizacion"))
				return eia.getContabilizationDate();

			if (field.getName().equals("costoLocal")) {
				BigDecimal cost = eia.getAdquisitionCostLocal();
				CurrencyTypeEnum curr = eia.getAdquisitionCostCurrencyLocal();

				return cost != null && curr != null ? cost.toString() + " "
						+ curr.toString() : null;
			}

			if (field.getName().equals("metodoDepreciacion")) {
				DepreciationMethodEnum depricMeth = eia.getDepreciationMethod();
				return depricMeth != null ? depricMeth.toString() : null;
			}

			if (field.getName().equals("fechaUltimaDepreciacion"))
				return eia.getDateLastDepreciation();

			if (field.getName().equals("costoActualLibros")) {
				BigDecimal cost = eia.getActualCost();
				CurrencyTypeEnum currency = eia.getActualCostCurrency();

				return cost != null && currency != null ? cost.toString() + " "
						+ currency.toString() : null;
			}

			if (field.getName().equals("tiempoDepreciacion")) {
				int time = eia.getDepreciationTime();
				TimePeriodEnum pot = eia.getDepreciationTimePoT();

				return pot != null ? getTimePeriodStr(time, pot) : null;
			}

			if (field.getName().equals("tiempoVida")) {
				int time = eia.getLifeTime();
				TimePeriodEnum pot = eia.getLifeTimePoT();

				return pot != null ? getTimePeriodStr(time, pot) : null;
			}

			if (field.getName().equals("proveedorMantenimiento")) {
				ExternalProvider mProv = eia.getMaintenanceProvider();
				return mProv != null ? mProv.getInstitution().getName() : null;

			}

			if (field.getName().equals("garantiaRealDesde")) {
				WarrantySinceEnum warrantySince = eia.getRealWarrantySince();
				return warrantySince != null ? warrantySince.toString() : null;
			}

			if (field.getName().equals("fechaInicioGarantiaReal"))
				return eia.getRealWarrantyBegin();

			if (field.getName().equals("duracionGarantiaReal")) {
				int time = eia.getRealWarrantyTime();
				TimePeriodEnum pot = eia.getRealWarrantyPoT();

				return pot != null ? getTimePeriodStr(time, pot) : null;
			}

			if (field.getName().equals("garantiaIntermedDesde")) {
				WarrantySinceEnum warrantySince = eia.getIntWarrantySince();
				return warrantySince != null ? warrantySince.toString() : null;
			}

			if (field.getName().equals("fechaInicioGarantiaIntermed"))
				return eia.getIntWarrantyBegin();

			if (field.getName().equals("duracionGarantiaIntermed")) {
				int time = eia.getIntWarrantyTime();
				TimePeriodEnum pot = eia.getIntWarrantyPoT();

				return pot != null ? getTimePeriodStr(time, pot) : null;
			}
		}

		return null;
	}

	private String getTimePeriodStr(int time, TimePeriodEnum timePeriod) {
		String string = timePeriod.toString();
		if (time == 1)
			string = string.substring(0, string.length() - 1);
		return time + " " + string;
	}

	@Override
	public boolean next() throws JRException {
		pos++;
		if (data == null)
			return false;
		return (pos < data.size());
	}

}
