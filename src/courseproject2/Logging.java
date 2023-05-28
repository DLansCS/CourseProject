package courseproject2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

public class Logging {
	
	static Logger logger = Logger.getLogger(Logging.class.getName());

	public static void main(String[] args) {
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("C:\\Users\\Dalton\\eclipse-workspace\\courseproject2\\src\\courseproject2\\mylogging.properties"));
		}catch(SecurityException | IOException e1) {
			e1.printStackTrace();
		}
		ConsoleHandler ch = new ConsoleHandler();
		ch.setFormatter(new SimpleFormatter());
		logger.addHandler(ch);
		
		try {
			Handler fileHandler = new FileHandler("/Users/Dalton/tmp/logger.log", 2000, 5);
			logger.addHandler(fileHandler);
			fileHandler.setFormatter(new XMLFormatter());
			
			for(int i=0;i<100;i++) {
				logger.log(Level.INFO, "Msg" + i);
			}
		}catch(SecurityException | IOException e) {
		e.printStackTrace();
		} 
		
	}
}
