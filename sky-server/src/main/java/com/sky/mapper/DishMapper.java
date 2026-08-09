package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 插入菜品数据
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id查询菜品和分类数据
     * @param id
     * @return
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 删除菜品数据
     * @param id
     */
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    /**
     * 修改菜品数据
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @Select("select * from dish where category_id = #{categoryId}")
    List<DishVO> list(Long categoryId);

    /**
     * 获取起售中的菜品
     * @param dish
     * @return
     */
    @Select("select * from dish where status = 1 and category_id = #{categoryId}")
    List<Dish> onSaleList(Dish dish);
}
