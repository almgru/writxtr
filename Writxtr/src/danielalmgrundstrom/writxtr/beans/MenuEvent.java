package danielalmgrundstrom.writxtr.beans;

import danielalmgrundstrom.writxtr.enums.MenuAction;


public class MenuEvent {
	private MenuAction item;
	
	public MenuEvent(MenuAction item){
		this.item = item;
	}
	
	public MenuAction getItem(){
		return item;
	}
}
