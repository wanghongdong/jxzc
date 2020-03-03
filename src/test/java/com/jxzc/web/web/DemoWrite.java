package com.jxzc.web.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

public class DemoWrite {

    public static void main(String[] args) throws IOException {
//        wirteDemoText(10004000);
//        readLineAndWrite();
//        readLine();

//        String s = "filename.xls";
//        System.out.println(s.substring(s.lastIndexOf(".")+1,s.length()));

        getSameSet();
    }


    public static Set<String> readUserIdTxt() throws IOException {
        long time = new Date().getTime();
        LineNumberReader lnr = new LineNumberReader(new FileReader("C:\\Users\\lunan\\Desktop\\beiingPoint.txt"));
        String line = null;
        Set<String> userIds = new HashSet<>();
        int i = 0;
        while (StringUtils.isNotBlank((line = lnr.readLine()))){
            i++;
            userIds.add(line);
        }
        lnr.close();
        long time1 = new Date().getTime();
        System.out.println("总耗时：" + (time1-time) + ", userId总数量："+i);
        return userIds;
    }

    public static Set<String> readTaskIdTxt() throws IOException {
        long time = new Date().getTime();
        LineNumberReader lnr = new LineNumberReader(new FileReader("C:\\Users\\admin\\Desktop\\taskId.txt"));
        String line = null;
        Set<String> taskIds = new HashSet<>();
        int i = 0;
        while (StringUtils.isNotBlank((line = lnr.readLine()))){
            taskIds.add(line);
            i++;
        }
        lnr.close();
        long time1 = new Date().getTime();
        System.out.println("总耗时：" + (time1-time) + ", taskId总数量："+i);
        return taskIds;
    }

    public static Set<String> getSameSet() throws IOException {
        long time = new Date().getTime();
        Set<String> userIdTxt = readUserIdTxt();
        Set<String> userIdTxt1 = readUserIdTxt();
        Set<String> taskIdTxt = readTaskIdTxt();
        boolean b = userIdTxt.retainAll(taskIdTxt);
        boolean b1 = userIdTxt1.removeAll(userIdTxt);
        long time1 = new Date().getTime();
        System.out.println("总耗时：" + (time1-time) + "，重复数据："+userIdTxt.size() + ", 不重复数据："+userIdTxt1.size());

        FileWriter fw = new FileWriter("C:\\Users\\admin\\Desktop\\diff.txt");
        for (String line : userIdTxt1) {
            fw.write(line+"\r\n");
        }
        FileWriter fw1 = new FileWriter("C:\\Users\\admin\\Desktop\\same.txt");
        for (String line : userIdTxt) {
            fw1.write(line+"\r\n");
        }
        fw1.close();
        fw.close();
        return userIdTxt;
    }


    public static void readLine() throws IOException {
        long time = new Date().getTime();
        LineNumberReader lnr = new LineNumberReader(new FileReader("C:\\Users\\admin\\Desktop\\s.csv"));
        String line = null;
        while (StringUtils.isNotBlank((line = lnr.readLine()))){
            if (!validPhone(line.substring(1,line.length()-1))){
                System.out.println("第"+ lnr.getLineNumber() +"行电话格式有误，格式为："+line);
            }
        }
        lnr.close();
        long time1 = new Date().getTime();
        System.out.println("总耗时：" + (time1-time));
    }


    public static void readLineAndWrite() throws IOException {
        long time = new Date().getTime();
        LineNumberReader lnr = new LineNumberReader(new FileReader("C:\\Users\\admin\\Desktop\\out.txt"));
        String line = null;
        int i = 0;
        int j = 0;
        String filePath = "C:\\Users\\admin\\Desktop\\"+ time;
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdirs();
        }
        FileWriter fw = new FileWriter(filePath + getFileName(j));
        while (StringUtils.isNotBlank((line = lnr.readLine()))){
            fw.write(line+"\r\n");
            i++;
            if (i==10000000){
                j++;
                i=0;
                fw.flush();
                fw = new FileWriter(filePath + getFileName(j));
            }
        }
        fw.flush();
        fw.close();
        long time1 = new Date().getTime();
        System.out.println("总耗时：" + (time1-time));
    }

    public static String getFileName(int num){
        return   "\\text"+ num +".txt";
    }

    public static void wirteDemoText(int n) throws IOException{
        FileWriter fw = new FileWriter("C:\\Users\\admin\\Desktop\\out.txt");
        for (int i = 0; i < n; i++) {
            fw.write(getTel()+"\r\n");
        }
        fw.close();
    }

    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    private static String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    public static boolean validPhone(String phone){
        if (StringUtils.isEmpty(phone)) return false;
        String regex = "^(13[0-9]|14[5-9]|15[012356789]|166|17[0-8]|18[0-9]|19[8-9])[0-9]{8}$";
        return phone.matches(regex);
    }

    public String readLineAndWrite(CommonsMultipartFile userFile, HttpServletRequest request) {
        long time = new Date().getTime();
        String filePath = null;
        if (userFile!=null && userFile.getSize()>0){
//            FileWriter fw = null;
//            LineNumberReader lnr = null;
//            InputStream inputStream = null;
//            InputStreamReader reader = null;
//            try {
//                inputStream = userFile.getInputStream();
//                reader = new InputStreamReader(inputStream);
//                lnr = new LineNumberReader(reader);
//                String line = null;
//                int i = 0;
//                int j = 0;
//                filePath = catalinaPath + File.separatorChar + time;
//                log.info("消息推送管理文件上传目录：" + filePath);
//                File file = new File(filePath);
//                if (!file.exists()){
//                    file.mkdirs();
//                }
//                fw = new FileWriter(filePath + getFileName(j));
//                while (org.apache.commons.lang3.StringUtils.isNotBlank((line = lnr.readLine()))){
//                    fw.write(line+"\n");
//                    i++;
//                    if (i==100000){
//                        j++;
//                        i=0;
//                        fw.flush();
//                        fw = new FileWriter(filePath + getFileName(j));
//                    }
//                }
//                fw.flush();
//            } catch (IOException e) {
//                log.error("消息推送管理文件上传错误!",e);
//            }finally {
//                try {
//                    inputStream.close();
//                    reader.close();
//                    lnr.close();
//                    fw.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        long time1 = new Date().getTime();
//        log.info("消息推送管理文件上传总耗时：" + (time1-time));
        return filePath;
    }

//    public String getFileName(int num){
//        return  "\\text"+ num +".txt";
//    }
}
