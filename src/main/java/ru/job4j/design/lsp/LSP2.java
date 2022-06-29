package ru.job4j.design.lsp;

/**
 * В данном классе есть подкласс который наследуется от внешнего. При этом он нарушает контракт меняя условие возврата,
 * тем самым нарушая инвариантность, это нарушает принцип LSP.
 */

public class LSP2 {
    public boolean cond;
    public int count;

    public LSP2(boolean cond, int count) {
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

    public class LSP3 extends LSP2 {

        public LSP3(boolean cond, int count) {
            super(cond, count);
        }

        @Override
        protected int doSomeAction() {
            return 1;
        }

    }
}
