package ru.job4j.design.dip;

import java.util.ArrayList;
import java.util.List;

public class DIP3 {
    public static class UsageOfList {
        public void useArrayList(ArrayList<Integer> list) {
            List<Integer> list1 = new ArrayList<>(list);
        }

        public void useHashList(ArrayList<Integer> list) {
            /**
             * List<Integer> list1 = new HashSet<>(list);
             * Нарушение принципа DIP, если бы мы использовали абстракцию List а не реализацию ArrayList мы бы смогли
             * воспользоваться присвоением к листу.
             */

        }
    }
}
