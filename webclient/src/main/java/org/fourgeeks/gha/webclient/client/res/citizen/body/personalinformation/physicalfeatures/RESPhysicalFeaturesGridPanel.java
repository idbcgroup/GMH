package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.physicalfeatures;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATopTitleLabel;

import com.smartgwt.client.types.Overflow;

/**
 * @author jfuentes
 *
 */
@Deprecated
public class RESPhysicalFeaturesGridPanel extends GHAVerticalLayout {

	private RESCitizenPhysicalFeaturesGrid featuresGrid;
	{
		featuresGrid = new RESCitizenPhysicalFeaturesGrid();
	}
	/**
	 * 
	 */
	public RESPhysicalFeaturesGridPanel() {
		super();
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_PATIENT_MINISECTION_HEIGHT);
		setMaxHeight(GHAUiHelper.DEFAULT_PATIENT_MINISECTION_HEIGHT);
		setBorder("1px solid #666666");
		setOverflow(Overflow.AUTO);
		//		setBackgroundColor("red");

		final GHATopTitleLabel title = new GHATopTitleLabel(GHAStrings.get("citizen-physical-features-title")).backgroundColored();
		addMembers(title,featuresGrid);
	}

}
