package com.luwei.lwbaselib;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import kotlin.collections.CollectionsKt;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void run() throws Exception {

//        String classPath = "C:\\Users\\JvmDev\\Desktop\\x.txt";
//        移除黄油刀 yc = new 移除黄油刀();
//        String 生成新类 = yc.处理类数据(Paths.get(classPath)).生成新类();

//        System.out.println(生成新类);
//        Files.write(Paths.get(classPath),生成新类.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

        String classPath = "C:\\Users\\JvmDev\\Desktop\\ASProject\\LuweiAppX\\app\\src\\main\\java\\com\\luwei\\lwbaselib";

        Files.walk(Paths.get(classPath)).filter(p -> p.toString().endsWith(".java")).forEach(p -> {
            System.out.println(p);
            移除黄油刀 yc = new 移除黄油刀();
            String newClass = "";
            try {
                newClass = yc.处理类数据(p).生成新类();
                if (newClass.equals("")) return;
                System.out.println(p+"\n");
                Files.write(p,newClass.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }


}