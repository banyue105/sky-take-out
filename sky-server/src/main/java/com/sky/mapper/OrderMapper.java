package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单
     * @param order
     */
    void insert(Orders order);

    /**
     * 根据订单号和用户id查询订单
     * @param orderNumber
     * @param userId
     */
    @Select("select * from orders where number = #{orderNumber} and user_id= #{userId}")
    Orders getByNumberAndUserId(String orderNumber, Long userId);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);

    /**
     * 根据条件查询订单历史
     * @param ordersPageQueryDTO
     * @return
     */
    Page<OrderVO> historyOrders(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    Orders getOrderById(Long id);

    /**
     * 根据状态查询订单数量
     * @param confirmed
     * @return
     */
    @Select("select count(*) from orders where status = #{confirmed}")
    Integer getConfirmedNum(Integer confirmed);
    @Select("select count(*) from orders where status = #{deliveryInProgress}")
    Integer getDeliveryInProgressNum(Integer deliveryInProgress);
    @Select("select count(*) from orders where status = #{toBeConfirmed}")
    Integer getToBeConfirmedNum(Integer toBeConfirmed);
}
