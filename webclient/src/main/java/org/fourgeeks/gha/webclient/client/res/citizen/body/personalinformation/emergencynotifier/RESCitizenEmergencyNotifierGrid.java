package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.emergencynotifier;

import org.fourgeeks.gha.domain.mix.PhysicalFeatures;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.google.gwt.event.logical.shared.ResizeEvent;

/**
 * @author jfuentes
 *
 */
public class RESCitizenEmergencyNotifierGrid extends GhaGrid<PhysicalFeatures>{

	/**
	 * 
	 */
	public RESCitizenEmergencyNotifierGrid() {
		super();
		setHeight("*");
		setMinHeight(GHAUiHelper.DEFAULT_PATIENT_MINISECTION_HEIGHT);
		//		setHeight(GHAUiHelper.DEFAULT_PATIENT_MINISECTION_HEIGHT);
		//		setMaxHeight(GHAUiHelper.DEFAULT_PATIENT_MINISECTION_HEIGHT);
		setBorder("1px solid #666666");

		final GHAGridField lastNamesGridField = new GHAGridField("lastNames", "Apellidos");
		final GHAGridField namesGridField = new GHAGridField("names", "Nombres");
		final GHAGridField relationshipGridField = new GHAGridField("relationship", "Relación");
		final GHAGridField phoneFromGridField = new GHAGridField("fromPhone", "Teléfono de");
		final GHAGridField countryCodeGridField = new GHAGridField("cCode", "Cod. País");
		final GHAGridField areaCodeGridField = new GHAGridField("aCode", "Cod. Área");
		final GHAGridField telephoneNumGridField = new GHAGridField("number", "No Teléfono");
		final GHAGridField contactScheduleGridField = new GHAGridField("schedule", "Horario de Contacto");
		final GHAGridField altEmailGridField = new GHAGridField("altMail", "Correo Electrónico Alterno");
		final GHAGridField activeGridField = new GHAGridField("isActive", "Activo");
		final GHAGridField dateGridField = new GHAGridField("date", "Fecha");

		setFields(lastNamesGridField,namesGridField,relationshipGridField,phoneFromGridField,
				countryCodeGridField, areaCodeGridField,telephoneNumGridField, contactScheduleGridField,
				altEmailGridField,activeGridField, dateGridField);

		//		headerMenuItems = generateHeaderMenuItems(idGridField, serialGridField,
		//				faiGridField, locationGridField, bpiGridField, statusGridField,
		//				obuGridField, roleGridField, typeFiled, brandFiled, modelFiled);
	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		setHeight("*");
		//		setHeight(GHAUiHelper.DEFAULT_PATIENT_MINISECTION_HEIGHT);
	}
}
