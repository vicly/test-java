package vic.test.jersey;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Task biz process
 *
 * @author Vic Liu
 */
public class Tasks {

    private static final AtomicInteger maxId = new AtomicInteger();
    private static final Map<String, Task> tasks = Maps.newHashMap();
    static {
        Task task;
        int id;

        task = new Task();
        id = maxId.addAndGet(1);
        task.setId(String.valueOf(id));
        task.setName("task " + id);
        tasks.put(task.getId(), task);

        task = new Task();
        id = maxId.addAndGet(1);
        task.setId(String.valueOf(id));
        task.setName("task " + id);
        tasks.put(task.getId(), task);
    }

    public Optional<Task> getTask(String id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public List<Task> findAll() {
        return Lists.newArrayList(tasks.values());
    }

    public void addTask(Task newTask) {
        int id = maxId.addAndGet(1);
        newTask.setId(String.valueOf(id));
        daoInsertTask(newTask);
    }

    private static void daoInsertTask(Task task) {
        if (task.getId() == null || task.getId().trim().isEmpty()) {
            throw new TechException("Data integration issue: missing task ID");
        }
        if (tasks.containsKey(task.getId())) {
            // Concurrency issue: added by another thread
            throw new TechException("Task ID " + task.getId() + " has been occupied");
        }
        tasks.put(task.getId(), task);
    }
}
