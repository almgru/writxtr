package writxtr.ui;

import writxtr.listeners.FontChangedListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FontWindow extends Stage implements EventHandler<ActionEvent>
{
	private static final long serialVersionUID = 3407769020921041025L;

	private FontChangedListener fontChangedListener;

	private Button confirmButton;
	private Button cancelButton;
	
	public FontWindow(FontChangedListener fontChangedListener){
		this.fontChangedListener = fontChangedListener;
	}
	
	public void init(boolean b, MainView window){
		setTitle("Writxtr - Font selection screen");
	}
	
	public static void setFontNames(String[] fontNames) {
	}
	
	public void fireFontChangedEvent() {

	}

    @Override
    public void handle(ActionEvent event)
    {
        if(event.getSource() instanceof Button){

            if(event.getSource() == confirmButton){
                fireFontChangedEvent();
                hide();
            }

            else if (event.getSource() == cancelButton){
                hide();
            }
        }
    }
}
