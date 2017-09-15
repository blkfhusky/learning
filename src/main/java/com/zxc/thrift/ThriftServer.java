package com.zxc.thrift;

import com.zxc.thrift.service.RPCNetAuthService;
import com.zxc.thrift.service.impl.RPCNetAuthSeriviceImpl;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
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
public class ThriftServer {

    public static void main(String[] args) {
        startRPCServer();
    }

    private static void startRPCServer() {
        try {
            //设置协议工厂为TBinaryProtocol.Factory
            TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();
            //关联处理器与Hello服务的实现
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            TServerSocket t = new TServerSocket(9090);
            TThreadPoolServer server = new TThreadPoolServer(new TThreadPoolServer.Args(t).processor(processor));
            processor.registerProcessor(RPCNetAuthService.class.getSimpleName(), new RPCNetAuthService.Processor<RPCNetAuthService.Iface>(new RPCNetAuthSeriviceImpl()));
            System.out.println("the serveris started and is listening at 9090...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
