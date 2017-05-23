package danielalmgrundstrom.writxtr;

import danielalmgrundstrom.writxtr.controller.Controller;
import danielalmgrundstrom.writxtr.data.DataHandler;
import danielalmgrundstrom.writxtr.ui.MainView;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Main class which launches the application and creates the components (DataHandler, MainView & Controller)
 */
public class Main extends Application
{
	public static void main(String[] args)
    {
		Application.launch(args);
	}

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        DataHandler dataHandler = new DataHandler();
        MainView window = new MainView();
        Controller control = new Controller(dataHandler, window);

        window.init();
        control.init();

        Scene scene = new Scene(window, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnShown(window::onShown);
        primaryStage.setOnCloseRequest(window::onClose);
    }
}
