package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHASuccessDialog extends GHADialog {

	/**
	 * @param message
	 */
	public GHASuccessDialog(String message) {
		super("SUCCESS",false,false);
		initByType();
		setMessage(message);
	}
	/**
	 * @param message
	 * @param buttons 
	 */
	public GHASuccessDialog(String message, Button... buttons) {
		super("SUCCESS",false,false,buttons);
		initByType();
		setMessage(message);
	}

	/**
	 * @param title 
	 * @param message
	 */
	public GHASuccessDialog(String title, String message) {
		this(message);
		setTitle(title);
	}

	/**
	 * @param title 
	 * @param message
	 * @param buttons 
	 */
	public GHASuccessDialog(String title, String message, Button... buttons) {
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
		// Green
		dialogType = "SUCCESS";
		isTimed = true;
		isModal = false;
		setTitle(GHAStrings.get("success"));
		setBorder("1px solid #AAC475");
		setBackgroundColor("#AAC475");
		setBodyColor("#D4E1BA");
		setIcon("../resources/icons/msgIT/check.png");
	}
}
