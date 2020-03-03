package com.jxzc.web.web;

import com.alibaba.fastjson.JSON;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassPath com.jxzc.web.web.BeijingPoints
 * @ClassName BeijingPoints
 * @Description TODO
 * @Author whd
 * @Date 2020/3/1 17:23
 * @Version 1.0
 */

public class BeijingPoints {


    public static void main(String[] args) throws IOException {
        String points = getPoints();
        String[] split = points.split(";");
        System.out.println(split.length);
        List list = new ArrayList();
        int i = 1;
        for (String s : split) {
            if (i%2==1){
                String[] split1 = s.split(",");
                BjPoints bjPoints = new BjPoints(split1[0], split1[1]);
                list.add(bjPoints);
            }
            i++;
        }
        String s = JSON.toJSONString(list);
        System.out.println(s);
        FileWriter fw = new FileWriter("C:\\Users\\lunan\\Desktop\\out.txt");
        fw.write(s);
        fw.close();
    }

    private static String getPoints(){
        String filePath = "C:\\Users\\lunan\\Desktop\\beiingPoint.txt";
        String s = "";
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
            char[] chars = new char[1024];
            while (fileReader.read(chars) > 0){
                s += new String(chars);
            }
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader!=null){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
}
