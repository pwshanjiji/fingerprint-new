package com.xml.utils;

import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FingerUtils {


    public static boolean metch(String path1,String path2){
        boolean match = false;
        try {
            byte[] probeImage = Files.readAllBytes(Paths.get(path1));

            byte[] candidateImage = Files.readAllBytes(Paths.get(path2));

            FingerprintTemplate probe = new FingerprintTemplate(probeImage);

// 由于直接从二进制中生成指纹模板非常消耗性能,推荐第一次使用后序列话成JSON,内部提供方法。再通过json生成模板

// String jsonTemplete=probe.json();

// probe=new FingerprintTemplate(jsonTemplete);

            FingerprintTemplate candidate = new FingerprintTemplate(candidateImage);

            FingerprintMatcher matcher = new FingerprintMatcher(probe);

            double score = matcher.match(candidate);

            System.out.println("匹配得分:" + score);

            match = score >= 40;

            System.out.println("是否匹配:" + match);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return match;
    }


    public static void main(String[] args) {

        try {

//首先是读取两张对比的指纹图片,图片必须是白色背景，指纹为黑色

            byte[] probeImage = Files.readAllBytes(Paths.get("E:\\项目1\\冯超毕设\\指纹考勤管理系统 定金400 总价 1200\\测试图片\\001.bmp"));

            byte[] candidateImage = Files.readAllBytes(Paths.get("E:\\项目1\\冯超毕设\\指纹考勤管理系统 定金400 总价 1200\\指纹训练数据集\\指纹训练数据集\\408张训练集\\0012_3_1.bmp"));

            FingerprintTemplate probe = new FingerprintTemplate(probeImage);

// 由于直接从二进制中生成指纹模板非常消耗性能,推荐第一次使用后序列话成JSON,内部提供方法。再通过json生成模板

// String jsonTemplete=probe.json();

// probe=new FingerprintTemplate(jsonTemplete);

            FingerprintTemplate candidate = new FingerprintTemplate(candidateImage);

            FingerprintMatcher matcher = new FingerprintMatcher(probe);

            double score = matcher.match(candidate);

            System.out.println("匹配得分:" + score);

            boolean match = score >= 40;

            System.out.println("是否匹配:" + match);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

}
