package com.aaa.providerserver.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author IT云清
 */
@FeignClient(value = "order-server")
@Repository
public interface OrderApi {

    /**
     * 修改订单金额
     * @param userId
     * @param money
     * @param status
     * @return
     */
    @RequestMapping("/order/update")
    String update(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money, @RequestParam("status") Integer status);
}
