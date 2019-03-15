package writxtr.controller.ui;

import java.awt.Font;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import writxtr.beans.FontSelectEvent;
import writxtr.listeners.FontChangedListener;
import writxtr.view.Window;

public class TextAreaController implements CaretListener, DocumentListener, FontChangedListener {

	private Window window;
    private unsavedChanges;
	
	TextAreaController(Window window) {
		this.window = window;
	}

    @Overrid
	public void onFontSelected(FontSelectEvent event) {
		window.getWindowTextArea().setFont(new Font(event.getFontName(), event.getStyle(), event.getSize()));
	}

    public void hasUnsavedChanges() {
        return unsavedChanges;
    }

    public void resetUnsavedChanges() {
        unsavedChanges = false;
    }
	
	@Override
	void caretUpdate(CaretEvent caretEvent) {
		int dot = caretEvent.getDot();
		int mark = caretEvent.getMark();
		
		if (dot == mark) {
			window.getWindowMenuBar().setCCPEnabled(false);
        }
		else {
			window.getWindowMenuBar().setCCPEnabled(true);
        }
	}

	@Override
	void changedUpdate(DocumentEvent arg0) {
        unsavedChanges = true;
    }

	@Override
	void insertUpdate(DocumentEvent arg0) { }

	@Override
	void removeUpdate(DocumentEvent arg0) { }
	
}

