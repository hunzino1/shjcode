package com.shj.searchByPage.dao;

import com.shj.searchByPage.page.PageCondition; /**
 * @create 2018/8/14
 */
public class SearchDao {

    public int countRecords(int condition) {
        //分页置成空，就是所有记录数目
        return 0;
    }

    public void searchRecords(int condition, PageCondition pageCond) {
        //根据分页查询条件返回记录
        if (pageCond != null) {

        }
    }

    public int searchCondition(PageCondition pageCond) {
        //这是查询条件
        int example = 0;

        //按照位移查询，获取分页查询条件
        if (pageCond != null) {
            int curPage = pageCond.getCurPage();
            int count = pageCond.getCount();
//            example.setOrderByClause("id desc limit " + (curPage - 1) * count + "," + count);
        }
        return example;
    }
}
