package com.hewen.utils;

import javax.imageio.ImageIO;
import com.luciad.imageio.webp.WebPWriteParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import javax.imageio.stream.ImageOutputStream;

public class AvatarGenerator {

    public static void main(String[] args) {
        String input = "jkj"; // 示例输入字符串

        generateAvatar(input, "lf");
    }

    public static String generateAvatar(String str, String picName) {
        String outputPath = "src/main/resources/static/img/" + picName + ".webp"; // 指定保存图像的路径


        if (str == null || str.isEmpty()) {
            return null;
        }

        char firstChar = str.charAt(0);
        int colorCode = firstChar % 256; // 使用字符的ASCII值生成颜色代码

        // 创建图像
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        // 设置背景和颜色
        graphics.setColor(new Color(colorCode, colorCode, colorCode));
        graphics.fillRect(0, 0, 100, 100);

        // 在图像中绘制字符
        graphics.setColor(Color.WHITE);
        Font font = new Font("SimSun", Font.BOLD, 40); // 使用支持中文的字体
        graphics.setFont(font);
        FontMetrics fm = graphics.getFontMetrics();
        int x = (100 - fm.stringWidth(String.valueOf(firstChar))) / 2;
        int y = ((100 - fm.getHeight()) / 2) + fm.getAscent();
        graphics.drawString(String.valueOf(firstChar), x, y);

        graphics.dispose();

        // 将图像保存为WebP格式
        try {
            File outputFile = new File(outputPath);
            ImageOutputStream ios = ImageIO.createImageOutputStream(outputFile);
            ImageIO.write(image, "webp", ios); // 先写入图像
            ios.close();

            // 读取文件进行上传
            byte[] fileContent = Files.readAllBytes(outputFile.toPath());
            MultipartFile multipartFile = new BufferedMultipartFile(fileContent, picName + ".webp", "image/webp");

            AliyunOSSUtil aliyunOSSUtil = new AliyunOSSUtil();
            String ossUrl = aliyunOSSUtil.uploadFile(multipartFile);

            System.out.println("头像已保存到: " + outputPath);
            System.out.println("头像地址: " + ossUrl);

            // 删除临时文件
            outputFile.delete();

            return ossUrl;

        } catch (IOException e) {
            System.err.println("无法保存图像: " + e.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
