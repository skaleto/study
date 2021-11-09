package com.skaleto.things.sundry.turing.isvp.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DigitPwdVerifyRequest {

    private String bizId;

    private Audio audio;

}
