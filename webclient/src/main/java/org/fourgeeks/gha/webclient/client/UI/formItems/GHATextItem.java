package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.Date;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.EditorExitEvent;
import com.smartgwt.client.widgets.form.fields.events.EditorExitHandler;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;

/**
 * @author alacret
 * 
 */
public class GHATextItem extends TextItem {

	/**
	 * 
	 */
	public GHATextItem() {
		super();
		setHeight(20);
		setWidth("*");
		setOriginalStyle();
		setLength(255);
		
		// setErrorOrientation(FormErrorOrientation.RIGHT);
		setShowErrorIcon(false);
		setValidateOnExit(true);
		
		addEditorExitHandler(new EditorExitHandler() {
			@Override
			public void onEditorExit(EditorExitEvent event) {
//				Window.alert("Se dispara el editorExit");
				if(getRequired()){
					if (validate()) {
						setTextBoxStyle("input requiredValidated");
					} else {
						setTextBoxStyle("input required");
					}
				}else{
					setOriginalStyle();
				}
			}
		});		
	}

	/**
	 * 
	 */
	private void setOriginalStyle() {
		setTextBoxStyle("input");
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");
	}
	
	/**
	 * @param title
	 */
	public GHATextItem(String title) {
		this();
		setTitle(title);
	}

	/**
	 * @param title
	 * @param width
	 */
	public GHATextItem(String title, int width) {
		this(title);
		setWidth(width);
	}
	
	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHATextItem(String title, int width, boolean required,
			ChangedHandler changedHandler) {
		this(title, width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}
	
	/**
	 * @param title
	 * @param required
	 * @param changedHandler
	 */
	public GHATextItem(String title, boolean required,
			ChangedHandler changedHandler) {
		this(title);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param width
	 */
	public GHATextItem(int width) {
		this();
		setShowTitle(false);
		setWidth(width);
	}

	/**
	 * @param title
	 * @param width
	 * @param active
	 */
	public GHATextItem(String title, int width, boolean active) {
		this(title, width);
		setDisabled(!active);
	}

	/**
	 * @param title
	 * @param active
	 */
	public GHATextItem(String title, boolean active) {
		this(title);
		setDisabled(!active);
	}

	@Override
	public void setDisabled(Boolean disabled) {
		super.setDisabled(disabled);
	}

	@Override
	public void setRequired(Boolean required) {
		super.setRequired(required);
		if (required) {
			setTextBoxStyle("input required");
		} else {
			setOriginalStyle();
		}
	}
	
	private void initialValidation(){
		Boolean required = getRequired();
		if(required != null && required){
			if(validate()){
				setTextBoxStyle("input requiredValidated");;
			}else{
				setTextBoxStyle("input required");
			}
		}
	}
	
	@Override
	public void setValue(boolean value) {
		super.setValue(value);
		initialValidation();
	}
	
	@Override
	public void setValue(String value) {
		super.setValue(value);
		initialValidation();
	}
	
	@Override
	public void setValue(Date value) {
		super.setValue(value);
		initialValidation();
	}
	
	@Override
	public void setValue(int value) {
		super.setValue(value);
		initialValidation();
	}
	
	@Override
	public void setValue(double value) {
		super.setValue(value);
		initialValidation();
	}
	
	@Override
	public void setValue(Object value) {
		super.setValue(value);
		initialValidation();
	}
	
	@Override
	public void clearValue() {
		super.clearValue();
		Boolean required = getRequired();
		setOriginalStyle();
		if (required != null && required)
			setTextBoxStyle("input required");
	}
	/**
	 * @author Guerere
	 * 
	 * @Metodo:
	 *  Valida que solo se ingresen letras usando la expresion regular ^[a-zA-ZáéíóúAÉÍÓÚÑñ]+$
	 *  recibe por parametro el mensaje de error que sera mostrado por el campo  
	 */
	
	public void validateCustomExpre(String messageError,String expresionReg) {
		RegExpValidator textValidator = new RegExpValidator();  
		textValidator.setErrorMessage(messageError);  
		textValidator.setExpression(expresionReg);  
	    setValidators(textValidator);
	    addEditorExitHandler(new EditorExitHandler(){

			@Override
			public void onEditorExit(EditorExitEvent event) {
				validate();
			}});
	    setShowErrorIcon(true);
	}

	public void validateWords() {
		validateCustomExpre(GHAStrings.get("formatter-error-words"), "^[a-zA-ZáéíóúÁÉÍÓÚÑñ ]+$");  
	}

	public void validateDates() {
		validateCustomExpre(GHAStrings.get("formatter-error-dates"), "^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
	}

	public void validateNumbers() {
		validateCustomExpre(GHAStrings.get("formatter-error-numbers"), "^[0-9]+$");
	}

	public void validateAlphanumeric() {
		validateCustomExpre(GHAStrings.get("formatter-error-alphanumeric"), "^[a-zA-ZáéíóúÁÉÍÓÚÑñ0-9 ]+$");
	}

	public void validateSerial() {
		validateCustomExpre(GHAStrings.get("formatter-error-serial"), "^[0-9-]+$");
	}

	public void validateCodes() { //alfanumerico sin espacio
		validateCustomExpre(GHAStrings.get("formatter-error-codes"), "^[a-zA-Z0-9]+$");
	}

	public void validateRif() {
		validateCustomExpre(GHAStrings.get("user-error-formatter-rif"), "^[VvMmPprREeJjIiGg0-9|-]+$");
	}
}
