package com.skaleto.things.sundry.working;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * 移动云MAS-HTTPS短信接口调用demo
 *
 *
 * maven依赖列表：
 * <dependency>
 * <groupId>org.apache.httpcomponents</groupId>
 * <artifactId>httpclient</artifactId>
 * <version>4.5.13</version>
 * </dependency>
 *
 * <dependency>
 * <groupId>org.apache.httpcomponents</groupId>
 * <artifactId>httpcore</artifactId>
 * <version>4.4.14</version>
 * </dependency>
 *
 * <dependency>
 * <groupId>cn.hutool</groupId>
 * <artifactId>hutool-all</artifactId>
 * <version>5.6.5</version>
 * </dependency>
 *
 * <dependency>
 * <groupId>com.alibaba</groupId>
 * <artifactId>fastjson</artifactId>
 * <version>1.2.60</version>
 * </dependency>
 *
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-web</artifactId>
 * <version>5.3.8</version>
 * </dependency>
 */
public class MASDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        //企业名称，固定值不用更改
        String ecName = "安徽省工商业联合会";
        //接口账户用户名，固定值不用更改
        String apId = "gsldx";
        //接口账户用户密码，固定值不用更改
        String secretKey = "Fxxx1sh,Zs1qxbfh";
        //发送手机号列表，逗号分割
        String mobiles = "xxx,xxx";
        //发送内容，按照业务逻辑进行内容拼接
        String content = "您收到xxx公司关于“xxx”的调解申请，请您尽快和xxx联系人进行联系，联系电话：xxx。";
        //短信接口签名，固定值不用更改
        String sign = "BDDh383R1";
        //扩展码，固定值不用更改
        String addSerial = "";
        //mac值，由上述内容动态生成
        String mac = MD5.create().digestHex(ecName + apId + secretKey + mobiles + content + sign + addSerial);
        Request request = new Request();
        request.setEcName(ecName);
        request.setApId(apId);
        request.setMobiles(mobiles);
        request.setContent(content);
        request.setSign(sign);
        request.setAddSerial(addSerial);
        request.setMac(mac);

        String reqStr = JSON.toJSONString(request);

        RestTemplate restTemplate = getRestTemplate();
        //url为固定值
        Object result = restTemplate.postForObject("https://112.35.10.201:28888/sms/submit", Base64.encode(reqStr), Object.class);

        System.out.println(result);
    }

    /**
     * 支持https调用的resttemplate
     *
     * @return
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static RestTemplate getRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,
                new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"},
                null,
                NoopHostnameVerifier.INSTANCE);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }

    static class Request {
        private String ecName;
        private String apId;
        private String mobiles;
        private String content;
        private String sign;
        private String addSerial;
        private String mac;

        public void setEcName(String ecName) {
            this.ecName = ecName;
        }

        public void setApId(String apId) {
            this.apId = apId;
        }

        public void setMobiles(String mobiles) {
            this.mobiles = mobiles;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public void setAddSerial(String addSerial) {
            this.addSerial = addSerial;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }
    }
}
