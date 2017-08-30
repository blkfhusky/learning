package com.zxc.flowable.boot;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Task;
import org.flowable.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/8/29.
 */
@Service
@Transactional
public class PhotoService {

    private final ProcessEngine processEngine;
    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(ProcessEngine processEngine, RuntimeService runtimeService, TaskService taskService, PhotoRepository photoRepository) {
        this.processEngine = processEngine;
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.photoRepository = photoRepository;
    }

    public void processPhoto(Long photoId) {
        System.out.println("process photo #" + photoId);
    }

    public void launchPhotoProcess(String... photoLabels) {
        TaskQuery taskQuery = taskService.createTaskQuery();

        List<Photo> photos = new ArrayList<>();
        for (String label : photoLabels) {
            Photo photo = this.photoRepository.save(new Photo(label));
            photos.add(photo);
        }

        Map<String, Object> procVars = new HashMap<>();
        procVars.put("photos", photos);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("dogeProcess", procVars);
        showTasks(taskQuery.list());

        for (int i = 0; i < 6; i++) {

            List<Execution> waitExecutions = runtimeService.createExecutionQuery().activityId("wait").list();
            System.out.println("--> #executions = " + waitExecutions.size());
            for (Execution execution : waitExecutions) {
                System.out.println("execution:" + execution.getId() + ": " + execution.getName());
                runtimeService.trigger(execution.getId());
            }
        }
        showTasks(taskQuery.list());

        Task reviewTask = taskQuery.processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(reviewTask.getId(), Collections.singletonMap("approved", false));
        showTasks(taskQuery.list());

        long count = runtimeService.createProcessInstanceQuery().count();
        System.out.println("Proc count " + count);
        showTasks(taskQuery.list());

//        FlowableUtil.getGraph(processEngine.getRepositoryService(), processInstance.getProcessDefinitionId());
    }


    /**
     * 展示所有task
     *
     * @param taskList
     */

    private void showTasks(List<Task> taskList) {
        System.out.println("\n*********** task query start ***********");
        for (Task task : taskList) {
            System.out.println("task: " + task.getId() + ": " + task.getName());
        }
        System.out.println("*********** task query end ***********\n");
    }

}
