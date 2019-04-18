package com.hepengju.java08.new07_collector;

import java.util.Comparator;

/**
 * 人
 * 
 * @author hepengju
 *
 */
 public class Person implements Comparable<Person>{
        private String name;
        private Integer age;
        private Double salary;
        
        public Person(String name, Integer age, Double salary) {
            super();
            this.name = name;
            this.age = age;
            this.salary = salary;
        }
        
        
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Person other = (Person) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            return true;
        }



        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getAge() {
            return age;
        }
        public void setAge(Integer age) {
            this.age = age;
        }
        public Double getSalary() {
            return salary;
        }
        public void setSalary(Double salary) {
            this.salary = salary;
        }
        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + ", salary=" + salary + "]";
        }

        @Override
        public int compareTo(Person o) {
            Comparator<Person> com = Comparator.nullsFirst(Comparator.comparing(Person::getName));
            return com.compare(this,o);
        }
        
        
    }