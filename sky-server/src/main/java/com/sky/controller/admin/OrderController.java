package com.sky.controller.admin;

import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminOrderController")
@RequestMapping("/admin/order")
@Slf4j
@Api(tags = "管理员-订单相关接口")
public class OrderController {

    @Autowired
    private OrderService orderService;



    /**
     * 分页查询订单
     * @param ordersPageQueryDTO
     * @return
     */
    @GetMapping("/conditionSearch")
    @ApiOperation("分页查询历史订单")
    public Result<PageResult> historyOrders(OrdersPageQueryDTO ordersPageQueryDTO){
        log.info("分页查询历史订单，页码：{}，每页数量：{}", ordersPageQueryDTO.getPage(), ordersPageQueryDTO.getPageSize());
        return Result.success(orderService.adminOrders(ordersPageQueryDTO));
    }

    @GetMapping("/details/{id}")
    @ApiOperation("根据id查询订单详情")
    public Result<OrderVO> getOrderDetails(@PathVariable Long id){
        log.info("根据id查询订单详情，id：{}", id);
        return Result.success(orderService.getOrderDetail(id));
    }

}
