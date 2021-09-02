package algorithms.sorting.comparison.model;

public class Student implements Comparable<Student> {

    private int age;

    private double score;

    public Student(int age, double score) {
        this.age = age;
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public double getScore() {
        return score;
    }

    @Override
    public int compareTo(Student o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", score=" + score +
                '}';
    }
}
