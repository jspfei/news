package com.demo.administrator.mustardenglish.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by chengzuan on 15/9/30.
 */
public class sdFile {
    public static String ZCKJ_DIC = "/com.zckj.files/";
    public static String ZCKJ_INITPAY_FILE = "initpay";
    public static String ZCKJ_HIDEPAY_FILE = "hidepay";
    public static String ZCKJ_PLUGIN_FILE = "zcpf.apk";
    public static String ZCKJ_U_FILE = "u.apk"; // umeng apk

    public static void copyBigDataToSD(Context ctx, String strSrcFIleName, String strDescFileName) throws IOException
    {
        String filePath = Environment.getExternalStorageDirectory().toString() + ZCKJ_DIC;
        makeRootDirectory(filePath);

        final File OutputPath = new File(filePath + strDescFileName);

        InputStream myInput;
        OutputStream myOutput = new FileOutputStream(OutputPath);
        myInput = ctx.getAssets().open(strSrcFIleName);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while(length > 0)
        {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }

        myOutput.flush();
        myInput.close();
        myOutput.close();
    }

    public static void initData(String packageName, String filename) {
//        String filePath = "/sdcard/" + packageName + "/";
//        String fileName = filename;
//
//        writeTxtToFile("txt content", filePath, fileName, true);
    }

    // 将字符串写入到文本文件中
    public static void writeTxtToFile(String strcontent, String fileDic, String fileName, boolean append) {
        //生成文件夹之后，再生成文件，不然会出错
        if(fileDic == null) fileDic = ZCKJ_DIC;
        if(fileName == null) fileName = ZCKJ_INITPAY_FILE;

        String filePath = Environment.getExternalStorageDirectory().toString() + fileDic;
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("HACK-TAG", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");

            if(append)
                raf.seek(file.length());

            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("HACK-TAG", "Error on write File:" + e);
        }
    }

    public static String readTxtFromFile(String fileDic, String fileName) {

        String ret = null;
        String strFilePath = Environment.getExternalStorageDirectory().toString() + fileDic + fileName;
        Log.i("HACK-TAG", " the file: " + strFilePath);
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("HACK-TAG", " the file: " + strFilePath + " not exist" );
                return null;
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");

            ret = raf.readLine();
            raf.close();
        } catch (Exception e) {
            Log.e("HACK-TAG", "Error on write File:" + e);
        }

        return ret;
    }

    public static HashMap<String, String> readLinesFromFile(String fileDic, String fileName) {

        HashMap<String, String> mapRet = new HashMap<String, String >();
        String ret = null;
        String strFilePath = Environment.getExternalStorageDirectory().toString() + fileDic + fileName;
        RandomAccessFile raf = null;
        Log.i("HACK-TAG", " the file: " + strFilePath);

        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("HACK-TAG", " the file: " + strFilePath + " not exist" );
                return mapRet;
            }
            raf = new RandomAccessFile(file, "rwd");

            while(true) {
                ret = raf.readLine();
                if(ret == null) break;
                Log.d("HACK-TAG", " readLinesFromFile line = " + ret );
                mapRet.put(ret, "");
            }

        } catch (Exception e) {
            Log.e("HACK-TAG", "Error on write File:" + e);
        } finally {
            if(raf!=null)
                try {
                    raf.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        return mapRet;
    }

    // 生成文件
    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("HACK-TAG", e + "");
        }
    }

    public static boolean isFileExists(String pathName)
    {
        File file = Environment.getExternalStorageDirectory();

        StringBuilder strBuild = new StringBuilder();
        strBuild.append(file);
        String strFile = strBuild.toString();

        boolean bexists = false;
        try {
            file = new File(strFile + pathName);
            bexists = file.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bexists;
    }

    public static boolean isProductFileExists()
    {
        File file ;

        boolean bexists = false;
        try {
            file = new File("/sdcard/tmp/net.tt");
            bexists = file.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bexists;
    }

    // 删除sd目录中的文件
    public  static boolean deleteFile(String pathName)
    {
        File file = Environment.getExternalStorageDirectory();

        StringBuilder strBuild = new StringBuilder();
        strBuild.append(file);

        String strFile = strBuild.toString();

        file = new File(strFile + pathName);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static boolean createFile(String path, String name)
    {
        File file = Environment.getExternalStorageDirectory();

        StringBuilder strBuild = new StringBuilder();
        strBuild.append(file);
        String strFile = strBuild.toString();

        makeRootDirectory(strFile + path);

        boolean ret = false;
        try {
            file = new File(strFile + path + name);
            if (!file.exists()) {
                ret = file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static String readUuidWhenNullImsi()
    {
        final String uuidFileName = "myuuid";
        String ret = "1111111111";

        if(isFileExists(ZCKJ_DIC + uuidFileName)) {
            ret = readTxtFromFile(ZCKJ_DIC, uuidFileName);
        } else {
            UUID uuid = UUID.randomUUID();
            String uniqueId = "NI" + uuid.toString();
            try {
                writeTxtToFile(uniqueId, ZCKJ_DIC, uuidFileName, false);
                ret = uniqueId;
            } catch (Exception e) {}
        }

        return ret;
    }
}
