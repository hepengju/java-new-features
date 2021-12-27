package com.hepengju.java08.new08_comparator;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * 比较器: java8中提供了一系列默认方法和静态方法处理 Comparator
 * 
 * @author hepengju
 */
public class _Comparator {

    /**
     * 排序
     */
    @Test public void testComparator() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("hepengju", 28, 20000.0));
        personList.add(new Person("lisi"    , 44, 40000.0));
        personList.add(new Person("wangwu"  , 55, 50000.0));
        personList.add(new Person("zhaoliu" , 66, 60000.0));
        personList.add(new Person("zhangsan", 33, 33333.0));
        personList.add(new Person("zhangsan", 23, 30000.0));
        
        
        // 1. sort的参数为null时, 必须实现Comparable接口才行
        // personList.sort(null);                      
        
        // 2. 按照名字排序
        Comparator<Person> c1 = comparing(Person::getName);
        personList.sort(c1);
        
        // 3. 先按照名字排序, 名字一样再按照年龄排序, 年龄一样再按照薪资排序
        Comparator<Person> c2 = comparing(Person::getName).thenComparing(Person::getAge).thenComparing(Person::getSalary);
        personList.sort(c2);
        
        // 4. 处理所有空值问题(null都到最后)
        personList.add(null);
        personList.add(new Person(null, 33, 30000.0));
        personList.add(new Person("zhangsan", null, 30000.0));
        personList.add(new Person("zhangsan", 33, null));
        personList.add(new Person(null, null, null));
        
        Comparator<Person> c3 = nullsLast(comparing(Person::getName  , nullsLast(naturalOrder()))
                                     .thenComparing(Person::getAge   , nullsLast(naturalOrder()))
                                     .thenComparing(Person::getSalary, nullsLast(naturalOrder())));
        personList.sort(c3);
        personList.forEach(System.out::println);
        
        /*
         * 结果如下: 
            Person [name=hepengju, age=28, salary=20000.0]
            Person [name=lisi, age=44, salary=40000.0]
            Person [name=wangwu, age=55, salary=50000.0]
            Person [name=zhangsan, age=23, salary=30000.0]
            Person [name=zhangsan, age=33, salary=33333.0]
            Person [name=zhangsan, age=33, salary=null]
            Person [name=zhangsan, age=null, salary=30000.0]
            Person [name=zhaoliu, age=66, salary=60000.0]
            Person [name=null, age=33, salary=30000.0]
            Person [name=null, age=null, salary=null]
            null
         */
    }
    
}
