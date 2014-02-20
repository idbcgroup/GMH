package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHANewMessageDialog extends GHADialog {
	/**
	 * @param type
	 * @param message
	 */
	public GHANewMessageDialog(GHAMessageType type, String message) {
		super("NEW_MESSAGE",false,false);
		setMessage(message);
		initByType(type);
	}
	/**
	 * @param type
	 * @param message
	 * @param buttons
	 */
	public GHANewMessageDialog(GHAMessageType type, String message, Button... buttons) {
		super("NEW_MESSAGE",false,false,buttons);
		setMessage(message);
		initByType(type);
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 */
	public GHANewMessageDialog(GHAMessageType type, String title, String message) {
		this(type,message);
		setTitle(title);
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 * @param buttons
	 */
	public GHANewMessageDialog(GHAMessageType type, String title, String message, Button... buttons) {
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
		// Gray
		dialogType = type.getCode();
		isTimed = type.isTimed();
		isModal = type.isModal();
		waitingTime = type.getTime();
		setTitle(GHAStrings.get("new-message"));
		setBorder("1px solid #BCBCBC");
		setBackgroundColor("#BCBCBC");
		setBodyColor("#EFEFEF");
		setIcon("../resources/icons/msgIT/newmsg.png");
	}
}
