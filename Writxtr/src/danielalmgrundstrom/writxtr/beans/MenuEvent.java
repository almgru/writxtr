package danielalmgrundstrom.writxtr.beans;

import danielalmgrundstrom.writxtr.enums.MenuItem;


public class MenuEvent {
	private MenuItem item;
	
	public MenuEvent(MenuItem item){
		this.item = item;
	}
	
	public MenuItem getItem(){
		return item;
	}
}
