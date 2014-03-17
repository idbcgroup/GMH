package org.fourgeeks.gha.webclient.client.UI.superclasses;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;

/**
 * @author jfuentes
 * 
 */

public class GHADynamicForm extends DynamicForm {
	/**
	 * @author jfuentes
	 *
	 */
	public enum FormType {
		/**
		 * Type for a SectionForm Mini Form
		 */
		SECTIONFORM_MINIFORM,
		/**
		 * Type for a SectionForm Form
		 */
		SECTIONFORM_FORM,
		/**
		 * Type for a full-width Form
		 */
		NORMAL_FORM;
	}

	final private int columns;
	private final FormType formtype;
	int itemWidth = GHAUiHelper.DEFAULT_ITEM_WIDTH;

	/**
	 * @param numCols
	 * @param type
	 */
	public GHADynamicForm(int numCols, FormType type) {
		this.formtype=type;
		this.columns=numCols;
		makeCalculationsAndRender();
	}

	/**
	 * 
	 */
	public void makeCalculationsAndRender() {
		int fWidth = 400;
		if(formtype == FormType.NORMAL_FORM){
			setMinWidth(GHAUiHelper.MIN_NORMAL_FORM_WIDTH);
			fWidth = GHAUiHelper.getFormWidth(formtype, 130);
		}else if(formtype == FormType.SECTIONFORM_FORM){
			setMinWidth(GHAUiHelper.MIN_SECTIONFORM_FORM_WIDTH);
			fWidth = GHAUiHelper.getFormWidth(formtype, 130);
		}else{
			setMinWidth(GHAUiHelper.MIN_SECTIONFORM_MINIFORM_WIDTH);
			fWidth = GHAUiHelper.getFormWidth(formtype, 280);
		}
		setWidth(fWidth);
		setNumCols(columns);
		setMinColWidth(50);

		itemWidth = fWidth / columns;

		// int widths[] = new int[numCols];
		// Window.alert(widths.length+"");
		// for(int i=0;i<widths.length; i++){
		// widths[i]=itemW;
		// Window.alert(widths[i]+"");
		// }

		setColWidths(itemWidth);
		setTitleOrientation(TitleOrientation.TOP);
		setWrapItemTitles(false);

		// setCanDragResize(true);
		// setShowEdges(true);
	}

	/**
	 * 	Function that handles the resize of the form.
	 */
	public void resize() {
		int formWidth = 400;
		if(formtype == FormType.NORMAL_FORM){
			formWidth = GHAUiHelper.getFormWidth(formtype, 130);
		}else if(formtype == FormType.SECTIONFORM_FORM){
			formWidth = GHAUiHelper.getFormWidth(formtype, 130);
		}else{
			formWidth = GHAUiHelper.getFormWidth(formtype, 280);
		}
		setWidth(formWidth);
		itemWidth = formWidth / columns;
		setColWidths(itemWidth);
	}


	/**
	 * @return
	 */
	public int getItemW() {
		return itemWidth-8;
	}

	/**
	 * @param itemW
	 */
	public void setItemW(int itemW) {
		this.itemWidth = itemW;
	}
}
