package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.physicalfeatures;

import org.fourgeeks.gha.domain.mix.PhysicalFeatures;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author jfuentes
 *
 */
public class RESCitizenPhysicalFeaturesGrid extends GhaGrid<PhysicalFeatures>{

	/**
	 * 
	 */
	public RESCitizenPhysicalFeaturesGrid() {
		super();
		setEmptyMessage(GHAStrings.get("no-results-to-show"));

		final GHAGridField etGroupGridField = new GHAGridField("etGroup", "Grupo Etario");
		final GHAGridField skinColGridField = new GHAGridField("skinColor", "Color de Piel");
		final GHAGridField hairColGridField = new GHAGridField("hairColor", "Color de Cabello");
		final GHAGridField complexionGridField = new GHAGridField("complexion", "Complexiòn");
		final GHAGridField extraGridField = new GHAGridField("extraInfo", "Otras Señas Particulares");
		final GHAGridField activeGridField = new GHAGridField("isActive", "Activo");
		final GHAGridField dateGridField = new GHAGridField("date", "Fecha");

		setFields(etGroupGridField, skinColGridField, hairColGridField,
				complexionGridField, extraGridField,activeGridField,
				dateGridField);

		//		headerMenuItems = generateHeaderMenuItems(idGridField, serialGridField,
		//				faiGridField, locationGridField, bpiGridField, statusGridField,
		//				obuGridField, roleGridField, typeFiled, brandFiled, modelFiled);

		setCanHover(true);
		setShowHover(true);
		setShowHoverComponents(true);
	}

}
