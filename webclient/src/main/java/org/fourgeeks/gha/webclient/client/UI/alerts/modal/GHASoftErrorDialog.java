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
public class GHASoftErrorDialog extends GHADialog {
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
	public GHASoftErrorDialog(String message) {
		super("ERROR_SOFT",false,false,buttonOK);
		setMessage(message);
		initByType();
	}

	/**
	 * @param title
	 * @param message
	 */
	public GHASoftErrorDialog(String title, String message){
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
		// Yellow
		dialogType = "ERROR_SOFT";
		isTimed = false;
		isModal = false;
		setTitle(GHAStrings.get("soft-error"));
		setBorder("1px solid #FCD14A");
		setBackgroundColor("#FCD14A");
		setBodyColor("#FCE499");
		setIcon("../resources/icons/msgIT/error.png");
	}
}
