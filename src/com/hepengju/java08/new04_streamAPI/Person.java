package com.hepengju.java08.new04_streamAPI;

/**
 * 人
 * 
 * @author hepengju
 *
 */
 public class Person implements Comparable<Person>{
        String name;
        String pyFirst;
        
        int age;
        double salary;
        
        public Person(String name, String pyFirst, int age, double salary) {
            super();
            this.name = name;
            this.pyFirst = pyFirst;
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
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public double getSalary() {
            return salary;
        }
        public void setSalary(double salary) {
            this.salary = salary;
        }

        public String getPyFirst() {
            return pyFirst;
        }


        public void setPyFirst(String pyFirst) {
            this.pyFirst = pyFirst;
        }


        @Override
        public int compareTo(Person o) {
            int nameCompare = this.name.compareTo(o.name);
            return nameCompare == 0 ? (int)(this.salary - o.salary) : nameCompare;
        }

        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + ", salary=" + salary + "]";
        }
        
    }