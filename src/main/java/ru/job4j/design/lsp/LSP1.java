package ru.job4j.design.lsp;

/**
 * В данном классе есть подкласс который наследуется от внешнего. При этом он нарушает контракт меняя постусловие,
 * это нарушает принцип LSP.
 */

public class LSP1 {
    public boolean cond;
    public int count;

    public LSP1(boolean cond, int count) {
        this.cond = cond;
        this.count = count;
    }

    protected int doSomeAction() {
        int res = 0;
        if (count >= 10 && cond) {
            res = 1;
        }
        return res;
    }

    public class LSP2 extends LSP1 {

        public LSP2(boolean cond, int count) {
            super(cond, count);
        }

        @Override
        protected int doSomeAction() {
            int res = 0;
            if (count >= 20 && cond) {
                res = 1;
            }
            return res;
        }

    }
}


