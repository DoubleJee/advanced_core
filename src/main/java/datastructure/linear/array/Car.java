package datastructure.linear.array;

public class Car {
    private int chimney;
    private String color;

    public Car() {
    }

    public Car(int chimney, String color) {
        this.chimney = chimney;
        this.color = color;
    }

    public int getChimney() {
        return chimney;
    }

    public void setChimney(int chimney) {
        this.chimney = chimney;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "chimney=" + chimney +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Car -> finalize");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Car car = (Car) obj;
        return this.color.equals(car.color) && this.chimney == car.chimney;
    }
}
