package sundry.turing.iat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sundry.turing.Business;

@EqualsAndHashCode(callSuper = true)
@Data
public class IatBusiness extends Business {

    public String language = "zh_cn";

    public String domain = "bytebl";

    public String accent = "mandarin";
}
