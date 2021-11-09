package com.skaleto.things.sundry.enginetraining;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OpenQA {

    public static final String FILE_PATH = "/Users/yaoyibin/Downloads/QA标注-变电所基本知识/QA标注-变电所基本知识.xlsx";

    public static final String OUTPUT_PATH = "/Users/yaoyibin/Downloads/变电所基本知识.xlsx";


    @Test
    public void trans2QaFormat() {
        ExcelReader reader = ExcelUtil.getReader(new File(FILE_PATH), 1);
        ExcelWriter writer = ExcelUtil.getWriter(new File(OUTPUT_PATH));

        //写入三行头部
        writer.writeRow(Collections.singletonList("批量导入自定义问答数据模板"));
//        writer.merge(2);
        writer.writeRow(Collections.singletonList("使用说明"));
//        writer.merge(2);
        writer.writeRow(Arrays.asList("主题", "问题", "答案"));

        //从第二行开始读
        List<List<Object>> list = reader.read(1);
        list.forEach(i -> {
            writer.writeRow(Arrays.asList(i.get(2), i.get(2) + "\n" + i.get(3),i.get(4)));
        });

        writer.flush();


    }
}
