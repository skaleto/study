package com.skaleto.things.sundry.turing.tts.v2.domain.request;

import lombok.Builder;

@lombok.Data
@Builder
public class Data {

    private String text;

    private int status;
}
