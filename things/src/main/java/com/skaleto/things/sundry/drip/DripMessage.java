package com.skaleto.things.sundry.drip;


import cn.hutool.crypto.digest.MD5;

public class DripMessage {

    public static void main(String[] args) {
        String s = "appid=KBOPP5DJ&phone=18915618653&tid=12370&tp={\"code\":\"123456\"}&key=54cd90d12aa1964a88a8a40f475ef8a0";
        String sign = MD5.create().digestHex(s).toUpperCase();
        System.out.println(sign);
    }
}
