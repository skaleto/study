package com.skaleto.things.sundry.turing.isvp.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DigitPwdRegisterRequest {

    private String bizId;

    private String dbName;

    private Audio[] audios;

    //tags略
}
