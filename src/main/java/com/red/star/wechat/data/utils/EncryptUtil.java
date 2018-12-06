package com.red.star.wechat.data.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具
 *
 * @author wWX326236
 * @version [版本号, 2017-3-3]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EncryptUtil {
    /**
     * base64加密
     *
     * @param bt 被sha256加密过后的byte
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String base64(byte[] bt) {
        String s = null;
        if (null != bt) {
            s = new BASE64Encoder().encode(bt);
        }

        return s;
    }

    /**
     * sha256加密
     *
     * @param param 加密的参数
     * @return
     * @throws NoSuchAlgorithmException
     * @see [类、类#方法、类#成员]
     */
    public static byte[] SHA256(String param)
            throws NoSuchAlgorithmException {
        byte[] bt = param.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bt);
        String s = md.toString();
        return md.digest();
    }
}
