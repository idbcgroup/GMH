package org.fourgeeks.gha.webclient.client.UI.pmewindows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * GHADialog: Superclase que hace Ventanas emergentes GHA.
 * Se crean dependiendo del tipo y de otros parametros.
 * Estos mensajes son siempre leidos de la base de datos. Nunca creados por los usuarios.
 * 
 * @author jfuentes.
 */
public abstract class GHADialog extends Dialog implements ResizeHandler,
Window.ScrollHandler {

	//---------------- Medidas Internas----------------------
	//NOTA: las vntanas se autoajustan al contenido, asi se intente lo contrario.
	/* Ancho estandar de la ventana*/
	private static final int DEFAULT_NOTIFICATION_WIDTH = 280;
	/* Alto estandar de las ventanas sin botones.*/
	private static final int DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT = 140;
	/* Ancho estandar de la ventana con botones.*/
	private static final int DEFAULT_NOTIFICATION_BUTTONS_HEIGHT = 160;
	/* Alto estandar del header de la ventana*/
	private final int HEADER_HEIGHT = 25;
	/* Ancho estandar del footer de la ventana*/
	private final int FOOTER_HEIGHT = 15;
	/* Espacio entre las ventanas y el lado derecho de la pantalla*/
	private final int RIGHT_MARGIN = 30;
	/* Espacio entre las ventanas y el lado derecho de la pantalla*/
	private final int BORDER_SEPARATION = 8;
	/* Tiempo que tardan las ventanas en mostrarse y ocultarse*/
	protected final int DEFAULT_ANIMATION_TIME = 300;

	//----------------Variables Internas-----------------------
	/* Variable para la altura actual de la ventana */
	protected int dialogHeight;
	/* Variable para el Tipo de ventana */
	protected String dialogType;
	/* Variable para La posicion en la lista de mensajes de esta ventana */
	protected int openedPosition = -1;
	/* Variable para el tiempo que dura la ventana abierta */
	protected int waitingTime = -1;
	/* Timer cierra la ventana despues de -waitingTime- Segundos. */
	private final Timer waiter = new Timer() {
		@Override
		public void run() {
			close();
		}
	};

	/* Tiene botones? */
	protected boolean hasButtons;
	/* Tiene botones? */
	protected boolean isModal;
	/* Se oculta automaticamente? */
	protected boolean isTimed;

	/* Handler para el cerrado de la ventana */
	protected ClickHandler closeClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			close();
		}
	};

	/**
	 * Crea una Ventana Emergente sin botones con los parametros especificados.
	 * 
	 * @param type : el tipo de ventana a utilizar
	 * @param timeShowing : el tiempo que dura mostrandose
	 */
	public GHADialog(GHAMessageType type, int timeShowing) {
		super();

		hasButtons = false;

		initHandlers();
		initHeaderControls();
		initFooterControls();
		initialize();
		setOriginalStyle();
		initByType(type, timeShowing);

	}

	/**
	 * Creates a GHADialog with buttons, with the specifying parameters.
	 * 
	 * @param type
	 * @param timeShowing
	 * @param buttons
	 */
	public GHADialog(GHAMessageType type, int timeShowing, Button... buttons) {
		super();
		hasButtons = true;

		initHandlers();
		initHeaderControls();
		initFooterControls();
		initialize();
		setOriginalStyle();
		initByType(type, timeShowing);

		// ---Buttons
		setButtons(buttons);
	}

	/**
	 * Cierra y destruye la ventana.
	 */
	@Override
	public void close() {
		Window.alert("CLOSE: openMessageCounter:"+GHAErrorMessageProcessor.getOpenMessagesCounter()+"\nOpenedPosition:"+openedPosition);
		if (openedPosition >= 0) {
			GHAErrorMessageProcessor.removeOpenMessageFromCounter();
			GHAErrorMessageProcessor.toggleMessagePosition(openedPosition);
			GHAErrorMessageProcessor.messageClosedActions();
		}
		animateRect(null, Window.getScrollTop() + Window.getClientHeight(),
				null, null, new AnimationCallback() {
			@Override
			public void execute(boolean earlyFinish) {
				hide();
				destroy();
			}
		}, DEFAULT_ANIMATION_TIME);

	}

	/**
	 *  Metodo que configura las opciones de tiempo y modalidad de la ventana.
	 */
	protected void confModalTimingSettings() {
		// ---Modal & Timing Conf.
		if (isModal) {
			setIsModal(true);
			setShowModalMask(true);
			setModalMaskOpacity(15);
		} else if (!hasButtons && !dialogType.equals("VEM-ADVANCE")) {
			if (isTimed) {
				waiter.schedule(waitingTime);
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
					waiter.schedule(waitingTime - 1000);
				}
			});
		}
	}

	/**
	 * Devuelve el tipo de ventana a mostrar.
	 * @return El tipo de ventana
	 */
	public String getDialogType() {
		return dialogType;
	}

	/**
	 * Funcion que hace uso de los metodos de configuracion de acuerdo al tipo de ventana.
	 */
	private void initByType(GHAMessageType type, int time) {
		initTypeParameters(type, time);
		confModalTimingSettings();
	}

	/**
	 * 	Funcion que inicializa los controles del footer.
	 */
	private void initFooterControls() {
		// ---Foooter controls
		setShowFooter(true);

		final Label function = new Label("Función");
		function.setStyleName("windowFooterText");
		final SimpleDateFormat sdf = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss a");
		// Label date = new
		// Label(DateFormat.getDateInstance(DateFormat.TIMEZONE_FIELD).format(new
		// Date()));
		final Label date = new Label(sdf.format(new Date()));
		date.setWidth(120);
		date.setStyleName("windowFooterText");
		setFooterControls(function, new LayoutSpacer(), date);
		setFooterHeight(FOOTER_HEIGHT);
	}

	/**
	 * 	Funcion que inicializa los handlers de la ventana.
	 */
	private void initHandlers() {
		GHAUiHelper.addGHAResizeHandler(this);
		GHAUiHelper.addWindowScrollHandler(this);
	}

	/**
	 * 	Funcion que inicializa los controles del header.
	 */
	private void initHeaderControls() {
		// ---Header Controls
		setShowHeaderIcon(true);
		setHeaderIcon("../resources/icons/favicon.ico");
		setShowMinimizeButton(false);
		setShowMaximizeButton(false);
		setShowCloseButton(false);
	}

	/**
	 * 	Funcion que inicializa los Parametros básicos de la vista de la ventana.
	 */
	private void initialize() {
		// ---Parámetros basicos------------------
		setAutoSize(false);
		setAutoCenter(false);
		setWidth(DEFAULT_NOTIFICATION_WIDTH);

		if (hasButtons)
			dialogHeight = DEFAULT_NOTIFICATION_BUTTONS_HEIGHT;
		else
			dialogHeight = DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT;

		setHeight(dialogHeight);
		setMaxHeight(dialogHeight);
		//----Body Autochild---------------------
		final Layout bodyAC = new Layout();
		bodyAC.setHeight(dialogHeight - (HEADER_HEIGHT + FOOTER_HEIGHT  + BORDER_SEPARATION));

		// bodyAC.setAlign(VerticalAlignment.CENTER);
		// bodyAC.setAlign(Alignment.CENTER);
		changeAutoChildDefaults("body", bodyAC);
		//----Message Stack Autochild-----------
		final Layout msgStack = new Layout();
		msgStack.setStyleName("dialogMessageStack");
		changeAutoChildDefaults("messageStack", msgStack);
		//-------------------------------------
	}

	/**
	 * 	Funcion que inicializa los parametros del tipo de ventana.
	 */
	private void initTypeParameters(GHAMessageType type, int time) {
		final int secsToMills = 1000;
		dialogType = type.getCode();
		isTimed = type.isTimed();
		isModal = type.isModal();
		if (time >= 0) {
			waitingTime = time * secsToMills;
		} else {
			waitingTime = type.getTimeShowing() * secsToMills;
		}
	}

	/**
	 * 	Funcion que inicializa la vista del tipo de ventana.
	 */
	protected abstract void initTypeView();

	@Override
	public void onResize(ResizeEvent event) {
		// Window.alert("weight:"+event.getWidth()+"\nheight"+event.getHeight());
		resize(Window.getScrollTop(), Window.getScrollLeft(), event.getWidth(),
				event.getHeight());

	}

	@Override
	public void onWindowScroll(ScrollEvent arg0) {
		// Window.alert("left:"+arg0.getScrollLeft()+"\ntop"+arg0.getScrollTop());
		resize(arg0.getScrollTop(), arg0.getScrollLeft(),
				Window.getClientWidth(), Window.getClientHeight());
	}

	/**
	 * 	Funcion que cambia de tamaño la ventana. depende de si la ventana tiene botones o no.
	 */
	private void resize(int top, int left, int width, int height) {
		if (isVisible()) {
			final int windowWidth = width;
			final int windowHeight = height;

			int multp = 0;
			int rightMargin;
			if (openedPosition < 0) {
				rightMargin = (getWidth() + RIGHT_MARGIN) * 2;
				multp = dialogHeight + BORDER_SEPARATION;
			} else {
				rightMargin = getWidth() + RIGHT_MARGIN;
				multp = (openedPosition + 1) * (dialogHeight + BORDER_SEPARATION);
			}

			setLeft(left + (windowWidth - rightMargin));
			setTop(top + (windowHeight - multp));
		}
	}

	/**
	 * @param dialogType
	 *            The type of dialog to set
	 */
	public void setDialogType(String dialogType) {
		this.dialogType = dialogType;
	}

	/**
	 * 	Funcion que inicializa el estilo de cada una de las partes de la ventana.
	 */
	private void setOriginalStyle() {
		setHeaderStyle("dialogHeaderStyle");
		setStyleName("dialogStyle");
		setBodyStyle("dialogBodyStyle");
		setMessageStyle("dialogMessageStyle");
	}

	/**
	 * 	Funcion que muestra la ventana.
	 */
	@Override
	public void show() {
		openedPosition = GHAErrorMessageProcessor.getFreeMessagePosition();
		Window.alert("SHOW: openMessageCounter:"+GHAErrorMessageProcessor.getOpenMessagesCounter()+"\nOpenedPosition:"+openedPosition);
		if (openedPosition >= 0 || isModal) {
			if (openedPosition >= 0) {
				GHAErrorMessageProcessor.toggleMessagePosition(openedPosition);
				GHAErrorMessageProcessor.addNewMessageToCounter();
			}

			super.show();
			// set the height to the available message text space
			getMessageStack().setHeight(40);

			final int windowWidth = Window.getClientWidth();
			final int windowHeight = Window.getClientHeight();

			int multp = 0;
			int rightMargin;
			if (openedPosition < 0) {
				rightMargin = (getWidth() + RIGHT_MARGIN) * 2;
				multp = dialogHeight + BORDER_SEPARATION;
			} else {
				rightMargin = getWidth() + RIGHT_MARGIN;
				multp = (openedPosition + 1) * (dialogHeight + BORDER_SEPARATION);
			}

			setLeft(Window.getScrollLeft()	+ (windowWidth - rightMargin));
			setTop(Window.getScrollTop() + Window.getClientHeight());

			animateRect(null, Window.getScrollTop() + (windowHeight - multp),
					null, null);
		}
	}
}
