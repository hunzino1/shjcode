package com.shj.searchByPage.search;

import com.shj.searchByPage.page.PageCondition;
import com.shj.searchByPage.page.PageInfo;
import com.zhe800.utils.TradeUtils;

import java.text.MessageFormat;

/**
 * @create 2018/8/13
 */
public class PageUtils {
    public static final String POINT = ".";
    public static String getMessage(String format, Object... keys) {
        MessageFormat form = new MessageFormat(format);
        return form.format(keys);
    }

    /**
     * 分页查询条件初始化
     *
     * @param pageCondition
     */
    public static PageCondition initPageInfo(PageCondition pageCondition) {
        if (pageCondition == null) {
            pageCondition = new PageCondition(20, 1);
        } else if (TradeUtils.isEmpty(pageCondition.getCount())) {
            pageCondition.setCount(20);
        } else if (TradeUtils.isEmpty(pageCondition.getCurPage())) {
            pageCondition.setCurPage(1);
        }
        return pageCondition;
    }

    /**
     * 通用分页信息
     *
     * @param total
     * @param pageCondition
     * @return
     */
    public static PageInfo getPageInfo(int total, PageCondition pageCondition) {
        int pageCount = 0;
        if (total % pageCondition.getCount() == 0) {
            pageCount = total / pageCondition.getCount();
        } else {
            pageCount = total / pageCondition.getCount() + 1;
        }
        return new PageInfo(total, pageCount, pageCondition.getCount(), pageCondition.getCurPage());
    }

    /**
     * 获取文件类型（后缀）
     * @param path
     * @return
     */
    public static String getPostfix(String path) {
        if (TradeUtils.isEmpty(path)) {
            return null;
        }
        if (path.contains(POINT)) {
            return path.substring(path.lastIndexOf(POINT) + 1, path.length());
        }
        return null;
    }

}
