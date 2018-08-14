#分页查询总结
代码并不可用，只是一个思路，分页查询条件PageCondition和分页信息PageInfo定义是可以直接用的。
##1、首先初始化分析查询条件
initPageInfo， 防止不合法的分页查询，对不合法的数据进行调整，调整为默认值；
##2、获取总的记录数
如果没有符合的记录，直接返回成功。
##3、存在记录
数据库查询：除了表字段查询条件之外，最后还要加上分页信息。
使用setOrderByClause，数据库的limit位移，基于此分页查询条件查询。
传入的pageCond为空，就是查询所有符合条件的记录。

        //按照位移查询，获取分页查询条件
            if (pageCond != null) {
                int curPage = pageCond.getCurPage();
                int count = pageCond.getCount();
                //位移X，查询Y个
                example.setOrderByClause("id desc limit " + (curPage - 1) * count + "," + count);
            }
            
##4、查询成功
返回结果中要加入当前页面信息。PageInfo