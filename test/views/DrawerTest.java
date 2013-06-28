package views;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pages.Dashboard;
import pages.Drawer;
import pages.Login;
import play.libs.Yaml;
import play.test.WithBrowser;
import util.EbeanTestUtil;

import com.avaje.ebean.Ebean;

//@Ignore
public class DrawerTest extends WithBrowser {

    public Dashboard dashboard;
    public Drawer drawer;

    @Before
    public void setUp() {

        start();

        try {
            EbeanTestUtil.dropDB();
            EbeanTestUtil.createDB();
        } catch (IOException e) {
            // ignore
        }
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