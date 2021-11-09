package com.skaleto.things.sundry;

import java.io.*;
import java.util.*;

/**
 * Created by yt on 2016/11/15.
 * Modified by jialin on 2020/02/11
 */

public class getRate {
    public static List<Float> getRate() {
        List<Map<String, String>> list1 = getList();//采样第一次CPU信息快照
        List<Map<String, Long>> time1 = new ArrayList<>();

        getSample(list1, time1);

        try {
            Thread.sleep(360);//等待360ms
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Map<String, String>> list2 = getList();//采样第二次CPU信息快照
        List<Map<String, Long>> time2 = new ArrayList<>();

        getSample(list2, time2);

        List<Float> cpuRates = new ArrayList<>();
        //个数以list1为准
        int cpuNum = list1.size();
        for (int k = 0; k < cpuNum; k++) {//依次计算各核心的占用率
            long totalTime1 = time1.get(k).get("totalTime");
            long idleTime1 = time1.get(k).get("idleTime");
            long totalTime2 = time2.get(k).get("totalTime");
            long idleTime2 = time2.get(k).get("idleTime");
            float cpuRate = 100 * (float) ((totalTime2 - totalTime1) - (idleTime2 - idleTime1)) / (float) (totalTime2 - totalTime1);
            cpuRates.add(cpuRate);
        }
        return cpuRates;
    }

    /**
     * @param list 每个CPU对应一个map，包含了8个字段
     * @param time 每个CPU对应一个map，包含了两个字段
     */
    public static void getSample(List<Map<String, String>> list, List<Map<String, Long>> time) {
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map1 = list.get(i);
            long totalTime = Long.parseLong(map1.get("user")) + Long.parseLong(map1.get("nice"))
                    + Long.parseLong(map1.get("system")) + Long.parseLong(map1.get("idle"))
                    + Long.parseLong(map1.get("iowait")) + Long.parseLong(map1.get("irq"))
                    + Long.parseLong(map1.get("softirq"));//获取totalTime1
            long idleTime = Long.parseLong(map1.get("idle"));//获取idleTime1

            Map<String, Long> timeList = new HashMap<>();
            timeList.put("totalTime", totalTime);
            timeList.put("idleTime", idleTime);
            time.add(timeList);
        }
    }


    //采样CPU信息快照的函数，返回Map类型
    public static List<Map<String, String>> getList() {

//        List<String> tempInfos = new ArrayList<>();
        List<Map<String, String>> cpuInfos = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")));//读取CPU信息文件
            for (int i = 0; i < 8; i++) {
                String[] lines = null;
                String load = br.readLine();
                lines = load.split(" ");
//                tempInfos.add(lines);
                Map<String, String> map = new HashMap<String, String>();
                map.put("user", lines[1]);
                map.put("nice", lines[2]);
                map.put("system", lines[3]);
                map.put("idle", lines[4]);
                map.put("iowait", lines[5]);
                map.put("irq", lines[6]);
                map.put("softirq", lines[7]);
                cpuInfos.add(map);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cpuInfos;
    }

    public static void main(String args[]) throws InterruptedException {

        String path = "/storage/sdcard0/test_hz/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String[] fileNames = {"cpu0.txt", "cpu1.txt", "cpu2.txt", "cpu3.txt", "cpu4.txt", "cpu5.txt", "cpu6.txt", "cpu7.txt"};
        //间隔1s采样一次（调用getRate()进行CPU占用率计算）
        while (true) {
            List<Float> rates = getRate();
            for (int i = 0; i < rates.size(); i++) {
                String content = Float.toString(rates.get(i));
                String fileName = "cpu" + i + "_rates.txt";
                try {
                    File file = new File(path + fileNames[i]);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fileWritter = new FileWriter(file.getName(), true);
                    fileWritter.write(content);
                    fileWritter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //间隔1秒再跑
            Thread.sleep(1000);
        }
    }
}


