package writxtr.io.io;

import writxtr.beans.IOErrorEvent;
import writxtr.beans.LoadFileEvent;
import writxtr.beans.SaveCompleteEvent;
import writxtr.beans.SaveEvent;
import writxtr.listeners.LoadFileListener;
import writxtr.listeners.SaveListener;

import java.io.*;

public class FileHandler implements SaveListener, LoadFileListener {
	
	private IOHandler iOHandler;
	
	public FileHandler(IOHandler iOHandler){
		this.iOHandler = iOHandler;
	}
	
	@Override
	public void onLoadFileRequest(File file) {
		loadFile(file);
	}

	@Override
	public void onSaveRequest(File file, SaveEvent event) {
		saveFile(file, event.getContent());
	}
	
	private void saveFile(File file, String content) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

			bw.write(content);
			iOHandler.fireSaveCompleteEvent(new SaveCompleteEvent(file.getName()));

		} catch (IOException e) {
			iOHandler.fireIOErrorEvent(new IOErrorEvent(String.format(
					"Could not write to file '%s'.", file), e));
		}
	}

	private void loadFile(File file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			StringBuilder s = new StringBuilder("");
			String line = null;

			while ((line = br.readLine()) != null) {
				s.append(line).append("\n");
			}

			LoadFileEvent event = new LoadFileEvent();
			event.setFileContent(s.toString());
			event.setName(file.getName());
			iOHandler.fireLoadCompleteEvent(event);

		} catch (FileNotFoundException e) {
			iOHandler.fireIOErrorEvent(new IOErrorEvent(String.format(
					"Could not find file '%s'.", file), e));
		} catch (IOException e) {
			iOHandler.fireIOErrorEvent(new IOErrorEvent(String.format(
					"Could not read file '%s'.", file), e));
		}
	}

}
