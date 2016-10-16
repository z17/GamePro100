package service_client.client;

import lombok.val;
import org.hamcrest.core.Every;
import org.junit.Test;
import service_client.data.request.TaskCreation;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class TaskClientTest {
    private TaskClient client = new TaskClient();

    @Test
    public void testGet() throws Exception {
        val task = client.get(1L);
        assertNotNull(task);
        System.out.println(task);
    }

    @Test
    public void testGetList() throws Exception {
        val tasks = client.getList();
        assertThat(tasks.isEmpty(), is(false));
    }

    @Test
    public void testGetListByLesson() throws Exception {
        val lessonId = 1L;
        val tasks = client.getListByLesson(lessonId);
        assertThat(tasks.isEmpty(), is(false));
        assertThat(tasks, Every.everyItem(hasProperty("lessonId", is(1L))));
    }

    @Test
    public void testAdd() throws Exception {
        val creation = new TaskCreation("name sqd", 1L, "asasf", "sdgsdgsdg");
        val task = client.add(creation);
        assertThat(task.getName(), is(creation.getName()));
    }
}