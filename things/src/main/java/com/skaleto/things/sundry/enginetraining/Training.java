package com.skaleto.things.sundry.enginetraining;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class Training {

    public static final String STATION_XLSX = "/Users/yaoyibin/Documents/工业图聆/语音模型训练数据/一次设备台账统计/变电站设备台账.xlsx";

    public static final String FACTORY_XLSX = "/Users/yaoyibin/Documents/工业图聆/语音模型训练数据/一次设备台账统计/电厂设备台账.xlsx";

    public static final String LOG_XLSX = "/Users/yaoyibin/Documents/工业图聆/语音模型训练数据/市调调度日志数据统计.xlsx";


    public static final String result = "/Users/yaoyibin/Documents/工业图聆/语音模型训练数据/result.txt";

    public static final String cut = "/Users/yaoyibin/Documents/工业图聆/语音模型训练数据/cutwords";


    public static LinkedHashSet<String> res = new LinkedHashSet<>();

    public static void getWords() {

        try (FileWriter fw = new FileWriter(result)) {
            //station-交流线路
            StringBuilder a = new StringBuilder();
            StringBuilder b = new StringBuilder();

            ExcelReader reader0 = ExcelUtil.getReader(new File(STATION_XLSX), 0);
            List<List<Object>> list0 = reader0.read(1);
            list0.forEach(i -> {
                String s = (String) i.get(2);
                String t = s.substring(s.lastIndexOf("V") + 1);
                t = t.replace("（用户）", "").replace("变电站", "").trim();
                res.add(t);
            });

            list0.forEach(i -> {
                String t = (String) i.get(3);
                res.add(t.substring(t.lastIndexOf(".") + 1));
            });

            //station-变电站
            ExcelReader reader1 = ExcelUtil.getReader(new File(STATION_XLSX), 1);
            List<List<Object>> list1 = reader1.read(1);
            list1.forEach(i -> {
                String s = (String) i.get(2);
                String t = s.substring(s.lastIndexOf("V") + 1);
                t = t.replace("（用户）", "").replace("变电站", "").trim();
                res.add(t);
            });


            //factory
            ExcelReader reader2 = ExcelUtil.getReader(new File(FACTORY_XLSX), 0);
            List<List<Object>> list2 = reader2.read(1);
            list2.forEach(i -> {
                String s = (String) i.get(2);
                String t = s.substring(s.lastIndexOf("V") + 1);
                t = t.replace("变电站", "").trim();
                res.add(t);
            });


            StringBuilder logdata = new StringBuilder();

            //log file1
            getName(LOG_XLSX, 0, 5, 7);
//            getLog(LOG_XLSX, 0, logdata, 6);

            getName(LOG_XLSX, 1, 5);
//            getLog(LOG_XLSX, 1, logdata, 6);

            getName(LOG_XLSX, 2, 9, 10);
//            getLog(LOG_XLSX, 2, logdata, 6, 7, 12);

            getName(LOG_XLSX, 3, 9, 10);
//            getLog(LOG_XLSX, 3, logdata, 7, 8);

            getName(LOG_XLSX, 4, 5, 7, 8);
//            getLog(LOG_XLSX, 4, logdata, 6);

            getName(LOG_XLSX, 5, 4, 9);
//            getLog(LOG_XLSX, 5, logdata, 6);

            getName(LOG_XLSX, 8, 5, 7);

            getName(LOG_XLSX, 9, 5, 8);
//            getLog(LOG_XLSX, 9, logdata, 6);

            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(cut)));
            String s = bf.readLine();
            Collections.addAll(res,s.split(","));

            res.forEach(i -> {
                try {
                    fw.write(i);
                    fw.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            fw.write("主变\n刀闸\n地刀\n中性点地刀\n电容串抗\n电抗\n电容\nCT\nPT\n");

//            System.out.println(logdata.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void getName(String filePath, int sheetIndex, int... nameIndex) {
        //log file3
        ExcelReader reader = ExcelUtil.getReader(new File(filePath), sheetIndex);
        List<List<Object>> list = reader.read(1);
        //人名
        list.forEach(i -> {
            Arrays.stream(nameIndex).forEach(j -> {
                if (j >= i.size()) {
                    return;
                }
                if (!(i.get(j) instanceof String)) {
                    return;
                }
                String s = (String) i.get(j);
                if (StringUtils.isEmpty(s)) {
                    return;
                }
                res.add(s.trim());
            });
        });
        reader.close();
    }

    public static void getLog(String filePath, int sheetIndex, StringBuilder sb, int... logIndex) {
        //log file3
        ExcelReader reader = ExcelUtil.getReader(new File(filePath), sheetIndex);
        List<List<Object>> list = reader.read(1);
        //人名
        list.forEach(i -> {
            Arrays.stream(logIndex).forEach(j -> {
                if (j >= i.size()) {
                    return;
                }
                if (!(i.get(j) instanceof String)) {
                    return;
                }
                String s = (String) i.get(j);
                if (StringUtils.isEmpty(s)) {
                    return;
                }
                sb.append(s.trim());
            });
        });
        reader.close();
    }

    public static void main(String[] args) {
        getWords();
    }
}
