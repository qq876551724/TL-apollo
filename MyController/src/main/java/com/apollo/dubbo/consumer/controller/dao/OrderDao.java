package com.apollo.dubbo.consumer.controller.dao;

import com.apollo.entity.order.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * com.example.dao.OrderDao is written for <br>
 *
 * @Author : tianlei
 * @Create : 2017/8/22.
 * @E-mail : 876551724@qq.com
 */
@Repository
@Transactional
public interface OrderDao extends CrudRepository<Order,Long> {

    @Query("select t from Order t where t.id = ?1")
    Order findById(int id);

    @Query("select t from Order t order by t.id desc")
    List<Order> getList();

    @Query("select t from Order t order by t.id desc")
    List<Order> noCache();
}
