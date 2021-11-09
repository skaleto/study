package com.skaleto.things.sundry.turing.tts.v2.domain.response;

import lombok.Data;

@Data
public class TTSResponse {

    private int code;

    private String message;

    private com.skaleto.things.sundry.turing.tts.v2.domain.response.Data data;

    private String sid;

}
