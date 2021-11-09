package com.skaleto.things.sundry.turing.isvp.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FreeSayRegisterRequest {

    private String bizId;

    private String dbName;

    private Audio[] audios;

    //tags略
}
