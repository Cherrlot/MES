package com.zhizhunbao.lib.common.util;

import android.util.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 扫码枪的工具类
 */
@SuppressWarnings("unchecked")
public class NetumUtil {

    //通用——根据指令计算对应的byte[]
    public static byte[] getCommandBtye(String command) {
        ArrayList<Byte> list_command = new ArrayList();
        List<Byte> date = getDate(command);
        list_command.add((byte) 0x02);
        list_command.add((byte) (date.size() + 4));
        list_command.add((byte) 0x0A);
        list_command.addAll(date);
        List<Byte> list = Arrays.asList(toObjects(getChk(date)));
        ArrayList<Byte> arrayList = new ArrayList<>(list);
        list_command.addAll(arrayList);
        list_command.add((byte) 0x03);
        Log.d("tag",Arrays.toString(list_command.toArray()));
//        Log.d("tag",NetumUtil.byte2Hex2(list_command.toArray()));
        return toPrimitives(list_command.toArray(new Byte[list_command.size()]));
    }

    /** 计算校验位 ，返回十六进制校验位 */
    public static String nt280h_MakeCheckSum(String data) {
        int dSum = 0;
        int length = data.length();
        int index = 0;

        // 遍历十六进制，并计算总和
        while (index < length) {
            String s = data.substring(index, index + 2); // 截取2位字符
            dSum += Integer.parseInt(s, 16); // 十六进制转成十进制 , 并计算十进制的总和
            index = index + 2;
        }

        int mod = dSum % 256; // 用256取余，十六进制最大是FF，FF的十进制是255
        String checkSumHex = Integer.toHexString(mod); // 余数转成十六进制
        length = checkSumHex.length();
        if (length < 2) {
            checkSumHex = "0" + checkSumHex;  // 校验位不足两位的，在前面补0
        }
        return ""+dSum;
    }

    //通用——根据指令计算对应的byte[]
    public static byte[] getChk(List<Byte> date) {

        int sum = 0;
        int bt_length = date.size() + 4;
        int size = date.size() + 2;
        for (int s = 0; s < size; s++) {
            switch (s) {
                case 0:
                    sum = (sum + (bt_length * (size - s)));
                    break;
                case 1:
                    sum =(sum + ((byte) 0x0A * (size - s)));
                    break;
                default:
                    int k = date.get(s - 2) * (size - s);
                    sum = sum + k;
                    break;
            }
        }


        int chk = new BigInteger("10000", 16).intValue() - (sum & 0xffff);

        Log.d("tag",""+chk);
        byte[] t = new byte[2];

        for (int s = 0; s < 2; s++) {
            switch (s) {
                case 0:
                    t[0] = (byte) ((chk & 0xFF00) >> 8);
                    break;
                case 1:
                    t[1] = (byte) (chk & 0xff);
                    break;
            }
        }
        Log.d("tag",Arrays.toString(t));
        return t;
    }

    //通用——根据指令计算对应的byte[]  Calculate the corresponding byte[] according to the instruction
    public static byte[] getEx25Chk(List<Integer> date) {

        int sum = 0;
        int bt_length = date.size();
        int size = date.size();
        for (int s = 0; s < size; s++) {
            switch (s) {
                default:
                    int k = date.get(s) * (size - s);
                    sum = sum + k;
                    break;
            }
        }

        int chk = sum;

        Log.d("tag",""+chk);

        byte[] t = new byte[2];
        for (int s = 0; s < 2; s++) {
            switch (s) {
                case 0:
                    t[0] = (byte) ((chk & 0xFF00) >> 8);
                    break;
                case 1:
                    t[1] = (byte) (chk & 0xff);
                    break;
            }
        }
        Log.d("tag",Arrays.toString(t));
        return t;
    }



    public static ArrayList<Byte> getDate(String command) {
        ArrayList<Byte> date = new ArrayList<>();
        for (int i = 0; i < command.length(); i++) {
            Log.d("tag", "size:" + ("" + command.charAt(i)).getBytes().length);
            date.add(("" + command.charAt(i)).getBytes()[0]);
        }
        return date;
    }


    public static byte[] toPrimitives(Byte[] oBytes) {
        byte[] bytes = new byte[oBytes.length];

        for (int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }
        return bytes;
    }


    // byte[] to Byte[]
    public static Byte[] toObjects(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];

        int i = 0;
        for (byte b : bytesPrim) bytes[i++] = b; // Autoboxing
        return bytes;
    }


    /**
     * 　　* 将byte转为16进制
     * 　　* @param bytes
     * 　　* @return
     */
    public static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
//1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }


    /**
     *        Convert byte[] to hexadecimal
     * 　　* 将byte转为16进制
     * 　　* @param bytes
     * 　　* @return
     */
    public static String byte2Hex2(Object[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString((byte)bytes[i] & 0xFF);
            if (temp.length() == 1) {
//1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }



    /**
     *Convert from hexadecimal string to byte array
     * 从十六进制字符串到字节数组转换
     */
    public static byte[] hexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

}
