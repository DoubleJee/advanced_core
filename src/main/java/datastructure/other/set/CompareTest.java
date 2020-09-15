package datastructure.other.set;

import datastructure.TimeTool;
import datastructure.linear.List;
import datastructure.linear.array.ArrayList;

// 集合性能比较
public class CompareTest {


    static void compare(List<Integer> list, Set<Integer> set) {

        for (int i = 0; i < list.size(); i++) {
            set.add(list.get(i));
        }

        for (int i = 0; i < list.size(); i++) {
            set.contains(list.get(i));
        }

        for (int i = 0; i < list.size(); i++) {
            set.remove(list.get(i));
        }

    }

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }

        TimeTool.checkTime("ListSet", new TimeTool.Task() {
            @Override
            public void execute() {
                compare(list,new ListSet<>());
            }
        });

        TimeTool.checkTime("TreeSet", new TimeTool.Task() {
            @Override
            public void execute() {
                compare(list,new TreeSet<>());
            }
        });

        // 红黑树集合性能优于链表集合
    }
}
