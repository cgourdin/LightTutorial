package light.main;

import java.io.FileNotFoundException;
import java.nio.file.Paths;

import org.ow2.erocci.backend.BackendDBusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErocciDbusMain {

	private static Logger logger = LoggerFactory.getLogger(ErocciDbusMain.class);
	
	public static void main(String[] args) throws FileNotFoundException {
		logger.info("Launching erocci-dbus-java");
		
		String[] argsForErocciDbus = null;
		if (args != null && args.length > 0) {
			argsForErocciDbus = args;
		} else {
			logger.info("Path :::");
			
			// Add the absolute path to light schema xml for Erocci.
			String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
			String parentPath = Paths.get(currentPath).getParent().normalize().toString();
			String lightXmlPath = Paths.get(parentPath + "/light/src-gen/erocci/light.xml").normalize().toString();
			
			
			argsForErocciDbus = new String[1];
			argsForErocciDbus[0] = lightXmlPath;
			
		}

		// Launch Erocci-dbus-java in the same context.
		BackendDBusService.main(argsForErocciDbus);
		
	}
	
}
