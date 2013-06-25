package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.header;
import static play.test.Helpers.session;
import static play.test.Helpers.status;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import play.libs.Yaml;
import play.mvc.Result;
import base.AbstractDBApplicationTest;

import com.avaje.ebean.Ebean;
import com.google.common.collect.ImmutableMap;

public class LoginTest extends AbstractDBApplicationTest {

    @BeforeClass
    public static void setUp() {
        Ebean.save((List<?>) Yaml.load("test-data.yml"));
    }

    @Test
    public void authenticateSuccess() {
        Result result = callAction(controllers.routes.ref.Login.authenticate(), fakeRequest().withFormUrlEncodedBody(ImmutableMap.of("email", "bob@example.com", "password", "secret")));
        assertEquals(303, status(result));
        assertEquals("bob@example.com", session(result).get("email"));
    }

    @Test
    public void authenticateFailure() {
        Result result = callAction(controllers.routes.ref.Login.authenticate(), fakeRequest().withFormUrlEncodedBody(ImmutableMap.of("email", "bob@example.com", "password", "badpassword")));
        assertEquals(400, status(result));
        assertNull(session(result).get("email"));
    }

    @Test
    public void authenticated() {
        Result result = callAction(controllers.routes.ref.Application.index(), fakeRequest().withSession("email", "bob@example.com"));
        assertEquals(200, status(result));
    }

    @Test
    public void notAuthenticated() {
        Result result = callAction(controllers.routes.ref.Application.index(), fakeRequest());
        assertEquals(303, status(result));
        assertEquals("/login", header("Location", result));
    }
}
