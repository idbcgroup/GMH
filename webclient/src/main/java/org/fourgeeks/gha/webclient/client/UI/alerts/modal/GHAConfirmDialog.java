package org.fourgeeks.gha.webclient.client.UI.alerts.modal;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;



/**
 * @author jfuentes
 *
 */
public class GHAConfirmDialog extends GHADialog {
	/**
	 * @param type
	 * @param message
	 * @param time
	 * @param buttons
	 */
	public GHAConfirmDialog(GHAMessageType type, String message, int time, Button... buttons) {
		super(type,false,time, buttons);
		setMessage(message);
		initTypeView();
	}
	/**
	 * @param type
	 * @param title
	 * @param message
	 * @param time
	 * @param buttons
	 */
	public GHAConfirmDialog(GHAMessageType type, String title, String message, int time, Button... buttons){
		this(type, message, time,buttons);
		setTitle(title);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog#initTypeParams()
	 */
	@Override
	protected void initTypeView() {
		// Gray
		setTitle(GHAStrings.get("confirm"));
		setBorder("1px solid #BCBCBC");
		setBackgroundColor("#BCBCBC");
		setBodyColor("#FFFFFF");
		setIcon("../resources/icons/msgIT/ask.png");
	}
}
