package com.hc.echart;

import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Base64 {
    /**
     * @return java.lang.String
     * @means 图片文件转为base64
     * 转换后的编码没有头，需要在保存时手动添加“data:image/png;base64,”,如不需要，即不做调整
     * @Author HC
     * @Date 2021/4/21
     * @Param [imgPath , type]  1返回带有“data:image/png;base64,” 其余不包含
     **/
    public static String img2base64(String imgPath, int type) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        String result = encoder.encode(Objects.requireNonNull(data));
        if (type == 1) {
            StringBuffer sb = new StringBuffer();
            sb.append("data:image/png;base64,").append(result);
            return sb.toString();
        } else {
            return result;
        }
    }

    public static boolean deleteFile(String pathname) {
        boolean result = false;
        File file = new File(pathname);
        if (file.exists()) {
            file.delete();
            result = true;
        }
        return result;
    }


}
