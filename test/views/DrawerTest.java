package views;

import org.junit.Before;
import org.junit.Test;

import pages.Dashboard;
import pages.Drawer;
import pages.Login;
import play.test.WithBrowser;

public class DrawerTest extends WithBrowser {

    public Dashboard dashboard;
    public Drawer drawer;

    @Before
    public void setUp() {
        // start(Helpers.fakeApplication(), 19001);

        start();
        Login login = browser.createPage(Login.class);
        login.go();
        login.login("bob@example.com", "secret");
        dashboard = browser.createPage(Dashboard.class);
        drawer = dashboard.drawer();
    }

    @Test
    public void newProject() throws Exception {
        drawer.group("Personal").newProject();
        dashboard.await().until(drawer.group("Personal").hasProject("New project"));
    }
}