package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHAProgressDialog extends GHADialog {
	/**
	 * @param type
	 * @param message
	 */
	public GHAProgressDialog(GHAMessageType type, String message) {
		super("PROCESSING",false,true);
		initByType(type);
		setMessage(message);
	}

	/**
	 * @param type
	 * @param message
	 * @param buttons
	 */
	public GHAProgressDialog(GHAMessageType type, String message, Button... buttons) {
		super("PROCESSING",false,true,buttons);
		initByType(type);
		setMessage(message);
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 */
	public GHAProgressDialog(GHAMessageType type, String title, String message) {
		this(type,message);
		setTitle(title);
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 * @param buttons
	 */
	public GHAProgressDialog(GHAMessageType type, String title, String message, Button... buttons) {
		this(type,message,buttons);
		setTitle(title);
	}

	/**
	 * 
	 */
	private void initByType(GHAMessageType type) {
		initTypeParams(type);
		confModalTimingSettings();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog#initTypeParams()
	 */
	@Override
	protected void initTypeParams(GHAMessageType type) {
		// Green
		dialogType = type.getCode();
		isTimed = type.isTimed();
		isModal = type.isModal();
		waitingTime = type.getTime();
		setTitle(GHAStrings.get("processsing"));
		setBorder("1px solid #AAC475");
		setBackgroundColor("#AAC475");
		setBodyColor("#D4E1BA");
		setIcon("../resources/icons/msgIT/process.png");
	}
}
