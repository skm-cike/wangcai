package org.test.see;

import java.util.ArrayList;

/**
 * Created by 陆华 on 16-4-12 上午9:25
 */
interface Parent {
    public int count(int i);
}
class Child implements  Parent {
    public int count(int i) {
        return i%9;
    }
}
public class See {
    public static void main(String[] args) {
        Child p = new Child();
        System.out.println(p.count(20));
    }
}
