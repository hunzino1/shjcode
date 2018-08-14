package com.shj.searchByPage.page;

/**
 * @create 2018/8/13
 * 分页条件
 */
public class PageCondition {
     /* 每页数量， 大于0小于等于200*/
    int count;
    /* 查询页码, 从1开始*/
    int curPage;

    public PageCondition(int count, int curPage) {
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurPage() {
        return this.curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    @Override
    public String toString() {
        return "PageCondition{" +
                "count=" + count +
                ", curPage=" + curPage +
                '}';
    }
}
