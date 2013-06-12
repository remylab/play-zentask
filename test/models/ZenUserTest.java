package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

import org.junit.Before;
import org.junit.Test;

import play.test.WithApplication;

public class ZenUserTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }

    @Test
    public void createAndRetrieveZenUser() {
        new ZenUser("bob@gmail.com", "Bob", "secret").save();
        ZenUser bob = ZenUser.find.where().eq("email", "bob@gmail.com").findUnique();
        assertNotNull(bob);
        assertEquals("Bob", bob.name);
    }

    @Test
    public void tryAuthenticateZenUser() {
        new ZenUser("bob@gmail.com", "Bob", "secret").save();

        assertNotNull(ZenUser.authenticate("bob@gmail.com", "secret"));
        assertNull(ZenUser.authenticate("bob@gmail.com", "badpassword"));
        assertNull(ZenUser.authenticate("tom@gmail.com", "secret"));
    }
}