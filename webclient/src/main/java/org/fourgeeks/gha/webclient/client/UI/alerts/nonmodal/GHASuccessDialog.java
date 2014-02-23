package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHASuccessDialog extends GHADialog {

	/**
	 * @param type
	 * @param message
	 * @param time
	 */
	public GHASuccessDialog(GHAMessageType type, String message , int time) {
		super(type,false, time);
		setMessage(message);
		initTypeView();
	}
	/**
	 * @param type
	 * @param message
	 * @param time
	 * @param buttons
	 */
	public GHASuccessDialog(GHAMessageType type, String message , int time, Button... buttons) {
		super(type,false,time, buttons);
		setMessage(message);
		initTypeView();
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 * @param time
	 */
	public GHASuccessDialog(GHAMessageType type, String title, String message , int time) {
		this(type, message,time);
		setTitle(title);
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 * @param time
	 * @param buttons
	 */
	public GHASuccessDialog(GHAMessageType type, String title, String message , int time, Button... buttons) {
		this(type, message,time,buttons);
		setTitle(title);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog#initTypeParams()
	 */
	@Override
	protected void initTypeView() {
		setTitle(GHAStrings.get("success"));
		setBorder("1px solid #AAC475");
		setBackgroundColor("#AAC475");
		setBodyColor("#D4E1BA");
		setIcon("../resources/icons/msgIT/check.png");
	}
}
