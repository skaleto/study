package com.skaleto.things.sundry.turing.tts.v2.domain.response;

@lombok.Data
public class Data {

    private String audio;

    private String text;

    private int status;

    private int curStart;

    private int ced;
}
