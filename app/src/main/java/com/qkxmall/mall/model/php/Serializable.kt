package com.qkxmall.mall.model.php

/**
 * Created by Sunshine on 01/04/2016.
 */
internal interface Serializable {
    fun serialize(): ByteArray
    fun unserialize(ss: ByteArray)
}
