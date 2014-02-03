package org.fourgeeks.gha.webclient.client.UI.alerts;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author jfuentes
 * 
 */
public abstract class GHADialog extends Dialog implements ResizeHandler {
	/**
	 * @author jfuentes
	 * 
	 */

	//Internal Measures
	private static final int DEFAULT_NOTIFICATION_WIDTH = 280;
	private static final int DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT = 140;
	private static final int DEFAULT_NOTIFICATION_BUTTONS_HEIGHT = 160;

	private final int HEADER_HEIGHT = 20;
	private final int FOOTER_HEIGHT = 15;
	private final int RIGHT_MARGIN = 40;
	private final int BORDER_SEPARATION = 7;

	protected boolean hasButtons;
	protected String dialogType;
	protected boolean isModal;
	protected boolean isTimed;
	protected int openedPositionOffset = 0;

	private final int DEFAULT_WAITING_TIME = 4000;
	private final Timer waiter = new Timer() {
		@Override
		public void run() {
			close();
		}
	};
	protected final int DEFAULT_ANIMATION_TIME = 400;

	/**
	 * 
	 */
	public GHADialog(){}

	/**
	 * Creates a GHADialog without buttons, with the specifying parameters.
	 * 
	 * @param type
	 * @param hasCloseButton
	 * @param canMinimize
	 */
	public GHADialog(String type, boolean hasCloseButton,
			boolean canMinimize) {
		super();

		GHAUiHelper.addGHAResizeHandler(this);
		hasButtons = false;

		initialize();
		setOriginalStyle();
		initHeaderControls(hasCloseButton, canMinimize);
		initFooterControls();

		// ---Handlers
		addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClickEvent event) {
				close();
			}
		});
	}

	/**
	 * Creates a GHADialog with buttons, with the specifying parameters.
	 * 
	 * @param type
	 * @param hasCloseButton
	 * @param canMinimize
	 * @param buttons
	 */
	public GHADialog(String type, boolean hasCloseButton,
			boolean canMinimize, Button... buttons) {
		super();

		GHAUiHelper.addGHAResizeHandler(this);
		hasButtons = true;

		initialize();
		setOriginalStyle();
		initHeaderControls(hasCloseButton, canMinimize);
		initFooterControls();

		// ---Buttons
		if (hasButtons) {
			setButtons(buttons);
		}
		// ---Handlers
		addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClickEvent event) {
				close();
			}
		});
	}

	/**
	 * Closes and destroy the window
	 */
	public void close() {
		if(!dialogType.equals("CONFIRMATION") && !dialogType.equals("ASKYESNO"))
			GHAAlertManager.removeOpenMessageFromCounter();

		GHAAlertManager.addOpenMessageOffset(-1*(getHeight()));

		animateRect(null, Window.getClientHeight() - openedPositionOffset, null, null,
				new AnimationCallback() {
			@Override
			public void execute(boolean earlyFinish) {
				hide();
				destroy();
			}
		}, DEFAULT_ANIMATION_TIME);
	}

	/**
	 * 
	 */
	protected void confModalTimingSettings() {
		// ---Modal & Timing Conf.
		if (isModal) {
			setIsModal(true);
			setShowModalMask(true);
			setModalMaskOpacity(40);
		} else if (!hasButtons && !dialogType.equals("PROCESSING")) {
			if (isTimed) {
				waiter.schedule(DEFAULT_WAITING_TIME);
			}
			addMouseOverHandler(new MouseOverHandler() {
				@Override
				public void onMouseOver(MouseOverEvent event) {
					waiter.cancel();
				}
			});
			addMouseOutHandler(new MouseOutHandler() {
				@Override
				public void onMouseOut(MouseOutEvent event) {
					waiter.schedule(DEFAULT_WAITING_TIME - 1000);
				}
			});
		}
	}

	/**
	 * @return The Dialog Type.
	 */
	public String getDialogType() {
		return dialogType;
	}

	/**
	 * 
	 */
	private void initFooterControls() {
		// ---Foooter controls
		setShowFooter(true);
		Label function = new Label("Función");
		function.setStyleName("windowFooterText");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		// Label date = new
		// Label(DateFormat.getDateInstance(DateFormat.TIMEZONE_FIELD).format(new
		// Date()));
		Label date = new Label(sdf.format(new Date()));
		date.setWidth(120);
		date.setStyleName("windowFooterText");
		setFooterControls(function, new LayoutSpacer(), date);
		setFooterHeight(FOOTER_HEIGHT);
	}

	/**
	 * @param hasCloseButton
	 * @param canMinimize
	 */
	private void initHeaderControls(boolean hasCloseButton, boolean canMinimize) {
		// ---Header Controls
		setShowHeaderIcon(true);
		setHeaderIcon("../resources/icons/favicon.ico");
		setShowMinimizeButton(canMinimize);
		setShowCloseButton(hasCloseButton);
		setShowMaximizeButton(false);
	}

	/**
	 * Set the neccesary parameters for the dialog to work
	 */
	private void initialize() {
		// ---Init
		setAutoSize(false);
		setAutoCenter(false);
		setWidth(DEFAULT_NOTIFICATION_WIDTH);
		Layout bodyAC = new Layout();
		if (hasButtons) {
			setHeight(DEFAULT_NOTIFICATION_BUTTONS_HEIGHT);
			setMaxHeight(DEFAULT_NOTIFICATION_BUTTONS_HEIGHT);
			bodyAC.setHeight(DEFAULT_NOTIFICATION_BUTTONS_HEIGHT - (HEADER_HEIGHT + FOOTER_HEIGHT));

		}else{
			setHeight(DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT);
			setMaxHeight(DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT);
			bodyAC.setHeight(DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT - (HEADER_HEIGHT + FOOTER_HEIGHT));
		}
		changeAutoChildDefaults("body", bodyAC);
		setAnimateTime(DEFAULT_ANIMATION_TIME);

		//		setOverflow(Overflow.SCROLL);
	}

	protected abstract void initTypeParams();

	@Override
	public void onResize(ResizeEvent event) {
		if (isVisible()) {
			if (hasButtons) {
				setLeft(Window.getClientWidth() - (getWidth() + RIGHT_MARGIN));
				setTop(Window.getClientHeight() - (getHeight() + BORDER_SEPARATION + openedPositionOffset));
			} else {
				setLeft(Window.getClientWidth() - (getWidth() + RIGHT_MARGIN));
				setTop(Window.getClientHeight()	- (getHeight() + BORDER_SEPARATION + openedPositionOffset));
			}
		}
	}

	/**
	 * @param dialogType The type of dialog to set
	 */
	public void setDialogType(String dialogType) {
		this.dialogType = dialogType;
	}


	/**
	 * 
	 */
	private void setOriginalStyle() {
		setHeaderStyle("dialogHeaderStyle");
		setStyleName("dialogStyle");
		setBodyStyle("dialogBodyStyle");
	}

	@Override
	public void show() {
		super.show();
		GHAAlertManager.addOpenMessageToCounter();
		openedPositionOffset = GHAAlertManager.getOpenMessagesOffset();

		setLeft(Window.getClientWidth() - (getWidth() + RIGHT_MARGIN));
		setTop(Window.getClientHeight() - openedPositionOffset);

		GHAAlertManager.addOpenMessageOffset(getHeight()+ BORDER_SEPARATION);

		if (hasButtons) {
			animateRect(null, Window.getClientHeight() - (DEFAULT_NOTIFICATION_BUTTONS_HEIGHT + BORDER_SEPARATION + openedPositionOffset),null, null);
		} else {
			animateRect(null, Window.getClientHeight() - (DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT + BORDER_SEPARATION + openedPositionOffset),null, null);
		}
	}
}
