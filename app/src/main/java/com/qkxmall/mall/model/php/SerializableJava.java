package com.qkxmall.mall.model.php;

/**
 * Created by Sunshine on 01/04/2016.
 */
interface SerializableJava {
    byte[] serialize();
    void unserialize(byte[] ss);
}
