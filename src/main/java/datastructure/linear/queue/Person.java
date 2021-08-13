package datastructure.linear.queue;

public class Person implements Comparable<Person> {

    private String name;

    private Integer height;

    public Person(String name, Integer height) {
        this.name = name;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        // 身高越高的人越优先
        // 当前height高的人 大于 传入height低的人
        return this.height - o.height;
    }
}
