package org.fourgeeks.gha.webclient.client.login.ForgottenPassword;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASubmitItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.History;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;

public class mailCheckPanel extends VLayout implements ResizeHandler{

	public mailCheckPanel() {
		setWidth100();
		setStyleName("padding-top-recovery");
		setHeight(GHAUiHelper.getTabHeight());
		setDefaultLayoutAlign(Alignment.CENTER);
		
		Label title = new Label("<h3>Recuperar Contraseña</h3>");
		title.setHeight(35);
		title.setWidth(400);
		title.setStyleName("title-label");		
		
		Label instructions = new Label("Si deseas restablecer la contraseña, ingresa la dirección de correo electrónico asociada a tu cuenta.");
		instructions.setHeight(35);
		instructions.setWidth(400);
		
		GHATextItem passField = new GHATextItem(140);
		GHASubmitItem submitButton = new GHASubmitItem("Enviar", new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			
			@Override
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				//Send password to user
				SC.say("En pocos minutos recibirá un correo a la dirección especificada con su contraseña.");
				History.newItem("login");
			}
		});
		submitButton.setWidth(190);
		submitButton.setStartRow(false);
		
		DynamicForm form = new DynamicForm();
		form.setWidth(400);
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(2);
		
		form.setItems(passField,submitButton);
				
		addMembers(title,instructions,form);		
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight());
		
	}

}