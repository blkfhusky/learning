namespace java com.zxc.thrift
service RPCNetAuthSerivice{
    bool login(1:string userAccount, 2:string password)
}