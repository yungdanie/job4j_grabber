package ru.job4j.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean result = false;
        if (Objects.equals(parentName, Menu.ROOT)) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            result = true;
        }
        MenuItem parent = findItem(parentName);
        if (parent != null) {
            parent.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
            result = true;
        }
        return result;
    }

    private MenuItem findItem(String name) {
        DFSIterator iterator = new DFSIterator();
        MenuItem result = null;
        while (iterator.hasNext()) {
            MenuItem current = iterator.next().menuItem;
            if (current.getName().equals(name)) {
                result = current;
                break;
            }
        }
        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        DFSIterator iterator = new DFSIterator();
        Optional<MenuItemInfo> result = Optional.empty();
        while (iterator.hasNext()) {
            ItemInfo itemInfo = iterator.next();
            if (itemInfo.menuItem.getName().equals(itemName)) {
                result = Optional.of(new MenuItemInfo(itemInfo.menuItem, itemInfo.number));
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<>() {
            DFSIterator iterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                ItemInfo itemInfo = iterator.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }


    private static class SimpleMenuItem implements MenuItem {

        private final String name;
        private final List<MenuItem> children = new ArrayList<>();
        private final ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
