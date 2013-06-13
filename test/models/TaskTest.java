package models;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import play.libs.Yaml;

import com.avaje.ebean.Ebean;

public class TaskTest extends EbeanTest {
	@Test
	public void testData() {
		Ebean.save((List) Yaml.load("test-data.yml"));

		// Count things
		assertEquals(3, ZenUser.find.findRowCount());
		assertEquals(7, Project.find.findRowCount());
		assertEquals(5, Task.find.findRowCount());
	}

	@Test
	public void findTodoTasksInvolving() {
		Ebean.save((List) Yaml.load("test-data.yml"));

		// Find all Bob's todo tasks
		List<Task> bobsTasks = Task.findTodoInvolving("bob@example.com");
		assertEquals(4, bobsTasks.size());
	}
}
