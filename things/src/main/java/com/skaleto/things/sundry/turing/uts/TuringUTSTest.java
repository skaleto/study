package com.skaleto.things.sundry.turing.uts;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import com.skaleto.things.sundry.turing.uts.domain.UTSRequest;

import java.net.URL;

import static com.skaleto.things.sundry.turing.isvp.TuringISVPTest.generateHeaders;

@Slf4j
public class TuringUTSTest {

    private static final String APP_ID = "chongqing_test";

    private static final String API_SECRET = "960972AAFEE84436AB39C226C318B63B";

    private static final String SERVICE_URL = "http://172.31.131.18:8585/semcmd/api/v1/semcmd/parse/";

    @Test
    public void testUTS() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        UTSRequest request = UTSRequest.builder()
                .sceneKey("chongqing_test")
                .text("我想看八柱发电站的电力信息")
                .build();

        String finalUrl=SERVICE_URL + "track1"+"?sceneKey=chongqing_test&text=我想看八柱发电站的电力信息";

        HttpEntity req = new HttpEntity(
                generateHeaders(new URL(finalUrl), HttpMethod.POST, APP_ID, API_SECRET, false, null));
//
//        Map<String, String> params = new HashMap<>();
//        params.put("sceneKey", "chongqing_test");
//        params.put("text", "我想看八柱发电站的电力信息");


//        Object response = restTemplate.exchange(SERVICE_URL + "track1", HttpMethod.GET, req, Object.class, params);

        Object response = restTemplate.postForEntity(finalUrl, req, Object.class);

        log.info("");
    }
}
