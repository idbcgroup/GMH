package org.fourgeeks.gha.webclient.client.UI.pmewindows.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHADefaultDialog extends GHADialog {
	/**
	 * @param type
	 * @param message
	 * @param time
	 */
	public GHADefaultDialog(GHAMessageType type, String message , int time) {
		super(type,time);
		setMessage(message);
		initTypeView();
	}
	/**
	 * @param type
	 * @param message
	 * @param time
	 * @param buttons
	 */
	public GHADefaultDialog(GHAMessageType type, String message , int time, Button... buttons) {
		super(type,time,buttons);
		setMessage(message);
		initTypeView();
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 * @param time
	 */
	public GHADefaultDialog(GHAMessageType type, String title, String message , int time) {
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
	public GHADefaultDialog(GHAMessageType type, String title, String message , int time, Button... buttons) {
		this(type, message,time,buttons);
		setTitle(title);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog#initTypeParams()
	 */
	@Override
	protected void initTypeView() {
		setTitle(GHAStrings.get("message"));
		setBorder("1px solid #BCBCBC");
		setBackgroundColor("#BCBCBC");
		setBodyColor("#EFEFEF");
		setIcon("../resources/icons/msgIT/say.png");
	}
}
