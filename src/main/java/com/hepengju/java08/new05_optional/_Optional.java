package com.hepengju.java08.new05_optional;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;

/**
 * 
 * Optional
 * 
 * <pre>
 *  说明: Optional<T> 是一个容器类, 代表一个值存在或不存在
 *       原来用null表示一个值不存在, 现在Optional可以更好的表达这个概念, 并且可以最大限度避免空指针异常
 *  
 *  常用方法:
 *      - 创建
 *          * Optional.empty()            创建一个空的Optional示例
 *          * Optional.of(T t)            创建一个Optional实例(t不能为空,否则NPE)
 *          * Optional.ofNullable(T t)    若t不为null创建Optional实例,否则创建空示例
 *          
 *      - 判断
 *          * isEmpty                                 是否为空
 *          * isPresent()                             是否存在
 *          * ifPresent(Consumer c)                   存在的话消费下
 *          * ifPresentOrElse(Consumer c,Runnable r)  存在的话消费下,否则做些其他的
 *          
 *      - 获取,过滤,映射,扁平化
 *          * get()                       获取值, 必须存在否则抛出NoSuchElementException
 *          * filter(Predicate p)         过滤
 *          * map(Function f)             如果有值对其处理并返回处理后的Optional,否则返回Optional.empty()
 *          * flatMap(Function f)         与map类似,要求返回值必须是Optional
 *          
 *      - 流处理
 *          * stream()                    流处理
 *          
 *      - 否则默认或异常
 *          * or(Supplier s)              如果调用对象包含值,返回调用对象,否则返回s创建的optional
 *          * orElse(T t)                 如果调用对象包含值,返回该值,否则返回t
 *          * orElseGet(Supplier s)       如果调用对象包含值,返回该值,否则返回s获取的值
 *          * orElseThrow()               不包含则抛出异常
 *          * orElseThrow(Supplier s)     不包含则抛出指定的异常
 *  
 *  代码展示:
 *      // 获取人 --> 车 --> 保险名
 *      private String getInsuranceNameByPerson2(Person person) {
 *          if(person != null) {
 *              Car car = person.getCar();
 *              if(car != null) {
 *                  Insurance insurance = car.getInsurance();
 *                  if(insurance != null) {
 *                      return insurance.getName();
 *                  }
 *              }
 *          }
 *          return "none";
 *      }
 *      
 *      private String getInsuranceNameByPerson3(Person person) {
 *          return Optional.ofNullable(person)
 *                  .map(Person::getCar)
 *                  .map(Car::getInsurance)
 *                  .map(Insurance::getName)
 *                  .orElse("none");
 *      }
 *  
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
        // 1.创建
        Optional<String> o1 = Optional.empty();
        //Optional<String> o2 = Optional.of(null);              //of方法的参数不能为空,否则NPE
        Optional<String> o3 = Optional.of("hepengju");
        Optional<String> o4 = Optional.ofNullable(null);
        Optional<String> o5 = Optional.ofNullable("hepengju");

        /*
         * 2.判断
         * [Optional.empty, Optional[hepengju], Optional.empty, Optional[hepengju]]
         *  [true, false, true, false]
         *  [false, true, false, true]
         */
        //print(o1, o2, o3, o4, o5); 
        print(o1, o3, o4, o5);
        print(o1.isEmpty(), o3.isEmpty(), o4.isEmpty(), o5.isEmpty());
        print(o1.isPresent(), o3.isPresent(), o4.isPresent(), o5.isPresent());

        // 3.获取,过滤,映射,扁平化
        //print(o1.get()); // NoSuchElementException
        print(o3.get());   // hepengju
        
        //[Optional.empty, Optional[hepengju], Optional.empty]
        Optional<String> o01 = o1.filter(s -> s.length() > 6);
        Optional<String> o02 = o3.filter(s -> s.length() > 6);
        Optional<String> o05 = o3.filter(s -> s.length() > 10);
        print(o01, o02, o05);
        
        //[Optional.empty, Optional[map:hepengju], Optional[map:hepengju]]
        o01 = o1.map(s -> "map:" + s);
        o02 = o3.map(s -> "map:" + s);
        o05 = o5.map(s -> "map:" + s);
        print(o01, o02, o05);
        
        //[Optional.empty, Optional[hepengju], Optional[hepengju]]
        o01 = o1.flatMap(s -> Optional.ofNullable(s));
        o02 = o3.flatMap(s -> Optional.ofNullable(s));
        o05 = o5.flatMap(s -> Optional.ofNullable(s));
        print(o01, o02, o05);
        
        // 4.流处理
        //[0, 1]
        long c1 = o1.stream().count();
        long c2 = o3.stream().count();
        print(c1,c2);
        
        // 5.否则
        //[default, hepengju]
        String s1 = o1.or(() -> Optional.of("default")).get();
        String s3 = o3.or(() -> Optional.of("default")).get();
        print(s1,s3);
        
        s1 = o1.orElse("default");
        s3 = o3.orElse("default");
        print(s1,s3);
        
        s1 = o1.orElseGet(() -> "default");
        s3 = o3.orElseGet(() -> "default");
        print(s1,s3);
        
        //o1.orElseThrow();  java.util.NoSuchElementException: No value present
        o3.orElseThrow();
        //o1.orElseThrow(RuntimeException::new); java.lang.RuntimeException
        o3.orElseThrow(RuntimeException::new);
    }

    /**
     * Optional的实际应用01: 单层空判断
     */
    @Test public void testOptional01(){
        Person person = new Person("孙悟空");
        print(getNameByPerson1(person));  // 孙悟空
        print(getNameByPerson2(person));  // 孙悟空
        print(getNameByPerson3(person));  // 孙悟空

        person = null;
        //print(getNameByPerson1(person));// NPE 空指针异常
        print(getNameByPerson2(person));  // Unkonwn --> 非空判断避免NPE
        print(getNameByPerson3(person));  // Unkonwn --> Optional语法避免NPE
    }

    // 获取人名
    private String getNameByPerson1(Person person){
        return person.getName();
    }
    private String getNameByPerson2(Person person){
        return person == null ? "Unkonwn" : person.getName();
    }
    private String getNameByPerson3(Person person){
        return Optional.ofNullable(person).map(Person::getName).orElse("Unknown");
    }
    
    /**
     * Optional的实际应用02: 多层深入空判断
     */
    @Test public void testOptional02(){
        Person p01 = new Person("唐玄奘");
        Person p02 = new Person("猪八戒");
        Car c01 = new Car("宝马");
        Insurance i01 = new Insurance("太平保险");
        c01.setInsurance(i01);
        p01.setCar(c01);
        
        print(getInsuranceNameByPerson2(p01)); // 太平保险
        print(getInsuranceNameByPerson3(p01)); // 太平保险
        print(getInsuranceNameByPerson2(p02)); // none
        print(getInsuranceNameByPerson3(p02)); // none
    }

    
    // 获取人 --> 车 --> 保险名
    private String getInsuranceNameByPerson2(Person person) {
        if(person != null) {
            Car car = person.getCar();
            if(car != null) {
                Insurance insurance = car.getInsurance();
                if(insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "none";
    }
    
    private String getInsuranceNameByPerson3(Person person) {
        return Optional.ofNullable(person)
                .map(Person::getCar)
                .map(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("none");
    }
    
    /**
     * Optional的实际应用03: 多层深入的flapMap
     * 
     * <pre>
     *  分析: 
     *      map的Optional是由jdk提供的,其内部可以包装用Optional.ofNullable包装避免空指针异常,最后可以使用orElse给出默认值
     *  而此处的Optional是用户自定义的, jdk只负责扁平化, 内部要求非空
     *      TODO: 为什么jdk的flapMap内部不处理非空呢? getInsuranceNameByPersonOptional2函数如何优雅处理呢?
     * </pre>
     */
    @Test public void testOptional03(){
        Person p01 = new Person("唐玄奘");
        Person p02 = new Person("猪八戒");
        Car c01 = new Car("宝马");
        Insurance i01 = new Insurance("太平保险");
        
        c01.setOinsurance(Optional.of(i01));
        p01.setOcar(Optional.of(c01));
        
        print(getInsuranceNameByPersonOptional1(p01));   // 太平保险
        print(getInsuranceNameByPersonOptional2(p01));   // 太平保险
        //print(getInsuranceNameByPersonOptional1(p02)); // NPE     
        print(getInsuranceNameByPersonOptional2(p02));   // none     
    }

    private String getInsuranceNameByPersonOptional1(Person p02) {
        return Optional.ofNullable(p02)
                .flatMap(Person::getOcar)
                .flatMap(Car::getOinsurance)
                .map(Insurance::getName)
                .orElse("none")
                ;
    }
    private String getInsuranceNameByPersonOptional2(Person p02) {
        return Optional.ofNullable(p02)
                .flatMap(p -> p.getOcar() == null ? Optional.empty() : p.getOcar())
                .flatMap(c -> c.getOinsurance() == null ? Optional.empty() : c.getOinsurance())
                .map(Insurance::getName)
                .orElse("none")
                ;
    }

    private void print(Object... objs) {
        System.out.println(Arrays.asList(objs));
    }
}
