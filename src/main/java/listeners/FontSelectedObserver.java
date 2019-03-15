package writxtr.listeners;

import writxtr.beans.FontSelectEvent;

public interface FontSelectedObserver {
	void onFontSelected(FontSelectEvent event);
}
