package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHAFailureDialog extends GHADialog {
	/**
	 * @param message
	 */
	public GHAFailureDialog(String message) {
		super("FAILURE",false,false);
		setMessage(message);
		initByType();
	}
	/**
	 * @param message
	 * @param buttons
	 */
	public GHAFailureDialog(String message, Button... buttons) {
		super("FAILURE",false,false,buttons);
		setMessage(message);
		initByType();
	}

	/**
	 * @param title
	 * @param message
	 */
	public GHAFailureDialog(String title, String message) {
		this(message);
		setTitle(title);
	}

	/**
	 * @param title
	 * @param message
	 * @param buttons
	 */
	public GHAFailureDialog(String title, String message, Button... buttons) {
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
		// Yellow
		dialogType = "FAILURE";
		isTimed = false;
		isModal = false;
		setTitle(GHAStrings.get("failure"));
		setBorder("1px solid #FCD14A");
		setBackgroundColor("#FCD14A");
		setBodyColor("#FCE499");
		setIcon("../resources/icons/msgIT/warn.png");
	}
}
