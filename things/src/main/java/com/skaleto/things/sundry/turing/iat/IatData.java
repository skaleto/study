package com.skaleto.things.sundry.turing.iat;

import lombok.EqualsAndHashCode;
import com.skaleto.things.sundry.turing.Data;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class IatData extends Data {

    public String format = "audio/L16;rate=16000";

    public String encoding = "speex-wb";

    public String audio = "exSI6ICJlbiIsCgkgICAgInBvc2l0aW9uIjogImZhbHNlIgoJf...";
}
