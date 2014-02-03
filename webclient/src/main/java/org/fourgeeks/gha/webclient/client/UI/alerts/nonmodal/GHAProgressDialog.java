package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHAProgressDialog extends GHADialog {
	/**
	 * @param message
	 */
	public GHAProgressDialog(String message) {
		super("PROCESSING",false,true);
		initByType();
		setMessage(message);
	}

	/**
	 * @param message
	 * @param buttons 
	 */
	public GHAProgressDialog(String message, Button... buttons) {
		super("PROCESSING",false,true,buttons);
		initByType();
		setMessage(message);
	}

	/**
	 * @param title 
	 * @param message
	 */
	public GHAProgressDialog(String title, String message) {
		this(message);
		setTitle(title);
	}

	/**
	 * @param title 
	 * @param message
	 * @param buttons 
	 */
	public GHAProgressDialog(String title, String message, Button... buttons) {
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
		dialogType = "PROCESSING";
		isTimed = false;
		isModal = false;
		setTitle(GHAStrings.get("processsing"));
		setBorder("1px solid #AAC475");
		setBackgroundColor("#AAC475");
		setBodyColor("#D4E1BA");
		setIcon("../resources/icons/msgIT/process.png");
	}
}
