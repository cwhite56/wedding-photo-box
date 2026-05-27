package com.cwhite.wedding_photo_box.Mapper;

public interface Mapper<A, B> {
    public B mapTo(A a);
    public A mapFrom(B b);
}
