package com.shj.searchByPage.search;

import com.shj.searchByPage.dao.SearchDao;
import com.shj.searchByPage.page.PageCondition;
import com.shj.searchByPage.page.PageInfo;

/**
 * @create 2018/8/13
 */
public class Search {
    private static SearchDao searchDao;

    public void shopScoreHistoryList(int condition, PageCondition pageCond){
        /**
         * 1、初始化分页信息
         */
        pageCond = PageUtils.initPageInfo(pageCond);

        /**
         *  2、业务逻辑处理
         */
        search(condition, pageCond);
    }

    private void search(int condition, PageCondition pageCond) {
        /**
         * 3：分页查询(总页数)
         */
        int total = searchDao.countRecords(condition);
        if (total == 0) {
            //成功
        }

        searchDao.searchRecords(condition, pageCond);

        /**
         * 4、查询完毕之后在返回值中添加上分页信息
         */
        PageInfo pageInfo = null;
    }

}
