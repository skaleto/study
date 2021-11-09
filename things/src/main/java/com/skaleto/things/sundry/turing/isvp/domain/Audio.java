package com.skaleto.things.sundry.turing.isvp.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author yaoyibin
 */
@Builder
@Data
public class Audio {

    private String audioId;

    private String audioBytes;

    private String isvCode;

    //tags略
}
