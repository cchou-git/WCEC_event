package org.wcec.retreat.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.wcec.retreat.entity.EventTbl;
import org.wcec.retreat.extendedgui.MyUpsertEventDesign;
import org.wcec.retreat.repo.EventTblRepo;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
public class VaadinUI extends UI {
	
	private final EventTblRepo eventTblRepo;

	private final EventTblEditor editor;

	final Grid<EventTbl> grid;

	final TextField filter;

	private final Button addNewBtn;
	
	
	@Autowired
	public VaadinUI(EventTblRepo eventTblRepo, EventTblEditor editor) {
		this.eventTblRepo = eventTblRepo;
		this.editor = editor;
		this.grid = new Grid<>(EventTbl.class);
		this.filter = new TextField();
		this.addNewBtn = new Button("New meal type", FontAwesome.PLUS);
	}

	 

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		//VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
		VerticalLayout mainLayout = new MyUpsertEventDesign(); 
		setContent(mainLayout);
/*
		grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("id", "mealType", "ageFrom", "ageTo", "mealPrice");

		filter.setPlaceholder("Filter by meal type");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.LAZY);
		filter.addValueChangeListener(e -> listEventTbls(e.getValue()));

		// Connect selected EventTbl to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editEventTbl(e.getValue());
		});

		// Instantiate and edit new EventTbl the new button is clicked
		addNewBtn.addClickListener(e -> editor.editEventTbl(new EventTbl()));

		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listEventTbls(filter.getValue());
		});

		// Initialize listing
		listEventTbls(null);
		*/
	}

	// tag::listEventTbls[]
	void listEventTbls(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(eventTblRepo.findAll());
		} 
	}
	// end::listEventTbls[]

}
