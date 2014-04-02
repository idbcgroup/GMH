package org.fourgeeks.gha.webclient.client.UI.pmewindows.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHADialog;

/**
 * @author jfuentes
 *
 */
public class GHAValidationErrorDialog extends GHADialog {
	/**
	 * @param type
	 * @param message
	 * @param time
	 */
	public GHAValidationErrorDialog(GHAMessageType type, String message , int time) {
		super(type,time);
		setMessage(message);
		initTypeView();
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 * @param time
	 */
	public GHAValidationErrorDialog(GHAMessageType type,String title, String message , int time) {
		this(type,message,time);
		setTitle(title);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog#initTypeParams()
	 */
	@Override
	public void initTypeView() {
		// Yellow
		setTitle(GHAStrings.get("failure"));
		setBorder("1px solid #FCD14A");
		setBackgroundColor("#FCD14A");
		setBodyColor("#FCE499");
		setIcon("../resources/icons/msgIT/warn.png");
	}
}
