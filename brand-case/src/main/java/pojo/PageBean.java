package pojo;

import java.util.List;

/**
 * 分页查询JavaBean
 */
public class PageBean<T> {
    //总记录数
    private Integer totalCount;

    //当前页数据 T为泛型，指定PageBean时创建
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
