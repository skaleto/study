package com.skaleto.things.sundry.working;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScapyUtil {

    public static void transferScapyJsonToExcel() throws Exception {
        ExcelWriter excelWriter = ExcelUtil.getWriter(new File("/Users/yaoyibin/Downloads/demands.xlsx"));

        File dir = new File("/Users/yaoyibin/Downloads/spider/scrapy_demo/result");
        File[] files = dir.listFiles();
        assert files != null;
        Arrays.stream(files).sorted().forEach(f -> {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                        String s = br.readLine();
                        s = s.substring(1, s.length() - 1);
                        ScrapyEntity i = JSON.parseObject(s, ScrapyEntity.class);

                        excelWriter.writeRow(
                                Arrays.asList(i.getLink(),
                                        i.getName(),
                                        i.getField(),
                                        i.getValidate_time(),
                                        i.getArea(),
                                        i.getCooperation_type(),
                                        i.getUrgent(),
                                        i.getPost_time(),
                                        i.getDifficulty(),
                                        i.getCompany_name()
                                ));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        excelWriter.flush();

    }

    private static String parseInfo(List<List<String>> infos) {
        if (CollectionUtils.isEmpty(infos)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        infos.forEach(sb::append);
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        ScapyUtil.transferScapyJsonToExcel();
//        ScapyUtil.modifyScrapyInfo();
    }

    static Pattern pattern = Pattern.compile("\\[(.*?)]");

    public static void modifyScrapyInfo() {

        ExcelReader excelReader = new ExcelReader(new File("/Users/yaoyibin/Downloads/软服之家-企业信息.xlsx"), 0);

        List<List<Object>> records = excelReader.read();
        records.forEach(i -> {
            String name = (String) i.get(0);
            String info = (String) i.get(1);

            String company = "";
            String mail = "";

            Matcher matcher = pattern.matcher(info);
            while (matcher.find()) {
                String s = matcher.group(1);

                if (s.startsWith("厂")) {
                    String temp = s.replace(",", "");
                    if (temp.split("：")[1].length() > name.length()) {
                        name = temp.split("：")[1];
                    }
                    company = temp.split("：")[1];
                }

                if (s.startsWith("邮")) {
                    String temp = s.replace(",", "");
                    mail = temp.split("：")[1];
                }

            }

            i.set(0, name);
            i.set(1, mail);

        });

        ExcelWriter excelWriter = ExcelUtil.getWriter(new File("/Users/yaoyibin/Downloads/软服之家-企业信息_20210809.xlsx"));
        excelWriter.write(records);
        excelWriter.flush();

    }
}
