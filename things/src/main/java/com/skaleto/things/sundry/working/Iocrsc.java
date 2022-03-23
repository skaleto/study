package com.skaleto.things.sundry.working;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.ibocr.domain.input.IbocrActionParam;
import com.iflytek.ibocr.domain.input.IbocrActionParamBuilder;
import com.iflytek.ibocr.domain.output.IbOcrSvcInfo;
import com.iflytek.ibocr.domain.output.IbocrActionResult;
import com.iflytek.ibocr.sdk.client.IbocrHttpClient;
import com.iflytek.iocrsc.sdk.client.IocrscHttpClient;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Iocrsc {

    public static void main(String[] args) throws Exception {
        Iocrsc iocrsc=new Iocrsc();
        iocrsc.testIocrsc();
    }

    public void testIocrsc() throws Exception {
        File file = new File("/Users/yaoyibin/Downloads/20211203.png");
        Path path=file.toPath();
        IocrscHttpClient iocrscHttpClient = new IocrscHttpClient("http://172.31.234.198:9902");

        //分类
//        List<byte[]> imageDataList = new ArrayList<>();
//        imageDataList.add(Files.readAllBytes(path));
//        IocrscActionParam iap = new IocrscActionParamBuilder()
//                .setOutType(0)
//                .setImageDataList(imageDataList)
//                .build();
//        IocrscActionResult iocrscActionResult = iocrscHttpClient.calssify(iap);
//        System.out.println(JSON.toJSONString(iocrscActionResult));

        System.out.println("------------------------------------------");
        //分类识别
        JSONObject jsonObject = new JSONObject();
        String data = Base64.getEncoder().encodeToString(Files.readAllBytes(path));
        jsonObject.put("image_data",data);
        String result = iocrscHttpClient.recognize(jsonObject.toJSONString());
        System.out.println(result);
    }


    private IbocrHttpClient ibocrHttpClient;

//    public static void main(String[] args) throws Exception {
//        Iocrsc ocr=new Iocrsc();
//        ocr.testRecognize();
//    }

    public void testRecognize() throws Exception {
        ibocrHttpClient = new IbocrHttpClient("http://122.225.200.4:19900");
        String imageAbsPath = "/Users/yaoyibin/Downloads/test.jpg";
        File imageFile = new File(imageAbsPath);
        if (!imageFile.exists()) {
            System.out.printf("cannot find image file. imageAbsPath:%s", imageAbsPath);
            return;
        }
        requestOnce(imageFile);
    }

    private void requestOnce(File file) throws Exception {
        IbOcrSvcInfo ibOcrSvcInfo = ibocrHttpClient.querySvcInfo();
        System.out.println(JSON.toJSONString(ibOcrSvcInfo));

        List<byte[]> imageDataList = new ArrayList<>();
        imageDataList.add(Files.readAllBytes(file.toPath()));
        IbocrActionParam iap = new IbocrActionParamBuilder()
                //template:票据模板类型名称，见下方模板列表
                .setTemplate("vat_invoice")
                .setImageDataList(imageDataList)
                //resultLevel:输出结果等级，取值范围["1", "2", "3", "4"]
                //resultLevel:取值解释："1" 表示仅输出文本行的top1结果，输出文本行及文本行位置（默认值），"2" 暂未使用（保留给topN使用）, "3" 表示输出文本行识别结果和校正后的图像，"4"表示输出文本行识别结果,校正后的图像和每个textblock图像。
                .setResultLevel("3").build();
        IbocrActionResult ibocrActionResult = ibocrHttpClient.recognize(iap);

        //输出原始结果
        System.out.println(JSON.toJSONString(ibocrActionResult));
    }


}