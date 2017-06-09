package writxtr;

import writxtr.controller.Controller;
import writxtr.io.DataHandler;
import writxtr.ui.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

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

        SwingUtilities.invokeLater(() -> window.getWindowTextArea().init());
    }
}
