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
	 */

	public GHAHardErrorDialog(GHAMessageType type, String message) {
		super("ERROR-HARD",false,false,buttonOK);
		setMessage(message);
		initByType(type);
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 */
	public GHAHardErrorDialog(GHAMessageType type, String title, String message){
		this(type, message);
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
		// Red
		dialogType = type.getCode();
		isTimed = type.isTimed();
		isModal = type.isModal();
		waitingTime = type.getTime();
		setTitle(GHAStrings.get("hard-error"));
		setBorder("1px solid #FC7A7E");
		setBackgroundColor("#FC7A7E");
		setBodyColor("#FCC2C3");
		setIcon("../resources/icons/msgIT/error.png");
	}
}
