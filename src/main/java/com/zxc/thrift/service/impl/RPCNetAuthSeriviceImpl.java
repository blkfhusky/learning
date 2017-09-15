package com.zxc.thrift.service.impl;

import com.zxc.thrift.service.RPCNetAuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/9/15.
 * <p>
 * ------------------ _ooOoo_-------------------
 * ------------------o8888888o------------------
 * ------------------88" . "88------------------
 * ------------------(| -_- |)------------------
 * ------------------O\  =  /O------------------
 * ---------------____/`---'\____---------------
 * -------------.'  \\|     |//  `.-------------
 * ------------/  \\|||  :  |||//  \------------
 * -----------/  _||||| -:- |||||-  \-----------
 * -----------|   | \\\  -  /// |   |-----------
 * -----------| \_|  ''\---/''  |   |-----------
 * -----------\  .-\__  `-`  ___/-. /-----------
 * ---------___`. .'  /--.--\  `. . __----------
 * ------."" '<  `.___\_<|>_/___.'  >'"".-------
 * -----| | :  `- \`.;`\ _ /`;.`/ - ` : | |-----
 * -----\  \ `-.   \_ __\ /__ _/   .-` /  /-----
 * ======`-.____`-.___\_____/___.-`____.-'======
 * -------------------`=---='-------------------
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * -----------佛祖保佑 BUG DIE DIE DIE-----------
 * =============================================
 */
@Controller
@Slf4j
public class RPCNetAuthSeriviceImpl implements RPCNetAuthService.Iface {

    @Override
    public boolean login(String userAccount, String password) throws TException {
        log.debug("userAccount: " + userAccount + ", password: " + password);
        return "aaa".equals(password);
    }
}
