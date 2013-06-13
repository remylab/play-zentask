package models;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import play.test.FakeApplication;
import play.test.Helpers;
import play.test.WithApplication;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.PostgresPlatform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;

public class EbeanTest extends WithApplication {

	public static FakeApplication app;

	@BeforeClass
	public static void startApp() throws IOException {

		app = Helpers.fakeApplication();
		Helpers.start(app);
	}

	@AfterClass
	public static void stopApp() {
		Helpers.stop(app);
	}

	@Before
	public void dropCreateDb() throws IOException {

		String serverName = "default";

		EbeanServer server = Ebean.getServer(serverName);

		ServerConfig config = new ServerConfig();

		DdlGenerator ddl = new DdlGenerator((SpiEbeanServer) server, new PostgresPlatform(), config);

		// Drop
		ddl.runScript(false, ddl.generateDropDdl());

		// Create
		ddl.runScript(false, ddl.generateCreateDdl());
	}
}