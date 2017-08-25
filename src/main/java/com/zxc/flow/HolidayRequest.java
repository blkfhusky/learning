package com.zxc.flow;

import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/8/24.
 */
public class HolidayRequest {
    private static final String key = "holidayRequest";

    public static void main(String[] args) {

        //创建过程引擎
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("root")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = cfg.buildProcessEngine();
        //部署过程引擎
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml")
                .deploy();
        //验证自定义流程已被流程引擎知晓
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("找到自定义流程：" + processDefinition.getName() + "\n======= 开始测试 ========");

        //根据key启动过程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, getMap());

        //获取实际任务列表
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        System.out.println("你有：" + tasks.size() + " 个任务");
        for (Task task : tasks) {
            System.out.println("任务名：" + task.getName());
        }

        //选择任务
        System.out.println("选择任务:1");
        Task task = tasks.get(0);
        Map<String, Object> processVariables = taskService.getVariables(task.getId());
        System.out.println(processVariables.get("employee").toString() + " " +
                processVariables.get("nrOfHolidays").toString() + " " + processVariables.get("description").toString());

        //审批
        Map<String, Object> param = new HashMap<>();
        param.put("approved", true);
        taskService.complete(task.getId(), param);
        task = getTaskList(processEngine);

        //签名
        param.clear();
        param.put("employee", "CEO");
        taskService.complete(task.getId(), param);
        getTaskList(processEngine);
    }

    private static Task getTaskList(ProcessEngine processEngine) {
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().list();
        System.out.println("===== tasks =====");
        for (Task task : list) {
            System.out.println(task.getName());
        }
        System.out.println("===== tasks =====");
        return list.size() > 0 ? list.get(0) : null;
    }

    private static Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        String name = "Joe";
        String days = "3";
        String des = "illness";
        map.put("employee", name);
        map.put("nrOfHolidays", days);
        map.put("description", des);
        return map;
    }
}
