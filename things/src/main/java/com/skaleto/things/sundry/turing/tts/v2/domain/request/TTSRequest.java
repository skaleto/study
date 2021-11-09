package com.skaleto.things.sundry.turing.tts.v2.domain.request;

import lombok.Builder;

@Builder
@lombok.Data
public class TTSRequest {

    private Common common;

    private Business business;

    private Data data;
}
