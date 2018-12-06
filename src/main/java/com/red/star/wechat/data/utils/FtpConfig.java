package com.red.star.wechat.data.utils;

import com.red.star.wechat.data.entity.SysParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FtpConfig {

    /**
     * ip地址
     **/
    private String host;

    /**
     * 端口号
     **/
    private Integer port = 21;

    /**
     * 用户名
     **/
    private String username;

    /**
     * 密码
     **/
    private String password;

    /**
     * 路径
     **/
    private String path;

    public FtpConfig(String host, String username, String password) {
        this(host, 21, username, password, null);
    }

    /**
     * 默认配置
     */
    private static FtpConfig DEFAULT_FTP_CONFIG = null;

    /**
     * 获取默认ftp配置
     *
     * @return ftp配置信息
     */
    public static FtpConfig defaultConfig() {
        if (DEFAULT_FTP_CONFIG == null) {
            DEFAULT_FTP_CONFIG = new FtpConfig(SysParam.FTP_HOST, SysParam.FTP_USERNAME, SysParam.FTP_PASSWORD);
        }
        return DEFAULT_FTP_CONFIG;
    }
}
