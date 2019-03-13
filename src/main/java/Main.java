package writxtr;

import java.awt.EventQueue;

import javax.swing.UIManager;

import writxtr.controller.Controller;
import writxtr.data.DataHandler;
import writxtr.ui.Window;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				setLookAndFeel();
				start();
			}
		});
	}

	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
					
		} catch (Exception e1) {
			System.out.println("Error loading system look and feel.\nAtempting to load cross plattform look and feel...");
			try {
				UIManager.setLookAndFeel(
						UIManager.getCrossPlatformLookAndFeelClassName());
				System.out.println("Success!");
				
			} catch (Exception e2) {

				System.out.println("Error loading cross platform look and feel. Exiting program...");
				System.exit(1);
			}
		}
	}

	private static void start() {
		DataHandler dataHandler = new DataHandler();
		Window window = new Window();
		Controller control = new Controller(dataHandler, window);

		window.init();
		control.init();
	}
}
