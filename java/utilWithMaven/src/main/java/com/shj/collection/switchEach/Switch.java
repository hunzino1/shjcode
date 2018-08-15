package main.java.com.shj.collection.switchEach;

import java.util.*;

/**
 * 数组、list、set相互转换
 */
public class Switch {

    public static void main(String[] args) {
        /**
         * 1、数组转化为list set
         */
        int[] nums = new int[]{3, 1};
        String[] array = new String[] {"zhu", "wen", "tao"};
        /**
         * 1、Arrays.asLsit 不适用int整型数组
         * asList接受的参数是一个泛型的变长参数，而基本数据类型是无法泛型化的,所以数组作为一个整体被作为参数传递了。
         * 执行的结果是这个数组作为list的第一个也是唯一一个元素存在list中。
         * guava中有一个Ints.asList可以
         * 非基本类型的就可以使用Arrays.asList
         */
        //1、长度一直是1
        List num2list = Arrays.asList(nums);
        //基础类型数组zhuanlist
        List num2List = arrayToList(nums);
        //2、 String数组转List集合
        List<String> str2list = Arrays.asList(array);

        /**
         * 2、数组转Set
         *  数组先转成list，list转化成数组
         *  如果是基本类型的话，可以直接转换成set，也必须这么做，否则set也只会一直是1个元素
         */
        Set num2set = arrayToSet(nums);
        Set str2set = new HashSet(Arrays.asList(array));


        List list = new ArrayList();
        list.add(3);
        list.add(1);

        /**
         * 3 list转数组,set
         */
        list.toArray();
        new HashSet<Integer>(list);
        //或者
        new HashSet<Integer>().addAll(list);

        /**
         * 4 set 转list 数组
         */
        Set<Integer> set = new HashSet<>();
        set.add(1);

        set.toArray();
        //set 转 list
        list.clear();
        list.addAll(set);

    }

    //数组转list
    public static List arrayToList(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }

        return list;
    }

    public static Set listToSet(List list) {
        return new HashSet(list);
    }

    public static Set<Integer> arrayToSet(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        return set;
    }
}
