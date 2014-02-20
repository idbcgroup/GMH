package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHAFailureDialog extends GHADialog {
	/**
	 * @param type
	 * @param message
	 */
	public GHAFailureDialog(GHAMessageType type, String message) {
		super("FAILURE",false,false);
		setMessage(message);
		initByType(type);
	}
	/**
	 * @param type
	 * @param message
	 * @param buttons
	 */
	public GHAFailureDialog(GHAMessageType type,String message, Button... buttons) {
		super("FAILURE",false,false,buttons);
		setMessage(message);
		initByType(type);
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 */
	public GHAFailureDialog(GHAMessageType type,String title, String message) {
		this(type,message);
		setTitle(title);
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 * @param buttons
	 */
	public GHAFailureDialog(GHAMessageType type, String title, String message, Button... buttons) {
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
		// Yellow
		dialogType = type.getCode();
		isTimed = type.isTimed();
		isModal = type.isModal();
		waitingTime = type.getTime();
		setTitle(GHAStrings.get("failure"));
		setBorder("1px solid #FCD14A");
		setBackgroundColor("#FCD14A");
		setBodyColor("#FCE499");
		setIcon("../resources/icons/msgIT/warn.png");
	}
}
