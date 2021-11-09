package com.skaleto.things.sundry.turing.isvp.domain;

import lombok.Data;

@Data
public class CreateDBResponse {

    private State state;

    private CreateDBBody body;
}
