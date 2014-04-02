package org.fourgeeks.gha.webclient.client.UI.pmewindows.modal;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHADialog;

import com.smartgwt.client.widgets.Button;



/**
 * @author jfuentes
 *
 */
public class GHAMinorErrorDialog extends GHADialog {
	protected static Button buttonOK = new Button(GHAStrings.get("accept"));
	/**
	 * @param type
	 * @param message
	 * @param time
	 */
	public GHAMinorErrorDialog(GHAMessageType type, String message, int time) {
		super(type,time,buttonOK);
		buttonOK.addClickHandler(closeClickHandler);
		setMessage(message);
		initTypeView();
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 * @param time
	 */
	public GHAMinorErrorDialog(GHAMessageType type,String title, String message, int time){
		this(type,message,time);
		setTitle(title);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog#initTypeParams()
	 */
	@Override
	protected void initTypeView() {
		setTitle(GHAStrings.get("error"));
		setBorder("1px solid #FCD14A");
		setBackgroundColor("#FCD14A");
		setBodyColor("#FCE499");
		setIcon("../resources/icons/msgIT/error.png");
	}
}
