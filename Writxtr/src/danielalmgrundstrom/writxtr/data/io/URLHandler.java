package danielalmgrundstrom.writxtr.data.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import danielalmgrundstrom.writxtr.beans.IOErrorEvent;
import danielalmgrundstrom.writxtr.beans.LoadURLEvent;
import danielalmgrundstrom.writxtr.listeners.LoadURLListener;

public class URLHandler implements LoadURLListener {

	private IOHandler iOHandler;
	
	public URLHandler(IOHandler iOHandler){
		this.iOHandler = iOHandler;
	}
	
	private void loadURL(URL url) {
		try (Scanner scanner = new Scanner(url.openStream())) {
			
			StringBuilder s = new StringBuilder("");
			String line = null;

			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				s.append(line).append("\n");
			}

			LoadURLEvent event = new LoadURLEvent();
			event.setURLContent(s.toString());
			event.setURL(url.toString());
			iOHandler.fireLoadCompleteEvent(event);

		} catch (FileNotFoundException e) {
			iOHandler.fireIOErrorEvent(new IOErrorEvent(String.format(
					"Could not find url '%s'.", url), e));
		} catch (IOException e) {
			iOHandler.fireIOErrorEvent(new IOErrorEvent(String.format(
					"Could not read url '%s'.", url), e));
		}
	}

	@Override
	public void onLoadURLRequest(URL url) {
		loadURL(url);
	}
}
