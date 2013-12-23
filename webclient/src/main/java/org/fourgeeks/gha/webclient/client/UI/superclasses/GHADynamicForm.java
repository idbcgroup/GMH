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
		 * Type for a SectionForm Form
		 */
		SECTIONFORM_FORM, 
		/**
		 * Type for a full-width Form
		 */
		NORMAL_FORM;
	}
	
	final private int columns;
	private FormType formtype;
	
	/**
	 * @param numCols
	 * @param type
	 */
	public GHADynamicForm(int numCols, FormType type) {
		this.formtype=type;
		this.columns=numCols;
		initComponents();
	}

	/**
	 * 
	 */
	public void initComponents() {
		int fWidth = GHAUiHelper.getFormWidth(formtype, 30);
		setWidth(fWidth);
		if(formtype == FormType.NORMAL_FORM){
			setMinWidth(GHAUiHelper.MIN_NORMAL_FORM_WIDTH);
		}else{
			setMinWidth(GHAUiHelper.MIN_SECTION_FORM_FORM_WIDTH);
		}
		setNumCols(columns);
		setMinColWidth(50);

		int itemW = fWidth / columns;

		// int widths[] = new int[numCols];
		// Window.alert(widths.length+"");
		// for(int i=0;i<widths.length; i++){
		// widths[i]=itemW;
		// Window.alert(widths[i]+"");
		// }

		setColWidths(itemW);
		setTitleOrientation(TitleOrientation.TOP);
		setWrapItemTitles(false);

		// setCanDragResize(true);
		// setShowEdges(true);

	}

	/**
	 * 	Function that handles the resize of the form.
	 */
	public void resize() {
		int formWidth = GHAUiHelper.getFormWidth(formtype, 30);
		setWidth(formWidth);
		int itemW = formWidth / columns;
		setColWidths(itemW);
	}
}
