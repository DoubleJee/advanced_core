package design_pattern.iterator.component;

public abstract class MenuComponent {
    public void add(MenuComponent menuComponent){

    }

    public void remove(MenuComponent menuComponent){

    }

    public MenuComponent getChild(int i){
        return null;
    }

    public String getName() {
        return null;
    }
    public String getDescription() {
        return null;
    }
    public double getPrice() {
        return 0;
    }
    public boolean isVegetarian() {
        return true;
    }

    public void print(){

    }

}
