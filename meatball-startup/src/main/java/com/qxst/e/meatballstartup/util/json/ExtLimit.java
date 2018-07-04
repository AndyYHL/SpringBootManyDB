package com.qxst.e.meatballstartup.util.json;

/**
 * Created by Administrator on 2017/5/24.
 */
public class ExtLimit {

    private Integer pagestart; //开始的条数
    private int pagesize;  //返回数据条数
    private int pageindex; //当前请求的页数
    private Integer count; //查询结果的总数量
    private String sort; //排序的字段
    private String dir; //排序方式

    public Integer getPagestart() {
        return pagestart;
    }

    public void setPagestart(Integer pagestart) {
        this.pagestart = pagestart;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPageindex() {
        return pageindex;
    }

    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
