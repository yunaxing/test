package com.qkxmall.mall.model.php

/**
 * Created by Sunshine on 01/04/2016.
 */
public class ToJson{
    constructor()

    public fun to(string: String): String {
        var json = string;
        json = json.replace("=","\":\"")
        json = json.replace("{","{\"")
        json = json.replace("}","\"}")
        json = json.replace(", ","\",\"")
        json = json.replace("}\",\"{","},{")
        return json
    }


}
