package service.impl;

import mapper.BrandMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Brand;
import pojo.PageBean;
import service.BrandService;
import util.SqlSessionFactoryUtils;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    //1.创建sqlSessionFactory
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Brand> selectAll() {
        //2.创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取brand mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        List<Brand> brands = mapper.selectAll();

        //5.释放资源
        sqlSession.close();

        return brands;


    }

    @Override
    public void add(Brand brand) {
        //2.创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取brand mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.add(brand);

        //5.提交事务
        sqlSession.commit();

        //6.释放资源
        sqlSession.close();

    }

    /**
     * 修改
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        //2.创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取brand mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.update(brand);

        //5.提交事务
        sqlSession.commit();

        //6.释放资源
        sqlSession.close();

    }

    /**
     * 删除单个数据
     * @param id
     */
    @Override
    public void delete(int id) {
        //2.创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取brand mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.deleteById(id);

        //5.提交事务
        sqlSession.commit();

        //6.释放资源
        sqlSession.close();
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteByIds(int[] ids) {
        //2.创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取brand mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.deleteByIds(ids);

        //5.提交事务
        sqlSession.commit();

        //6.释放资源
        sqlSession.close();
    }

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //2.创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取brand mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //5.调用方法
        //查询当前页数据
        List<Brand> rows = mapper.selectByPage(begin, size);
        //查询总记录数
        int totalCount = mapper.selectTotalCount();

        //6.封装bean
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //7.释放资源
        sqlSession.close();

        return pageBean;


    }

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //2.创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取brand mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //处理brand条件 加上模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0){
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0){
            brand.setCompanyName("%"+companyName+"%");
        }

        //5.调用方法
        //查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin,size,brand);
        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);

        //6.封装bean
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //7.释放资源
        sqlSession.close();

        return pageBean;
    }

}
