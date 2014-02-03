package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHAInformationDialog extends GHADialog {
	/**
	 * @param message
	 */
	public GHAInformationDialog(String message) {
		super("INFORMATION",false,false);
		setMessage(message);
		initByType();
	}

	/**
	 * @param message
	 * @param buttons 
	 */
	public GHAInformationDialog(String message, Button... buttons) {
		super("INFORMATION",false,false,buttons);
		setMessage(message);
		initByType();
	}

	/**
	 * @param title 
	 * @param message
	 */
	public GHAInformationDialog(String title, String message) {
		this(message);
		setTitle(title);
	}

	/**
	 * @param title 
	 * @param message
	 * @param buttons 
	 */
	public GHAInformationDialog(String title, String message, Button... buttons) {
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
		dialogType = "INFORMATION";
		isTimed = true;
		isModal = false;
		setTitle(GHAStrings.get("information"));
		setBorder("1px solid #8CB1E0");
		setBackgroundColor("#8CB1E0");
		setBodyColor("#D9E3EF");
		setIcon("../resources/icons/msgIT/info.png");
	}
}
