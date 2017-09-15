package com.zxc.thrift;

import com.zxc.thrift.service.RPCNetAuthService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

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
public class ThriftClient {

    private RPCNetAuthService.Client rpcNetAuthService;
    private TBinaryProtocol protocol;
    private TSocket transport;

    public RPCNetAuthService.Client getRpcNetAuthService() {
        return rpcNetAuthService;
    }

    public void open() throws TTransportException {
        transport.open();
    }

    public void close() {
        transport.close();
    }

    public ThriftClient() throws TTransportException {
        transport = new TSocket("localhost", 9090);
        protocol = new TBinaryProtocol(transport);

        TMultiplexedProtocol mp2 = new TMultiplexedProtocol(protocol, RPCNetAuthService.class.getSimpleName());
        rpcNetAuthService = new RPCNetAuthService.Client(mp2);
    }

    public static void main(String[] args) {
        try {
            ThriftClient client = new ThriftClient();
            client.open();
            boolean login = client.getRpcNetAuthService().login("hello", "kkk");
            System.out.println(login);
            login = client.getRpcNetAuthService().login("hello", "aaa");
            System.out.println(login);

            client.close();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
