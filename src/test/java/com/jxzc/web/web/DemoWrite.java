package com.jxzc.web.web;

import java.io.*;

public class DemoWrite {

    public static void main(String[] args) throws IOException {

        FileWriter fw = new FileWriter(new File("C:\\Users\\admin\\Desktop\\test.txt"));
        //写入中文字符时会出现乱码
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < 20000000; i++) {
            bw.write(i+"\t\n");
        }
        bw.close();
        fw.close();
    }

}
