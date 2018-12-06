package com.red.star.wechat.data.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class FtpUtil {
    private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);
    private static FTPClient ftp;

    /**
     * 获取ftp连接
     *
     * @param f
     * @return
     * @throws Exception
     */
    public static boolean connectFtp(FtpConfig f) throws Exception {
        ftp = new FTPClient();
        boolean flag = false;
        int reply;
        if (f.getPort() == null) {
            ftp.connect(f.getHost(), 21);
        } else {
            ftp.connect(f.getHost(), f.getPort());
        }
        ftp.enterLocalPassiveMode();
        ftp.setDefaultPort(10012);
        ftp.login(f.getUsername(), f.getPassword());
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            return flag;
        }
        ftp.changeWorkingDirectory(f.getPath());
        flag = true;
        return flag;
    }

    /**
     * 关闭ftp连接
     */
    public static void closeFtp() {
        if (ftp != null && ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ftp上传文件
     *
     * @param f
     * @throws Exception
     */
    public static void upload(File f) throws Exception {
        if (f.isDirectory()) {
            ftp.makeDirectory(f.getName());
            ftp.changeWorkingDirectory(f.getName());
            String[] files = f.list();
            for (String fstr : files) {
                File file1 = new File(f.getPath() + "/" + fstr);
                if (file1.isDirectory()) {
                    upload(file1);
                    ftp.changeToParentDirectory();
                } else {
                    File file2 = new File(f.getPath() + "/" + fstr);
                    FileInputStream input = new FileInputStream(file2);
                    ftp.storeFile(file2.getName(), input);
                    input.close();
                }
            }
        } else {
            File file2 = new File(f.getPath());
            FileInputStream input = new FileInputStream(file2);
            ftp.storeFile(file2.getName(), input);
            input.close();
        }
    }

    /**
     * @param remote
     * @param data
     * @return
     * @throws IOException
     */
    public static boolean upload(String remote, byte[] data) throws IOException {
        if (CheckUtil.isEmpty(data)) {
            return false;
        }
        return ftp.storeFile(remote, new ByteArrayInputStream(data));
    }

    /**
     * 下载链接配置
     *
     * @param f
     * @param localBaseDir  本地目录
     * @param remoteBaseDir 远程目录
     * @throws Exception
     */
    public static void startDown(FtpConfig f, String localBaseDir, String remoteBaseDir) throws Exception {
        if (FtpUtil.connectFtp(f)) {

            try {
                FTPFile[] files = null;
                boolean changedir = ftp.changeWorkingDirectory(remoteBaseDir);
                if (changedir) {
                    ftp.setControlEncoding("GBK");
                    files = ftp.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        try {
                            downloadFile(files[i], localBaseDir, remoteBaseDir);
                        } catch (Exception e) {
                            logger.error("下载失败", e);
                            logger.error("<" + files[i].getName() + ">下载失败");
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("下载过程中出现异常", e);
            }
        } else {
            logger.error("链接失败！");
        }

    }

    /**
     * 127      *
     * 128      * 下载FTP文件
     * 129      * 当你需要下载FTP文件的时候，调用此方法
     * 130      * 根据<b>获取的文件名，本地地址，远程地址</b>进行下载
     * 131      *
     * 132      * @param ftpFile
     * 133      * @param relativeLocalPath
     * 134      * @param relativeRemotePath
     * 135
     */
    private static void downloadFile(FTPFile ftpFile, String relativeLocalPath, String relativeRemotePath) {
        if (ftpFile.isFile()) {
            if (ftpFile.getName().indexOf("?") == -1) {
                OutputStream outputStream = null;
                try {
                    File locaFile = new File(relativeLocalPath + ftpFile.getName());
                    //判断文件是否存在，存在则返回
                    if (locaFile.exists()) {
                        return;
                    } else {
                        outputStream = new FileOutputStream(relativeLocalPath + ftpFile.getName());
                        ftp.retrieveFile(ftpFile.getName(), outputStream);
                        outputStream.flush();
                        outputStream.close();
                    }
                } catch (Exception e) {
                    logger.error("输出文件流异常", e);
                } finally {
                    try {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        logger.error("输出文件流异常", e);
                    }
                }
            }
        } else {
            String newlocalRelatePath = relativeLocalPath + ftpFile.getName();
            String newRemote = new String(relativeRemotePath + ftpFile.getName().toString());
            File fl = new File(newlocalRelatePath);
            if (!fl.exists()) {
                fl.mkdirs();
            }
            try {
                newlocalRelatePath = newlocalRelatePath + '/';
                newRemote = newRemote + "/";
                String currentWorkDir = ftpFile.getName().toString();
                boolean changedir = ftp.changeWorkingDirectory(currentWorkDir);
                if (changedir) {
                    FTPFile[] files = null;
                    files = ftp.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        downloadFile(files[i], newlocalRelatePath, newRemote);
                    }
                }
                if (changedir) {
                    ftp.changeToParentDirectory();
                }
            } catch (Exception e) {
                logger.error("文件从ftp下载失败", e);
            }
        }
    }

}
