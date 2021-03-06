package com.wangsh.hqlGenerate.vo;

/**
 * @author wangsh
 * @description 之间
 * @date 2019-8-28
 * @Copyright
 */
public class BetweenVo<T extends Comparable> {
    private T begin;
    private T end;

    public BetweenVo(T begin, T end) {
        this.begin = begin;
        this.end = end;
    }

    public T getBegin() {
        return begin;
    }

    public void setBegin(T begin) {
        this.begin = begin;
    }

    public T getEnd() {
        return end;
    }

    public void setEnd(T end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return " begin : " + this.begin.toString() + " end : " + this.end.toString();
    }
}
