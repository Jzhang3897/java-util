package com.zj.util.orm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by zhangjun16 on 2018/1/15.
 */
public class GenerateEntity {
    private static String SPLIT_CHAR = "\t";

    public static void main(String[] args) throws Exception {
        generateEntity("D:\\data\\reward1.txt", true);
    }

    private static void generateEntity(String fileName, boolean annotation) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String line;
        while ((line = bf.readLine()) != null) {
            String[] columns = line.split(SPLIT_CHAR);
            if (annotation) {
                if ("id".equals(columns[0])) {
                    System.out.println("@Id");
                    System.out.println("@GeneratedValue(strategy = GenerationType.IDENTITY)");
                }
                if("Date".equalsIgnoreCase(typeOf(columns[1]))){
                    System.out.println("@JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"GMT+8\")");
                }
                System.out.println("@Column(name = \"" + columns[0] + "\")");
            }
            System.out.println("private " + typeOf(columns[1]) + " " + nameOf(columns[0]) + ";");
        }
        bf.close();
    }
    private static String typeOf(String sqlType){
        String type;
        switch (sqlType){
            case "bigint": type ="Long";break;
            case "varchar": type ="String";break;
            case "int": type ="Integer";break;
            case "tinyint": type ="int";break;
            case "datetime": type ="Date";break;
            case "text": type ="String";break;
            case "double": type ="double";break;
            case "float": type ="float";break;
            case "smallint": type ="int";break;
            case "integer": type ="int";break;
            case "decimal": type ="BigDecimal";break;
            case "char": type = "char";break;
            default: type = sqlType;
    }
        return type;
    }

    private static String nameOf(String name) {
        String[] words = name.split("_");
        StringBuilder sb = new StringBuilder();
        sb.append(words[0]);
        for (int i = 1; i < words.length; i++) {
            sb.append(Character.toUpperCase(words[i].charAt(0)));
            sb.append(words[i].substring(1, words[i].length()));
        }
        return sb.toString();
    }
}
