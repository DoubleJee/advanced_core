package datastructure.tree;

// Comparable此接口定义 本类具有可比较性
public class Person implements Comparable<Person> {

    private int age;

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person person) {
        return age - person.age;
    }

    @Override
    public String toString() {
        return String.valueOf(age);
    }
}
