package org.fourgeeks.gha.webclient.client.UI.alerts.modal;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;



/**
 * @author jfuentes
 *
 */
public class GHAHardErrorDialog extends GHADialog {
	static Button buttonOK = new Button(GHAStrings.get("accept"));
	{
		buttonOK.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				close();
			}
		});
	}
	/**
	 * @param type
	 * @param message
	 * @param time
	 */

	public GHAHardErrorDialog(GHAMessageType type, String message, int time) {
		super(type,false,time, buttonOK);
		setMessage(message);
		initTypeView();
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 * @param time
	 */
	public GHAHardErrorDialog(GHAMessageType type, String title, String message, int time){
		this(type, message, time);
		setTitle(title);
	}



	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog#initTypeParams()
	 */

	@Override
	protected void initTypeView() {
		setTitle(GHAStrings.get("error"));
		setBorder("1px solid #FC7A7E");
		setBackgroundColor("#FC7A7E");
		setBodyColor("#FCC2C3");
		setIcon("../resources/icons/msgIT/error.png");
	}
}
