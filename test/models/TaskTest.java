package models;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import play.libs.Yaml;
import base.AbstractDBApplicationTest;

import com.avaje.ebean.Ebean;

public class TaskTest extends AbstractDBApplicationTest {

    @BeforeClass
    public static void setUp() {
        Ebean.save((List<?>) Yaml.load("test-data.yml"));
    }

    @Test
    public void testData() {

        // Count things
        assertEquals(3, ZenUser.find.findRowCount());
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
