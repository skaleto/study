package com.skaleto.things.sundry.working;

import lombok.experimental.Accessors;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yaoyibin
 */
public class DataScreen {

//    public static void main(String[] args) {
//        List<Data> dataList = new ArrayList<>();
//
//        ExcelReader reader = new ExcelReader("/Users/yaoyibin/Downloads/副本未来工厂.xlsx", 0);
//        int rowCount = reader.getRowCount();
//        for (int i = 1; i < rowCount; i++) {
//            List<Object> list = reader.readRow(i);
//            dataList.add(new Data()
//                    .setName(String.valueOf(list.get(0)))
//                    .setIndustryType(String.valueOf(list.get(1)))
//                    .setTotalTax(String.valueOf(list.get(2)))
//                    .setEvenTax(String.valueOf(list.get(3)))
//                    .setAiApp(String.valueOf(list.get(4)))
//                    .setIndustrialApp(String.valueOf(list.get(5)))
//                    .setCompanyScore("-")
//                    .setCompanyClass(String.valueOf(list.get(6)))
//            );
//        }
//        String result = dataList.stream().com.skaleto.things.map(i -> "{"
//                + "name:\"" + i.getName() + "\","
//                + "industryType:\"" + i.getIndustryType() + "\","
//                + "totalTax:\"" + i.getTotalTax() + "\","
//                + "evenTax:\"" + i.getEvenTax() + "\","
//                + "aiApp:\"" + i.getAiApp() + "\","
//                + "industrialApp:\"" + i.getIndustrialApp() + "\","
//                + "companyScore:\"" + i.getCompanyScore() + "\","
//                + "companyClass:\"" + i.getCompanyClass() + "\""
//                + "}").collect(Collectors.joining(","));
//        System.out.println(result);
//    }

    @lombok.Data
    @Accessors(chain = true)
    public static class Data {
        private String name;
        private String industryType;
        private String totalTax;
        private String evenTax;
        private String aiApp;
        private String industrialApp;
        private String companyScore;
        private String companyClass;
    }


    private ConcurrentHashMap<String, ConcurrentHashMap<String, Data>> registry = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        DataScreen instance = new DataScreen();
        for (int i = 0; i < 100; i++) {
            Data data = new Data().setName(String.valueOf(i));
            new Thread(() -> {
                instance.addInstance(data);
                System.out.println(instance.registry.get(data.getName()));
            }).start();
        }
    }

    public void addInstance(Data data) {
        ConcurrentHashMap<String, Data> gMap = registry.get(data.getName());
        if (gMap == null) {
            ConcurrentHashMap<String, Data> newMap = new ConcurrentHashMap<>();
            gMap = registry.putIfAbsent(data.getName(), newMap);
            if (gMap == null) {
                gMap = newMap;
            }
        }
        gMap.put(data.getName(), data);


    }

}
