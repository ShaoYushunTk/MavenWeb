package service;

import org.apache.ibatis.annotations.Param;
import pojo.Brand;
import pojo.PageBean;

import java.util.List;

public interface BrandService {

    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();

    /**
     * 添加
     * @param brand
     */
    void add(Brand brand);

    /**
     * 修改
     * @param brand
     */
    void update(Brand brand);

    /**
     * 删除单个数据
     * @param id
     */
    void delete(int id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("its")int[] ids);

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);
}
