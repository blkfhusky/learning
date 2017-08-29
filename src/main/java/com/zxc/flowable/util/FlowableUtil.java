package com.zxc.flowable.util;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.RepositoryService;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/8/29.
 */
public class FlowableUtil {

    public static void getGraph(RepositoryService repositoryService, String processDefinitionId) {
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
