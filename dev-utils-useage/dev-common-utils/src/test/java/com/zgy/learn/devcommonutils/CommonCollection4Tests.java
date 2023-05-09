package com.zgy.learn.devcommonutils;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootTest
public class CommonCollection4Tests {

    /**
     * null 或者长度为0
     */
    @Test
    public void testCollectionEmpty() {
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        String[] strings = {"1", "2", "3"};
        System.out.println(CollectionUtils.isEmpty(stringList));
        // 不能直接操作数组, 数组使用ArraysUtils
        // System.out.println(CollectionUtils.isEmpty(strings));
        System.out.println(CollectionUtils.isEmpty(Arrays.asList(strings)));
    }

    @Test
    public void testCollectionEqual() {
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        List<String> stringList02 = new ArrayList<>();
        stringList02.add("1");
        stringList02.add("2");
        List<String> stringList03 = new ArrayList<>();
        stringList03.add("1");
        stringList03.add("2");
        stringList03.add("3");
        // stringList与stringList02内容是否相等
        System.out.println(CollectionUtils.isEqualCollection(stringList, stringList02));
        // stringList02是否为stringList03的一部分
        System.out.println(CollectionUtils.isSubCollection(stringList02, stringList03));
        // stringList02的元素是否也全部包含在stringList03之中, isSubCollection更好用
        System.out.println(CollectionUtils.containsAll(stringList03, stringList02));
    }

    @Test
    public void testCollectionCreate() {
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        List<String> stringList02 = new ArrayList<>();
        stringList02.add("1");
        stringList02.add("2");
        List<String> stringList03 = new ArrayList<>();
        stringList03.add("1");
        stringList03.add("2");
        stringList03.add("3");
        // 把后面的集合完全放在前面的集合之中, 形成一个新的集合
        System.out.println(CollectionUtils.addAll(stringList, stringList02)); // [1, 2, 1, 2]
        System.out.println(stringList);
        System.out.println(CollectionUtils.addAll(stringList, stringList03)); // [1, 2, 1, 2, 1, 2, 3]
        System.out.println(stringList);
    }

    @Test
    public void testCollectionCacal() {
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        List<String> stringList02 = new ArrayList<>();
        stringList02.add("1");
        stringList02.add("u");
        List<String> stringList03 = new ArrayList<>();
        stringList03.add("1");
        stringList03.add("hello");
        stringList03.add("mg");
        // 并
        Collection<String> union = CollectionUtils.union(stringList, stringList03);
        // 交
        Collection<String> intersection = CollectionUtils.intersection(stringList, stringList03);
        // 差
        // stringList - stringList03 和 stringList03 - stringList 不一样
        Collection<String> subtract01 = CollectionUtils.subtract(stringList, stringList03);
        Collection<String> subtract02 = CollectionUtils.subtract(stringList03, stringList);
        System.out.println(union);
        System.out.println(intersection);
        System.out.println(subtract01);
        System.out.println(subtract02);
    }


}
