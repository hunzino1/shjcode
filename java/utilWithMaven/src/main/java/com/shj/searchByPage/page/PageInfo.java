package com.shj.searchByPage.page;

/**
 * @create 2018/8/13
 * 分页
 */
public class PageInfo {
    /* 总数量 */
   int total;
   /* 页数 */
   int pageCount;
   /* 每页数量 */
   int count;
   /* 第几页，从1开始 */
   int curPage;

   public PageInfo(int total, int pageCount, int count, int curPage) {

   }

   public int getTotal() {
      return this.total;
   }

   public void setTotal(int total) {
      this.total = total;
   }

   public int getPageCount() {
      return this.pageCount;
   }

   public void setPageCount(int pageCount) {
      this.pageCount = pageCount;
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
      return "PageInfo{" +
              "total=" + total +
              ", pageCount=" + pageCount +
              ", count=" + count +
              ", curPage=" + curPage +
              '}';
   }
}
