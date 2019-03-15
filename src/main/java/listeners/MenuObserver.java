package writxtr.listeners;

import writxtr.beans.MenuEvent;

public interface MenuObserver {
	void onMenuButtonClicked(MenuEvent event);
}
