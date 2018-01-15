package com.zj.util.orm;

/**
 * Created by zhangjun16 on 2018/1/15.
 */
public class GenerateEntity {
    public static void main(String[] args) {
        String column = "id\n" +
                "active_id\n" +
                "sku\n" +
                "comment_id\n" +
                "guid\n" +
                "pin\n" +
                "word_count\n" +
                "show_count\n" +
                "video_count\n" +
                "status\n" +
                "create_time\n" +
                "modify_time\n";
        String[] columns = column.split("\n");
        for (int i = 0; i < columns.length; i++) {
            System.out.printf("private ");
        }
    }
}
