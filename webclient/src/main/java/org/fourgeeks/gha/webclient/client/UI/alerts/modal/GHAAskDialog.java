package org.fourgeeks.gha.webclient.client.UI.alerts.modal;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;



/**
 * @author jfuentes
 *
 */
public class GHAAskDialog extends GHADialog {

	/**
	 * @param message 
	 * @param buttons 
	 */
	public GHAAskDialog(String message, Button... buttons) {
		super("ASKYESNO",false,false,buttons);
		setMessage(message);
		initByType();
	}
	/**
	 * @param title
	 * @param message
	 * @param buttons
	 */
	public GHAAskDialog(String title, String message, Button... buttons){
		this(message, buttons);
		setTitle(title);
	}

	/**
	 * 
	 */
	private void initByType() {
		initTypeParams();
		confModalTimingSettings();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog#initTypeParams()
	 */
	@Override
	protected void initTypeParams() {
		// Gray
		dialogType = "ASKYESNO";
		isTimed = false;
		isModal = true;
		setTitle(GHAStrings.get("confirm"));
		setBorder("1px solid #BCBCBC");
		setBackgroundColor("#BCBCBC");
		setBodyColor("#EFEFEF");
		setIcon("../resources/icons/msgIT/ask.png");
	}
}
