package com.skaleto.things.sundry.turing.iat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.skaleto.things.sundry.turing.Business;

@EqualsAndHashCode(callSuper = true)
@Data
public class IatBusiness extends Business {

    public String language = "zh_cn";

    public String domain = "bytebl";

    public String accent = "mandarin";
}
