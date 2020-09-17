package datastructure.hash;

import java.util.Objects;

public class Person {

    private int age;
    private double height;
    private String name;
    // 自定义对象
    private Object object;

    public Person(int age, double height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", height=" + height +
                ", name='" + name + '\'' +
                '}';
    }

    // 对象的每个信息算出来hash值，再将每个信息的hash值，按照字符串的方式去计算最终的hash值
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = hashCode + 31 * Integer.hashCode(age);
        hashCode = hashCode + 31 * Double.hashCode(height);
        hashCode = hashCode + 31 * (name == null ? 0 : name.hashCode());
        hashCode = hashCode + 31 * (object == null ? 0 : object.hashCode());
        return hashCode;
    }


    // 用来比较两个对象是否相等
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        // 严格按照Class判断
        if (obj == null || obj.getClass() != this.getClass()) return false;

        // 下面这种如果是Person的子类 instanceof 也会通过
//        if (obj == null || !(obj instanceof Person)) return false;
        Person p2 = (Person) obj;
        return age == p2.age && height == p2.height && Objects.equals(name, p2.name);
    }
}
