package com.zxc.flow;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Task;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().list();
        System.out.println(processDefinitionList.size());

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

        //获取执行历史
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().
                processInstanceId(processInstance.getId()).
                finished().
                orderByActivityId().
                desc().
                list();
        for (HistoricActivityInstance instance : list) {
            System.out.println("instance:" + instance.getActivityId() + ", name:" + instance.getActivityName() + ": " + instance.getDurationInMillis());
        }

        //生成流程图
        getGraph(repositoryService, processDefinition.getId());
    }

    private static Task getTaskList(ProcessEngine processEngine) {
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().list();
        System.out.println("===== tasks start =====");
        for (Task task : list) {
            System.out.println(task.getName());
        }
        System.out.println("===== tasks end =====");
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

    /**
     * 生成流程图
     *
     * @param repositoryService
     */
    private static void getGraph(RepositoryService repositoryService, String processDefinitionId) {
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        DefaultProcessDiagramGenerator generator = new DefaultProcessDiagramGenerator();
        List<String> list = new ArrayList<>();
        InputStream is = generator.generateDiagram(bpmnModel, "PNG", list);

        try {
            // 将输入流is写入文件输出流fos中
            int ch;
            OutputStream fos = new FileOutputStream("file-new.png");
            try {
                while ((ch = is.read()) != -1) {
                    fos.write(ch);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                //关闭输入流等（略）
                fos.close();
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
