package com.apollo.dubbo.consumer.controller.order;

import com.apollo.commons.locks.DistributedLock;
import com.apollo.commons.locks.ZkConfig;
import com.apollo.commons.locks.enums.LockName;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * OrderController <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/6.
 * @E-mail : tianlei@simpletour.com
 */
@RestController
@RequestMapping("/")
public class OrderController {
    @Resource(name = "zkConfig")
    ZkConfig zkConfig;
    @RequestMapping("getList")
    public Object getList(){
        String time=null;
        try {
            //初始化库存锁
            DistributedLock lock = new DistributedLock(zkConfig,LockName.InventoryLock.getValue());
            lock.lock();
            time = System.currentTimeMillis()+"";
            System.out.println("===Thread " + time + " running");
            Thread.sleep(2000);
            lock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }
}
