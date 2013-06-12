package models;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import play.libs.Yaml;
import play.test.WithApplication;

import com.avaje.ebean.Ebean;

public class TaskTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));

        Ebean.save((List) Yaml.load("test-data.yml"));
    }

    @Test
    public void testData() {
        // Count things
        assertEquals(3, User.find.findRowCount());
        assertEquals(7, Project.find.findRowCount());
        assertEquals(5, Task.find.findRowCount());
    }

    @Test
    public void findTodoTasksInvolving() {
        // Find all Bob's todo tasks
        List<Task> bobsTasks = Task.findTodoInvolving("bob@example.com");
        assertEquals(4, bobsTasks.size());
    }
}
