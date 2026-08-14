package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    /**
     * 查找超时订单
      * @param status
     * @param time
     * @return
     */
    @Select("select * from orders where status = #{status} and order_time < #{time}")
    List<Orders> updateOrderStatus(Integer status, LocalDateTime time);

    /**
     * 根据条件查询订单金额总和
     * @param map
     * @return
     */
    Double sumByMap(Map map);

    /**
     * 根据时间范围和状态查询订单数量
     * @param beginTime
     * @param endTime
     * @param status
     * @return
     */
    Integer getOrderCount(LocalDateTime beginTime, LocalDateTime endTime, Integer status);

    /**
     * 根据时间范围查询订单数量
     * @param beginTime
     * @param endTime
     * @return
     */
    List<GoodsSalesDTO> top10(LocalDateTime beginTime, LocalDateTime endTime);

    /**
     * 根据条件查询订单数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
