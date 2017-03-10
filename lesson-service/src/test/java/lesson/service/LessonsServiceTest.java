package lesson.service;

import lesson.AbstractTest;
import lesson.request.LessonCreation;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Transactional
public class LessonsServiceTest extends AbstractTest {

    @Autowired
    private LessonsService lessonService;

    @Test
    public void testAdd() throws Exception {
        val name = "test name";
        val creation = new LessonCreation(name, "test desc");
        val entity = lessonService.add(creation);
        assertNotNull(entity);
        assertEquals(entity.getName(), name);
    }

    @Test
    public void testGetList() throws Exception {
        val list = lessonService.getList();
        assertThat(list, is(not(empty())));
    }

    @Test
    public void testDelete() throws Exception {
        val creation = new LessonCreation("asfsa", "test desc");
        val entity = lessonService.add(creation);
        assertNotNull(lessonService.get(entity.getId()));
        lessonService.delete(entity);
        assertNull(lessonService.get(entity.getId()));
    }

    @Test
    public void testGet() throws Exception {
        assertNotNull(lessonService.get(1L));
    }

    @Test
    public void testUpdate() throws Exception {
        val oldName = "old name";
        val newName = "new name";
        val creation = new LessonCreation(oldName, "test desc");
        val entity = lessonService.add(creation);
        assertThat(entity.getName(), is(oldName));
        entity.setName(newName);
        val updatedEntity = lessonService.update(entity);
        assertThat(updatedEntity.getName(), is(newName));

    }
}