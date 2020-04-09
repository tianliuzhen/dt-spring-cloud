package com.aaa.accountserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author IT云清
 */
@Mapper
@Repository
public interface AccountDao {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    @Update({"   UPDATE account SET residue = residue - #{money},used = used + #{money} where user_id = #{userId}; "})
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
