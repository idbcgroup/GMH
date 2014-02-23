package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHAInformationDialog extends GHADialog {
	/**
	 * @param type
	 * @param message
	 * @param time
	 */
	public GHAInformationDialog(GHAMessageType type, String message, int time) {
		super(type,false,time);
		setMessage(message);
		initTypeView();
	}

	/**
	 * @param type
	 * @param message
	 * @param time
	 * @param buttons
	 */
	public GHAInformationDialog(GHAMessageType type, String message, int time, Button... buttons) {
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
	public GHAInformationDialog(GHAMessageType type, String title, String message, int time) {
		this(type,message,time);
		setTitle(title);
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 * @param time
	 * @param buttons
	 */
	public GHAInformationDialog(GHAMessageType type, String title, String message, int time, Button... buttons) {
		this(type,message,time,buttons);
		setTitle(title);
	}



	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog#initTypeParams()
	 */

	@Override
	protected void initTypeView() {
		setTitle(GHAStrings.get("information"));
		setBorder("1px solid #8CB1E0");
		setBackgroundColor("#8CB1E0");
		setBodyColor("#D9E3EF");
		setIcon("../resources/icons/msgIT/info.png");
	}
}
