package org.wcec.retreat.app;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.StringUtils;
import org.wcec.retreat.entity.EventTbl;
import org.wcec.retreat.extendedgui.MyTableMaintenanceDesign;
import org.wcec.retreat.repo.AccessLevelRepo;
import org.wcec.retreat.repo.AddressTblRepo;
import org.wcec.retreat.repo.BedTypeTblRepo;
import org.wcec.retreat.repo.BuildingTblRepo;
import org.wcec.retreat.repo.ChurchTblRepo;
import org.wcec.retreat.repo.EmailTblRepo;
import org.wcec.retreat.repo.EventHostTblRepo;
import org.wcec.retreat.repo.EventTblRepo;
import org.wcec.retreat.repo.EventTypeTblRepo;
import org.wcec.retreat.repo.GroupTblRepo;
import org.wcec.retreat.repo.GroupTypeTblRepo;
import org.wcec.retreat.repo.LodgingAssignmentTblRepo;
import org.wcec.retreat.repo.MealPlanTblRepo;
import org.wcec.retreat.repo.MealTblRepo;
import org.wcec.retreat.repo.MealTblRepository;
import org.wcec.retreat.repo.PaymentTblRepo;
import org.wcec.retreat.repo.PersonTblRepository;
import org.wcec.retreat.repo.PhoneTblRepo;
import org.wcec.retreat.repo.PhoneTypeTblRepo;
import org.wcec.retreat.repo.RegistrationTblRepo;
import org.wcec.retreat.repo.RoomTblRepo;
import org.wcec.retreat.repo.UserLoginRepo;
import org.wcec.retreat.repo.UserTblRepo;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
public class VaadinUI extends UI {
	@Autowired
	AccessLevelRepo                            TheAccessLevelRepo;
	
	@Autowired
	AddressTblRepo                             TheAddressTblRepo;
	
	@Autowired
	BedTypeTblRepo                             TheBedTypeTblRepo;
	
	@Autowired
	BuildingTblRepo                            TheBuildingTblRepo;
	
	@Autowired
	ChurchTblRepo                              TheChurchTblRepo;
	
	@Autowired
	EmailTblRepo                               TheEmailTblRepo;
	
	@Autowired
	EventHostTblRepo                           TheEventHostTblRepo;
	
	@Autowired
	EventTblRepo                               TheEventTblRepo;
	
	@Autowired
	EventTypeTblRepo                           TheEventTypeTblRepo;
	
	@Autowired
	GroupTblRepo                               TheGroupTblRepo;
	
	@Autowired
	GroupTypeTblRepo                           TheGroupTypeTblRepo;
	
	@Autowired
	LodgingAssignmentTblRepo                   TheLodgingAssignmentTblRepo;
	
	@Autowired
	MealPlanTblRepo                            TheMealPlanTblRepo;
	
	@Autowired
	MealTblRepo                                TheMealTblRepo;
	
	@Autowired
	MealTblRepository                          TheMealTblRepository;
	
	@Autowired
	PaymentTblRepo                             ThePaymentTblRepo;
	
	@Autowired
	PersonTblRepository                        ThePersonTblRepository;
	
	@Autowired
	PhoneTblRepo                               ThePhoneTblRepo;
	
	@Autowired
	PhoneTypeTblRepo                           ThePhoneTypeTblRepo;
	
	@Autowired
	RegistrationTblRepo                        TheRegistrationTblRepo;
	
	@Autowired
	RoomTblRepo                                TheRoomTblRepo;
	
	@Autowired
	UserLoginRepo                              TheUserLoginRepo;
	
	@Autowired
	UserTblRepo                                TheUserTblRepo;
	
	SimpleJpaRepository                        TheRepository;

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final EventTblRepo eventTblRepo;

	private final EventTblEditor editor;

	 

	final TextField filter;

	private final Button addNewBtn;
	
	
	@Autowired
	public VaadinUI(EventTblRepo eventTblRepo, EventTblEditor editor) {
		this.eventTblRepo = eventTblRepo;
		this.editor = editor;
		
		
		this.filter = new TextField();
		this.addNewBtn = new Button("New meal type", FontAwesome.PLUS);
	}
	
	
	 
	 

	void findTabSheet(ComponentContainer aComponentContainer, List<TabSheet> outList) {
		Iterator<Component> itr = aComponentContainer.getComponentIterator();
		while (itr.hasNext()) {
			Component comp = itr.next();
			System.out.println("Comp = " + comp);
			if (comp instanceof TabSheet) {
				outList.add((TabSheet)comp);
			}
			if (comp instanceof ComponentContainer) {
				findTabSheet((ComponentContainer) comp, outList);
			}
		}
	}
	
	@Override
	protected void init(VaadinRequest request) {
		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		//VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
		VerticalLayout mainLayout = new MyTableMaintenanceDesign(); 
		ArrayList<TabSheet> outList = new ArrayList<TabSheet>();
		findTabSheet((ComponentContainer) mainLayout, outList); 
		setContent(mainLayout);
		TabSheet sheet = outList.get(0);
		Grid<EventTbl> theGrid = new Grid<>(EventTbl.class);
		
		List<Field> currentClassFields = (List) Lists.newArrayList(EventTbl.class.getDeclaredFields());
		String fieldNames[] = new String[currentClassFields.size()];
		int actualSize = 0;
		for (int i = 0, j = 0; i < fieldNames.length; i++) {
			if (!currentClassFields.get(i).getName().equals("serialVersionUID")) {
				fieldNames[j] = currentClassFields.get(i).getName();
				j++;
				actualSize = j;
			}
		}
		String fieldNames2[] = new String[actualSize];
		for (int i = 0, j = 0; i < fieldNames.length; i++) {
			if (fieldNames[i] != null) { 
				fieldNames2[j] = fieldNames[i];
				j++;
			}
		}
		
		VerticalLayout tab1 = new VerticalLayout(); 
		sheet.addTab(tab1, "Domain Table", null); 
		tab1.setVisible(true);
		
		
		tab1.addComponent(theGrid);
		theGrid.setHeight(100, Unit.PERCENTAGE);
		theGrid.setWidth(100, Unit.PERCENTAGE);
		theGrid.setVisible(true);
		theGrid.setColumns(fieldNames2);
		theGrid.setItems(eventTblRepo.findAll());
		
		/*

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
*/
		// Initialize listing
		listEventTbls(null);
		 
	}

	// tag::listEventTbls[]
	void listEventTbls(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			//grid.setItems(eventTblRepo.findAll());
		} 
	}
	// end::listEventTbls[]

}
