package base;

import static play.test.Helpers.fakeGlobal;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import play.test.FakeApplication;
import play.test.Helpers;
import play.test.WithApplication;
import util.EbeanTestUtil;

public class AbstractDBApplicationTest extends WithApplication {

	public static FakeApplication app;

	@BeforeClass
	public static void beforeClass() throws IOException {

		app = Helpers.fakeApplication(fakeGlobal());
		Helpers.start(app);

		try {
			EbeanTestUtil.dropDB();
		} catch (IOException e) {
			// ignore
		}
		EbeanTestUtil.createDB();
	}

	@AfterClass
	public static void afterClass() throws IOException {

		Helpers.stop(app);

	}
}