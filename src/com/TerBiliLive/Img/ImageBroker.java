package com.TerBiliLive.Img;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageBroker {

    public static ImageIcon loadImage(String filename) throws IOException {
        InputStream is = ImageBroker.class.getResourceAsStream(filename);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            while (true) {
                int b = is.read();
                if (b >= 0) {
                    os.write(b);
                } else {
                    break;
                }
            }
            return new ImageIcon(os.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            is.close();
            os.close();
        }
    }

    public static ImageIcon loadImageIcon(String imgUrl){
        //设置图片
        try {
            return ImageBroker.loadImage(imgUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image loadImages(String iconUrl){
        //设置图标
        try {
            return ImageBroker.loadImage(iconUrl).getImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

