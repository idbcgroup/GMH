package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHAWarningDialog extends GHADialog {
	/**
	 * @param message
	 */
	public GHAWarningDialog(String message) {
		super("WARNING",false,false);
		setMessage(message);
		initByType();
	}
	/**
	 * @param message
	 * @param buttons
	 */
	public GHAWarningDialog(String message, Button... buttons) {
		super("WARNING",false,false,buttons);
		setMessage(message);
		initByType();
	}

	/**
	 * @param title
	 * @param message
	 */
	public GHAWarningDialog(String title, String message) {
		this(message);
		setTitle(title);
	}

	/**
	 * @param title
	 * @param message
	 * @param buttons
	 */
	public GHAWarningDialog(String title, String message, Button... buttons) {
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
		// Blue
		dialogType = "WARNING";
		isTimed = true;
		isModal = false;
		setTitle(GHAStrings.get("warning"));
		setBorder("1px solid #8CB1E0");
		setBackgroundColor("#8CB1E0");
		setBodyColor("#D9E3EF");
		setIcon("../resources/icons/msgIT/warn.png");
	}
}
