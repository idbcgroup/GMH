package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHAWarningDialog extends GHADialog {
	/**
	 * @param type
	 * @param message
	 */
	public GHAWarningDialog(GHAMessageType type, String message) {
		super("WARNING",false,false);
		setMessage(message);
		initByType(type);
	}
	/**
	 * @param type
	 * @param message
	 * @param buttons
	 */
	public GHAWarningDialog(GHAMessageType type, String message, Button... buttons) {
		super("WARNING",false,false,buttons);
		setMessage(message);
		initByType(type);
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 */
	public GHAWarningDialog(GHAMessageType type, String title, String message) {
		this(type,message);
		setTitle(title);
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 * @param buttons
	 */
	public GHAWarningDialog(GHAMessageType type, String title, String message, Button... buttons) {
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
		// Blue
		dialogType = type.getCode();
		isTimed = type.isTimed();
		waitingTime = type.getTime();
		isModal = type.isModal();
		setTitle(GHAStrings.get("warning"));
		setBorder("1px solid #8CB1E0");
		setBackgroundColor("#8CB1E0");
		setBodyColor("#D9E3EF");
		setIcon("../resources/icons/msgIT/warn.png");
	}
}
