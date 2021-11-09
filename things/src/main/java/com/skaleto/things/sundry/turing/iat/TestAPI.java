package com.skaleto.things.sundry.turing.iat;

import com.alibaba.fastjson.JSON;
import com.skaleto.things.sundry.turing.AuthUtils;
import com.skaleto.things.sundry.turing.Common;
import com.skaleto.things.sundry.turing.ReqParam;

import java.util.Map;

public class TestAPI {

    public static void main(String[] args) {
        ReqParam param = new ReqParam();
        param.setCommon(new Common());
        param.setBusiness(new IatBusiness());
        param.setData(new IatData());

        byte[] body = JSON.toJSONBytes(param);

        System.out.println(JSON.toJSONString(param));

        Map<String, String> header = AuthUtils.assembleRequestHeader("172.31.131.18:8585/v2/iat", "ii-test", "9FAC5585825445D3ADBBCAF68315277D", body);

    }
}
