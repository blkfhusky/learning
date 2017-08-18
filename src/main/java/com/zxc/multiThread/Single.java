package com.zxc.multiThread;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/8/17.
 */
public class Single {
    private static Single single;

    private Single() {}

    public static Single getInstance() {
        if(null == single){
            single =  new Single();
        }
        return single;
    }
}
