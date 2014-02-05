package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHASayDialog extends GHADialog {
	/**
	 * @param message
	 */
	public GHASayDialog(String message) {
		super("SAY",false,false);
		setMessage(message);
		initByType();
	}
	/**
	 * @param message
	 * @param buttons 
	 */
	public GHASayDialog(String message, Button... buttons) {
		super("SAY",false,false,buttons);
		setMessage(message);
		initByType();
	}

	/**
	 * @param title 
	 * @param message
	 */
	public GHASayDialog(String title, String message) {
		this(message);
		setTitle(title);
	}

	/**
	 * @param title 
	 * @param message
	 * @param buttons 
	 */
	public GHASayDialog(String title, String message, Button... buttons) {
		this(message,buttons);
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
		dialogType = "SAY";
		isTimed = true;
		isModal = false;
		setTitle(GHAStrings.get("message"));
		setBorder("1px solid #BCBCBC");
		setBackgroundColor("#BCBCBC");
		setBodyColor("#EFEFEF");
		setIcon("../resources/icons/msgIT/say.png");
	}
}
