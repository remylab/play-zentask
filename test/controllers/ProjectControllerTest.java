package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.status;

import java.util.List;

import models.Project;

import org.junit.BeforeClass;
import org.junit.Test;

import play.libs.Yaml;
import play.mvc.Result;
import base.AbstractDBApplicationTest;

import com.avaje.ebean.Ebean;
import com.google.common.collect.ImmutableMap;

public class ProjectControllerTest extends AbstractDBApplicationTest {

    @BeforeClass
    public static void setUp() {
        Ebean.save((List<?>) Yaml.load("test-data.yml"));
    }

    @Test
    public void newProject() {
        Result result = callAction(
                controllers.routes.ref.ProjectController.add(),
                fakeRequest().withSession("email", "bob@example.com")
                        .withFormUrlEncodedBody(ImmutableMap.of("folder", "Some folder"))
                );
        assertEquals(200, status(result));
        Project project = Project.find.where()
                .eq("folder", "Some folder").findUnique();
        assertNotNull(project);
        assertEquals("New project", project.name);
        assertEquals(1, project.members.size());
        assertEquals("bob@example.com", project.members.get(0).email);
    }

    @Test
    public void renameProject() {
        long id = Project.find.where()
                .eq("members.email", "bob@example.com")
                .eq("name", "Private").findUnique().id;

        String newName = "Private bob";

        Result result = callAction(
                controllers.routes.ref.ProjectController.rename(id),
                fakeRequest().withSession("email", "bob@example.com")
                        .withFormUrlEncodedBody(ImmutableMap.of("name", newName))
                );

        assertEquals(200, status(result));
        assertEquals(newName, Project.find.byId(id).name);
    }

    @Test
    public void renameProjectForbidden() {
        long id = Project.find.where()
                .eq("members.email", "jeff@example.com")
                .eq("name", "Private").findUnique().id;

        Result result = callAction(
                controllers.routes.ref.ProjectController.rename(id),
                fakeRequest().withSession("email", "bob@example.com")
                        .withFormUrlEncodedBody(ImmutableMap.of("name", "New name"))
                );
        assertEquals(403, status(result));
        assertEquals("Private", Project.find.byId(id).name);
    }

}