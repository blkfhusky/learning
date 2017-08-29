package com.zxc.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/8/25.
 */
public class CallExternalSystemDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        Object employee = execution.getVariable("employee");
        System.out.println(employee.toString() + "要请假" + execution.getVariable("nrOfHolidays") + "天");
    }
}
