package com.skaleto.things.sundry.turing.isvp.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FreeSayVerifyRequest {

    private String bizId;

    private Audio audio;

}
