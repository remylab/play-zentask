package util;

import java.io.IOException;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.PostgresPlatform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;

public class EbeanTestUtil {

	private final static String serverName = "default";

	public static void dropDB() throws IOException {

		EbeanServer server = Ebean.getServer(serverName);

		ServerConfig config = new ServerConfig();

		DdlGenerator ddl = new DdlGenerator((SpiEbeanServer) server, new PostgresPlatform(), config);

		// Drop
		ddl.runScript(false, ddl.generateDropDdl());
	}

	public static void createDB() throws IOException {

		EbeanServer server = Ebean.getServer(serverName);

		ServerConfig config = new ServerConfig();

		DdlGenerator ddl = new DdlGenerator((SpiEbeanServer) server, new PostgresPlatform(), config);

		// Create
		ddl.runScript(false, ddl.generateCreateDdl());
	}
}
