package com.demo.util;

import java.nio.charset.Charset;

/**
 * Java基本数据类型和byte数组相互转化
 * @author liuyazhuang
 *
 */
public class ByteUtil {

    public static byte getBytes(boolean val){
        return (byte) (val ? 0x01:0x00);
    }

    public static byte[] getBytes(short data) {
        byte[] bytes = new byte[2];
        bytes[1] = (byte) (data & 0xff);
        bytes[0] = (byte) ((data & 0xff00) >> 8);
        return bytes;
    }

    public static byte[] getBytes(char data) {
        byte[] bytes = new byte[2];
        bytes[1] = (byte) (data);
        bytes[0] = (byte) (data >> 8);
        return bytes;
    }

    public static byte[] getBytes(int data) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) (data & 0xff);
        bytes[2] = (byte) ((data & 0xff00) >> 8);
        bytes[1] = (byte) ((data & 0xff0000) >> 16);
        bytes[0] = (byte) ((data & 0xff000000) >> 24);
        return bytes;
    }

    public static byte[] getBytes(long data) {
        byte[] bytes = new byte[8];
        bytes[7] = (byte) (data & 0xff);
        bytes[6] = (byte) ((data >> 8) & 0xff);
        bytes[5] = (byte) ((data >> 16) & 0xff);
        bytes[4] = (byte) ((data >> 24) & 0xff);
        bytes[3] = (byte) ((data >> 32) & 0xff);
        bytes[2] = (byte) ((data >> 40) & 0xff);
        bytes[1] = (byte) ((data >> 48) & 0xff);
        bytes[0] = (byte) ((data >> 56) & 0xff);
        return bytes;
    }

    public static byte[] getBytes(float data) {
        int intBits = Float.floatToIntBits(data);
        return getBytes(intBits);
    }

    public static byte[] getBytes(double data) {
        long intBits = Double.doubleToLongBits(data);
        return getBytes(intBits);
    }

    public static byte[] getBytes(String data, String charsetName) {
        Charset charset = Charset.forName(charsetName);
        return data.getBytes(charset);
    }

    public static byte[] getBytes(String data) {
        return getBytes(data, "UTF-8");
    }

    public static boolean getBool(byte val){
        return val != 0x00;
    }

    public static short getShort(byte[] bytes) {
        return (short) ((0xff & bytes[1]) | (0xff00 & (bytes[0] << 8)));
    }

    public static char getChar(byte[] bytes) {
        return (char) ((0xff & bytes[1]) | (0xff00 & (bytes[0] << 8)));
    }

    public static int getInt(byte[] bytes) {
        return (0xff & bytes[3]) | (0xff00 & (bytes[2] << 8)) | (0xff0000 & (bytes[1] << 16))
                | (0xff000000 & (bytes[0] << 24));
    }

    public static long getLong(byte[] bytes) {
        return (0xffL & (long) bytes[7]) | (0xff00L & ((long) bytes[6] << 8)) | (0xff0000L & ((long) bytes[5] << 16))
                | (0xff000000L & ((long) bytes[4] << 24)) | (0xff00000000L & ((long) bytes[3] << 32))
                | (0xff0000000000L & ((long) bytes[2] << 40)) | (0xff000000000000L & ((long) bytes[1] << 48))
                | (0xff00000000000000L & ((long) bytes[0] << 56));
    }

    public static float getFloat(byte[] bytes) {
        return Float.intBitsToFloat(getInt(bytes));
    }

    public static double getDouble(byte[] bytes) {
        long l = getLong(bytes);
        return Double.longBitsToDouble(l);
    }

    public static String getString(byte[] bytes, String charsetName) {
        return new String(bytes, Charset.forName(charsetName));
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    public static String getString(byte[] bytes) {
        return getString(bytes, "UTF-8");
    }

    public static void main(String[] args) {
        short s = 123;
        int i = 123;
        long l = 123333;

        char c = '1';

        float f = 123.22f;
        double d = 123.22;

        String string = "我是好孩子";
        System.out.println(s);
        System.out.println(i);
        System.out.println(l);
        System.out.println(c);
        System.out.println(f);
        System.out.println(d);
        System.out.println(string);

        System.out.println("**************");

        System.out.println(getShort(getBytes(s)));
        System.out.println(getInt(getBytes(i)));
        System.out.println(getLong(getBytes(l)));
        System.out.println(getChar(getBytes(c)));
        System.out.println(getFloat(getBytes(f)));
        System.out.println(getDouble(getBytes(d)));
        System.out.println(getString(getBytes(string)));
    }
}
