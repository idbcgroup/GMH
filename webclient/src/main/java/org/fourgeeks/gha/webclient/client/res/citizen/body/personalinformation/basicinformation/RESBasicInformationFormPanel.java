package org.fourgeeks.gha.webclient.client.res.citizen.body.personalinformation.basicinformation;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;

import com.smartgwt.client.types.Overflow;

/**
 * @author jfuentes
 *
 */
public class RESBasicInformationFormPanel extends GHAVerticalLayout {

	private final RESCitizenBasicInformationForm citizenForm = new RESCitizenBasicInformationForm();
	//	private final RESBpuBasicInformationForm bpuForm = new RESBpuBasicInformationForm();
	//	private final RESCitizenParentBasicInformationForm parentForm = new RESCitizenParentBasicInformationForm();
	/**
	 * 
	 */
	public RESBasicInformationFormPanel() {
		super();
		setWidth100();
		setHeight(GHAUiHelper.DEFAULT_PATIENT_BASIC_INFO_HEIGHT);
		setMaxHeight(GHAUiHelper.DEFAULT_PATIENT_BASIC_INFO_HEIGHT);
		setBorder("1px solid #666666");
		setOverflow(Overflow.AUTO);
		//		setBackgroundColor("cyan");

		final GHALabel title = new GHALabel(GHAStrings.get("citizen-basic-information-title")).colored();
		addMembers(title,citizenForm/*,bpuForm, parentForm*/);
	}

}
