package com.apollo.commons.mq.utils.pojo;


/**
 * com.apollo.commons.mq.utils.pojo.MQCallerInfo <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/24.
 * @E-mail : tianlei@simpletour.com
 */
public class MQCallerInfo {
    private String className;
    private String method;
    private Integer line;
    private String ip;
    private static String ips;

    public MQCallerInfo(String className, String method, Integer line) {
        this.className = className;
        this.method = method;
        this.line = line;
        this.ip = ips;
    }

    public String getClassName() {
        return this.className;
    }

    public String getMethod() {
        return this.method;
    }

    public Integer getLine() {
        return this.line;
    }

    public String getIp() {
        return this.ip;
    }

    public static void main(String[] args) throws Exception {
    }

    static {
        try {
//            List<String> allIps = new ArrayList();
//            Iterator var1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
//
//            while(var1.hasNext()) {
//                NetworkInterface ni = (NetworkInterface)var1.next();
//                String ips = (String)Collections.list(ni.getInetAddresses()).stream().filter((address) -> {
//                    return address instanceof Inet4Address;
//                }).map(InetAddress::getHostAddress).collect(Collectors.joining(","));
//                allIps.add(ips);
//            }
//
//            ips = (String)allIps.stream().collect(Collectors.joining(","));
//            System.out.println("init ips" + ips);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
}
