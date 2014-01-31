package org.fourgeeks.gha.webclient.client.UI.alerts.modal;

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
	 * @param message 
	 */

	public GHAHardErrorDialog(String message) {
		super("ERROR_HARD",false,false,buttonOK);
		setMessage(message);
		initByType();
	}

	/**
	 * @param title
	 * @param message
	 */
	public GHAHardErrorDialog(String title, String message){
		this(message);
		setTitle(title);
	}

	/**
	 * 
	 */
	private void initByType() {
		initTypeParams();
		confModalTimingSettings();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog#initTypeParams()
	 */
	@Override
	protected void initTypeParams() {
		// Red
		dialogType = "ERROR_HARD";
		isTimed = false;
		isModal = false;
		setTitle(GHAStrings.get("hard-error"));
		setBorder("1px solid #FC7A7E");
		setBackgroundColor("#FC7A7E");
		setBodyColor("#FCC2C3");
		setIcon("../resources/icons/msgIT/error.png");
	}
}
