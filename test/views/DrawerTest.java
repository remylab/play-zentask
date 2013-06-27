package views;

import static play.test.Helpers.fakeGlobal;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pages.Dashboard;
import pages.Drawer;
import pages.Login;
import play.libs.Yaml;
import play.test.FakeApplication;
import play.test.Helpers;
import play.test.WithBrowser;
import util.EbeanTestUtil;

import com.avaje.ebean.Ebean;

//@Ignore
public class DrawerTest extends WithBrowser {

    public Dashboard dashboard;
    public Drawer drawer;

    @BeforeClass
    public static void beforeClass() throws IOException {

        FakeApplication tmpApp = Helpers.fakeApplication(fakeGlobal());
        Helpers.start(tmpApp);

        try {
            EbeanTestUtil.dropDB();
        } catch (IOException e) {
            // ignore
        }
        EbeanTestUtil.createDB();
        Helpers.stop(tmpApp);
    }

    @Before
    public void setUp() {

        start();
    }

    @Test
    public void newProject() throws Exception {

        Ebean.save((List<?>) Yaml.load("test-data.yml"));

        Login login = browser.createPage(Login.class);
        login.go();
        login.login("bob@example.com", "secret");
        dashboard = browser.createPage(Dashboard.class);

        drawer = dashboard.drawer();

        // click button "new project"
        drawer.group("Personal").newProject();

        // check if new project is there
        dashboard.await().until(drawer.group("Personal").hasProject("New project"));

    }

}