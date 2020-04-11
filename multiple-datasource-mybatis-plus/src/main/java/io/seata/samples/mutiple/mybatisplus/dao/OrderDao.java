package io.seata.samples.mutiple.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.seata.samples.mutiple.mybatisplus.common.order.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author HelloWood
 */
@Mapper
@Repository
public interface  OrderDao extends BaseMapper<Order> {

}
