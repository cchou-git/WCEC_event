package org.wcec.retreat.gui;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class UpsertEventDesign extends VerticalLayout {
	protected ComboBox<org.wcec.retreat.entity.EventTypeTbl> comboEventTypeDropdown;
	protected ComboBox<org.wcec.retreat.entity.EventTbl> comboAvailableEventDropdown;
	protected TextField textBoxEventName;
	protected DateTimeField dateTimeStartDate;
	protected DateTimeField dateTimeEndDate;

	public UpsertEventDesign() {
		Design.read(this);
	}
}