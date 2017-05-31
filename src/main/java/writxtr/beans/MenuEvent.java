package writxtr.beans;

import writxtr.enums.MenuAction;


public class MenuEvent {
	private MenuAction item;
	
	public MenuEvent(MenuAction item){
		this.item = item;
	}
	
	public MenuAction getItem(){
		return item;
	}
}
