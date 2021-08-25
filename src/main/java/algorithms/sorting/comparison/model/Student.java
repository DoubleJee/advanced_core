package algorithms.sorting.comparison.model;

public class Student implements Comparable<Student> {

    private double score;

    private String name;

    public Student(double score, String name) {
        this.score = score;
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student o) {
        return (int) (this.score - o.score);
    }

    @Override
    public String toString() {
        return "Student{" +
                "score=" + score +
                ", name='" + name + '\'' +
                '}';
    }
}
