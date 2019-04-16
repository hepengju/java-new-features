package com.hepengju.java08.new07_optional;

import java.util.Optional;

import com.hepengju.java08.new04_streamAPI.Person;
import org.junit.Test;

/**
 * 
 * Optional
 * 
 * <pre>
 *  说明: Optional<T> 是一个容器类, 代表一个值存在或不存在.
 *       原来用null表示一个值不存在, 现在Optional可以更好的表达这个概念, 并且可以最大限度避免空指针异常
 *  
 *  常用方法:
 *      * Optional.of(T t)            创建一个Optional实例
 *      * Optional.empty()            创建一个空的Optional示例
 *      * Optional.ofNullable(T t)    若t不为null创建Optional实例,否则创建空示例
 *      * isPresent()                 判断是否包含值
 *      * orElse(T t)                 如果调用对象包含值,返回该值,否则返回t
 *      * orElseGet(Supplier s)       如果调用对象包含值,返回该值,否则返回s获取的值
 *      * map(Function f)             如果有值对其处理并返回处理后的Optional,否则返回Optional.empty()
 *      * flatMap(Function f)         与map类似,要求返回值必须是Optional
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _Optional {

    /**
     * Optional的API
     */
    @Test public void testOptionalApi() {
        Optional<Integer> op = Optional.of(1);
        System.out.println(op.isPresent());      // true
        System.out.println(op.get());            // 1
        System.out.println(op.orElse(2));        // 1
        System.out.println(op.map(x -> x *2 ));  // Optional[2]
    }

    /**
     * Optional的使用
     */
    @Test public void testOptionalUse(){
        Person person = new Person("孙悟空");
        System.out.println(getNameByPerson1(person));
        System.out.println(getNameByPerson2(person));

        person = null;
        System.out.println(getNameByPerson1(person));
        System.out.println(getNameByPerson2(person));
    }

    /**
     * 嵌套的使用
     *
     *  <br> Person里面有Optional<Car>, Car里面有Optional<Insurance>
     *  <br> 即: 人不一定有车, 车不一定有保险; 通过人获取车的保险名之前需要多重==null判断
     */
    @Test public void testOptionalMany(){

        /*
        return Optional.ofNullable(person)
                       .flatMap(Person::getCar)
                       .flatMap(Car::getInsurance)
                       .map(Insurance::getName)
                       .orElse("Unkonwn");
         */
    }

    /**
     * 方法1
     */
    public String getNameByPerson1(Person person){
        if(person == null){
            return "Unkonwn";
        }

        return person.getName();
    }

    /**
     * 方法2
     */
    public String getNameByPerson2(Person person){
        return Optional.ofNullable(person).map(Person::getName).orElse("Unknown");
    }

    class Person{
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

    }
}
