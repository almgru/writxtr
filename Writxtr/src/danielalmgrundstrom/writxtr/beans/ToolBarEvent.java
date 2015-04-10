package danielalmgrundstrom.writxtr.beans;

import danielalmgrundstrom.writxtr.enums.ToolBarItem;


public class ToolBarEvent {
	private ToolBarItem item;
	
	public ToolBarEvent(ToolBarItem item){
		this.item = item;
	}
	
	public ToolBarItem getItem(){
		return item;
	}
}
