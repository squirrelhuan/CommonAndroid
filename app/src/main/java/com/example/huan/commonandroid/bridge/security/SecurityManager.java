package com.example.huan.commonandroid.bridge.security;

import android.content.Context;

import com.example.huan.commonandroid.bridge.BridgeLifeCycleListener;
import com.example.huan.commonandroid.capabilities.security.SecurityUtils;

import java.security.MessageDigest;


/**
 * <加解密管理类>
 *
 * @author caoyinfei
 * @version [版本号, 2016/6/12]
 * @see [相关类/方法]
 * @since [V1]
 */
public class SecurityManager implements BridgeLifeCycleListener {
    @Override
    public void initOnApplicationCreate(Context context) {

    }

    @Override
    public void clearOnApplicationQuit() {

    }

    /**
     * md5 加密
     *
     * @param str
     * @return
     */
    public String get32MD5Str(String str) {
        return SecurityUtils.get32MD5Str(str);
    }

    public String encryptSHA(byte[] data) {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
            sha.update(data);
            return bytesToHexString(sha.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytesToHexString(sha.digest());
    }

    //二进制转十六进制
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
