package com.zxc.flowable.boot.interation;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.spring.integration.FlowableInboundGateway;
import org.flowable.spring.integration.IntegrationActivityBehavior;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/8/30.
 */
@SpringBootApplication
//@ImportResource(locations = "classpath*:/processes/integration.bpmn20.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    AnalysingService analysingService() {
        return new AnalysingService();
    }

    @Bean
    FlowableInboundGateway inboundGateway(ProcessEngine processEngine) {
        return new FlowableInboundGateway(processEngine, "customerId", "projectId");
    }


    @Bean
    IntegrationActivityBehavior flowableDelegate(FlowableInboundGateway flowableInboundGateway) {
        return new IntegrationActivityBehavior(flowableInboundGateway);
    }


    public static class AnalysingService {
        private final AtomicReference<String> stringAtomicReference = new AtomicReference<>();

        public void dump(String projectId) {
            this.stringAtomicReference.set(projectId);
        }

        public AtomicReference<String> getStringAtomicReference() {
            return stringAtomicReference;
        }
    }

    @Bean
    IntegrationFlow inboundProcess(FlowableInboundGateway inboundGateway) {
        return IntegrationFlows
                .from(inboundGateway)
                .handle(new GenericHandler<DelegateExecution>() {

                    @Override
                    public Object handle(DelegateExecution delegateExecution, Map<String, Object> map) {
                        return MessageBuilder.withPayload(delegateExecution)
                                .setHeader("projectId", "3243549")
                                .copyHeaders(map).build();
                    }
                }).get();

    }


    @Bean
    CommandLineRunner init(
            final AnalysingService analysingService,
            final RuntimeService runtimeService
    ) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                String integrationGatewayProcess = "integrationGatewayProcess";
                runtimeService.startProcessInstanceByKey(integrationGatewayProcess, Collections.singletonMap("customerId", 232L));
                System.out.println("ProjectId = " + analysingService.getStringAtomicReference().get());
            }
        };
    }
}
