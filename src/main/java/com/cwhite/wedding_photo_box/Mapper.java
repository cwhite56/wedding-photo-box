package com.cwhite.wedding_photo_box;

public interface Mapper<A, B> {
    public B mapTo(A a);
    public A mapFrom(B b);
}
