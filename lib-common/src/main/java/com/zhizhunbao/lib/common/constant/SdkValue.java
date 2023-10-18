package com.zhizhunbao.lib.common.constant;

/**
 * 扫码枪指令集
 */
public class SdkValue {

    //eventbus type
    //收到消息
    public final static int SdkMessageType_Receve = 101;
    //发出指令
    public final static int SdkMessageType_Send = 102;
    //断开spp蓝牙并停止服务
    public final static int getSdkMessageType_DisSppBluetoothAndServce = 103;
    //断开ble蓝牙并停止服务
    public final static int getSdkMessageType_DisBleBluetoothAndServce = 104;
    //
    public final static int SdkMessageType_ConnectUsb = 105;

    //蓝牙连接成功
    public final static int connectbt_success = 106;
    //蓝牙连接失败
    public final static int connectbt_fail = 107;

    public final static String MODELSTYLE_SE4750 = "SE4750";

    public final static String MODELSTYLE_NT_C06H = "NT-C06H";

    public final static String MODELSTYLE_EX25 = "EX25";

    public final static String MODELSTYLE_NT_280H = "NT280H";

    //    usbhid连接模式
    public final static int MODE_USBHID = 1;
    //    usbhid连接模式
    public final static int MODE_USBVCOMM = 2;
    //    蓝牙HID连接模式
    public final static int MODE_BLURTOOTHHID = 3;
    //    蓝牙SPP连接模式
    public final static int MODE_BLURTOOTHSPP = 4;
    //    蓝牙Ble连接模式
    public final static int MODE_BLURTOOTHBLE = 5;

    //指令集
    //手机接口为micro_b, MCU重启
    public static String USB_MICRO_B = "%%0UB";
    //手机接口为type_c MCU重启
    public static String USB_TYPE_C = "%%0UC";
    //蓝牙传输
    public static String TRANSPORT_BLUETOOTH = "%000601";
    //USB_HID传输
    public static String TRANSPORT_USBHID = "%000602";
    //USB_SPP传输
    public static String TRANSPORT_USBSPP = "%000603";
    //AUTO传输模式
    public static String TRANSPORT_AUTO = "%000604";
    //AUTO传输模式
    public static String TRANSPORT_NAUTO = "%000605";


    //断开当前蓝牙配对其它接受设备
    public static String DISCONNECT_OTHERBT = "%%ALL-CH";
    //蓝牙SPP模式
    public static String TRANSPORT_BTSPP = "AT+MODE=1";
    //蓝牙SPP模式
    public static String TRANSPORT_BTHID = "AT+MODE=2";
    //蓝牙BLE模式
    public static String TRANSPORT_BTBLE = "AT+MODE=3";
    //查询蓝牙模块程序版本号
    public static String BT_VERSION = "AT+VER";
    //重启蓝牙
    public static String BT_REBOOT = "AT+REBOOT";
    //恢复出厂设置
    public static String RESTORE = "AT+RESTORE";
    //有线HID多键传送开启
    public static String KB_KEYMUL_ON = "%00060<";
    //有线HID多键传送关闭
    public static String KB_KEYMUL_OFF = "%00060=";

    //普通工作模式
    public static String Normal_Mode = "%#NORMD";
    //储存工作模式
    public static String Store_Mode = "%#INVMD";
    //储存数据条数
    public static String Date_Count = "%#+TCNT";
    //储存数据上传
    public static String Uploading = "%#TXMEM";
    //储存数据上传
    public static String Clear_Memory = "%#*NEW*";
    //获取 二维模组型号
    public static String MODE_STYLE = "%MODULESN#";
    //扫描
    public static String scanner = "%SCANTM#3#";


    //查看程序版本号
    public static String NETUM_VERSION = "%%HDVersn";
    //获取电池电量
    public static String Battery_Volume = "%BAT_VOL#";
    //恢复出厂设置
    public static String Factory_Setting = "%000608";
    //系统重启
    public static String System_Reset = "%000609";
    //高音量
    public static final String HIGH_SOUND = "%000606";
    //中音量
    public static final String MIDDLE_SOUND = "%00060;";
    //低音量
    public static final String LOW_SOUND = "%00060E";
    //音量关闭
    public static final String OFF_SOUND = "%000607";
    //高音调
    public static final String HIGH_TONE = "%00060C";
    //低音调
    public static final String LOW_TONE = "%00060D";

    //震动开启
    public static final String Vibration_ON = "%00060>";
    //震动关闭
    public static final String Vibration_OFF = "%00060?";

    //立即关机
    public static final String Power_Down = "%%0P";
    //睡眠时间30秒
    public static final String Sleeptime_A = "%%01";
    //睡眠时间60秒
    public static final String Sleeptime_B = "%%02";
    //睡眠时间3分钟
    public static final String Sleeptime_C = "%%03";
    //睡眠时间6分钟
    public static final String Sleeptime_D = "%%04";
    //睡眠时间12分钟
    public static final String Sleeptime_E = "%%05";
    //睡眠时间30分钟
    public static final String Sleeptime_F = "%%06";
    //睡眠时间1小时
    public static final String Sleeptime_G = "%%07";
    //睡眠时间2小时
    public static final String Sleeptime_H = "%%08";
    //永不休眠
    public static final String Sleeptime_I = "%%09";

    // V4 产品键盘设置命令
    //美国键盘
    public static final String America_key = "A0100";
    //法国键盘
    public static final String French_key = "A0101";
    //德国键盘
    public static final String Germany_key = "A0102";
    //土耳其Q键盘
    public static final String TurkeyQ_key = "A0103";
    //土耳其F键盘
    public static final String TurkeyF_key = "A0104";
    //葡萄牙键盘
    public static final String Portugal_key = "A0105";
    //西班牙键盘
    public static final String Spain_key = "A0106";
    //捷克键盘
    public static final String CZECH_key = "A0107";
    //意大利键盘
    public static final String Italy_key = "A0108";
    //比利时法语键盘
    public static final String Belgium_Key = "A0109";
    //巴西葡萄牙语键盘
    public static final String Brazil_Key = "A010:";
    //加拿大法语
    public static final String CanadianFR_Key = "A010;";
    //克罗地亚键盘
    public static final String Croatia_Key = "A010<";

    //斯洛伐克键盘
    public static final String Slovak_Key = "A010=";
    //丹麦键盘
    public static final String Denmark_Key = "A010>";
    //芬兰键盘
    public static final String Finland_Key = "A010?";
    //匈牙利键盘
    public static final String Hungary_Key = "A010@";
    //拉丁美洲(西班牙语)键盘
    public static final String LatinAmerica_Key = "A010A";
    //荷兰键盘
    public static final String Netherland_Key = "A010B";
    //挪威键盘
    public static final String Norway_Key = "A010C";
    //波兰键盘
    public static final String Poland_Key = "A010D";
    //塞尔维亚(拉丁文)键盘
    public static final String Serbia_Key = "A010E";
    //斯洛文尼亚键盘
    public static final String Sloenia_Key = "A010F";
    //瑞典键盘
    public static final String Sweden_Key = "A010G";
    //瑞士-德语键盘
    public static final String SwissDE_Key = "A010I";
    //英国_英语键盘
    public static final String UK_Key = "A010J";
    //日语键盘
    public static final String Japanese_Key = "A010K";
    //泰语键盘
    public static final String Thailand_Key = "A010L";
    //ALT通用键盘
    public static final String Alt_Key = "A010M";


    //C 系列产品键盘设置
    //读取当前键盘设置
    public static final String C_Read_KeySeting = "$LAN#";
    //美国键盘设置
    public static final String C_America_EN_key = "$LAN#EN";
    //法国键盘设置
    public static final String C_French_key = "$LAN#FR";

    //德国键盘设置
    public static final String C_Germany_key = "$LAN#GE";
    //意大利键盘设置
    public static final String C_Italy_key = "$LAN#IT";
    //葡萄牙键盘设置
    public static final String C_Portugal_key = "$LAN#PT";
    //西班牙键盘设置
    public static final String C_Spain_key = "$LAN#ES";
    //土耳其Q键盘设置
    public static final String C_Turkey_Qkey = "$LAN#TK";
    //土耳其F键盘设置
    public static final String C_Turkey_Fkey = "$LAN#TF";
    //英国键盘设置
    public static final String C_UK_key = "$LAN#UK";
    //捷克键盘设置
    public static final String C_Czech_key = "$LAN#CS";
    //匈牙利键盘设置
    public static final String C_Hungary_key = "$LAN#HU";
    //比利时法语键盘设置
    public static final String C_Belgium_FR_Key = "$LAN#FB";
    //巴西(葡萄牙语)键盘设置
    public static final String C_Brazil_PT_Key = "$LAN#PB";
    //加拿大(法语)键盘设置
    public static final String C_Canadian_FR_Key = "$LAN#FC";
    //克罗地亚键盘设置
    public static final String C_Croatia_Key = "$LAN#HR";
    //斯洛伐克键盘设置
    public static final String C_Slovak_Key = "$LAN#SK";
    //丹麦键盘设置
    public static final String C_Denmark_Key = "$LAN#DA";
    //芬兰键盘设置
    public static final String C_Finland_Key = "$LAN#FI";
    //拉丁美洲(西班牙语)键盘设置
    public static final String C_Latin_America_ES_Key = "$LAN#EL";
    //荷兰键盘设置
    public static final String C_Netherland_Key = "$LAN#NL";
    //挪威键盘设置
    public static final String C_Norway_Key = "$LAN#NO";
    //波兰键盘设置
    public static final String C_Poland_Key = "$LAN#PL";
    //塞尔维亚(拉丁文)键盘设置
    public static final String C_Serbia_Key = "$LAN#SR";
    //斯洛文尼亚键盘设置
    public static final String C_Slovenia_Key= "$LAN#SL";
    //瑞典键盘设置
    public static final String C_Sweden_Key= "$LAN#SV";
    //瑞士(德语)键盘设置
    public static final String C_Swiss_DE_Key= "$LAN#DS";
    //日语键盘设置
    public static final String C_Japanese_Key= "$LAN#JP";
    //国际通用键盘设置
    public static final String C_ALT_Global_Key= "$LAN#AG";
    //ALT单字节特殊字符键盘设置
    public static final String C_ALT_Single_Byte_Special_Key= "$LAN#RU";



    //输出字符集设置
    //*普通输出字符集
    public static final String Normal_Character_Set = "%CHARSET#0";
    //输出字符集为GBK
    public static final String GBK_Character_Set = "%CHARSET#1";
    //输出字符集为UTF8
    public static final String UTF8_Character_Set = "%CHARSET#2";


    //有线键盘传输速度设定
    //%KB#SP后跟随的字符范围为0x30~0x3F，值越大代表速度越慢.
    //*有线传输低延时
    public static final String USB_KB_High_Speed = "%KB#SP0";
    //有线传输中延时
    public static final String USB_KB_Middle_Speed = "%KB#SP7";
    //有线传输高延时
    public static final String USB_KB_Low_Speed = "%KB#SP?";

    // 蓝牙键盘传输速度设定
    //等号后跟随的字符范围为十进制1~32，值越大代表速度越慢.
    //蓝牙传输速度查询
    public static final String Qurey_BlutoothSpeed = "AT+HIDDLY";
    //蓝牙HID低延时
    public static final String Bluetooth_HID_High_Speed = "AT+HIDDLY=2";
    //*蓝牙HID中延时
    public static final String Bluetooth_HID_Middle_Speed = "AT+HIDDLY=10";
    //*蓝牙HID高延时
    public static final String Bluetooth_HID_Low_Speed = "AT+HIDDLY=30";

    // CapsLock,NumLock
    //*正常大小写
    public static final String Caps_Lock_normal = "%LKLED#0";
    //大小写互换
    public static final String Up_Low_Case_Swap = "%LKLED#1";
    //全大写
    public static final String All_Upper_Case = "%LKLED#2";
    //全小写
    public static final String All_Lower_Case = "%LKLED#3";
    //不用小键盘
    public static final String Num_Lock_off = "%LKLED#4";
    //不用小键盘
    public static final String Num_Lock_on = "%LKLED#5";

    // 前后缀输出格式设置
    //*清除格式
    public static final String Del_Form = "$DATA#0";
    //允许后缀显示
    public static final String Later_Form = "$DATA#1";
    //允许前缀显示
    public static final String Foward_Form = "$DATA#2";
    //允许隐藏条码尾部内容
    public static final String Hide_Later_Form = "$DATA#3";
    //允许隐藏条码中部内容
    public static final String Hide_Middle_Form = "$DATA#4";
    //允许隐藏条码首部内容
    public static final String Hide_Foward_Form = "$DATA#5";
    // 补光灯常亮
    public static final String LEDOFF = "LEDOFF";
    // 补光灯扫描时开启
    public static final String LEDON = "LEDON";
    // 连续扫
    public static final String SCAN_CONT = "SCAN_CONT";
    // 自动扫
    public static final String SCAN_AUTO = "SCAN_AUTO";
    // 按键扫
    public static final String SCAN_KEY = "SCAN_KEY";
    //瞄准灯常亮关闭
    public static final String AIMED_OFF = "AIMED_OFF";
    //瞄准灯常亮开启
    public static final String AIMED_ON = "AIMED_ON";


    //NT-280H  编译器下的指令。 数组第一位 ，第二位，第三位分别对应Exid ,Excmd ,Data
    // 补光灯扫描时开启
    public static int[] N280H_LEDOFF = {0xA1, 0x04, 0x01};

    // 补光灯一直开启
    public static int[] N280H_LEDON = {0xA1, 0x04, 0x02};

    // 扫描模式 连续扫
    public static int[] N280H_SCAN_CONT = {0xA1, 0x02, 0x03};

    // 扫描模式 自动扫
    public static int[] N280H_SCAN_AUTO = {0xA1, 0x02, 0x02};

    // 扫描模式 按键扫
    public static int[] N280H_SCAN_KEY = {0xA1, 0x02, 0x01};

    // 瞄准灯 扫描时关闭
    public static int[] N280H_AIMED_OFF = {0xA1, 0x03, 0x02};

    // 瞄准灯 一直开启
    public static int[] N280H_AIMED_ON = {0xA1, 0x03, 0x01};


    //SE4750 编译器下的指令。 数组第一位 ，第二位，第三位分别对应Length,opcode,messagesource

    // 补光灯扫描时开启
    public static int[] SE4750_LEDOFF = {0x05, 0xE8, 0x04};

    // 补光灯一直开启
    public static int[] SE4750_LEDON = {0x05, 0xE7, 0x04};

    // 扫描模式 连续扫
    public static int[] SE4750_SCAN_CONT = {0xA1, 0x02, 0x03};

    // 扫描模式 自动扫
    public static int[] SE4750_SCAN_AUTO = {0xA1, 0x02, 0x02};

    // 扫描模式 按键扫
    public static int[] SE4750_SCAN_KEY = {0xA1, 0x02, 0x01};

    // 瞄准灯 扫描时关闭  length, opcode, messagesource
    public static int[] SE4750_AIMED_OFF = {0x04, 0xC4, 0x04};

    // 瞄准灯 开启  length, opcode, messagesource
    public static int[] SE4750_AIMED_ON = {0x04, 0xc5, 0x04};

//    public static int[]SE4750_AIMED_ON = new Se4750_Command();


    //EX_25 编译器下的SG指令。 数组第2位 ，第3位，第4位分别对应SG,FID,PARM,第一位01表示为SG命令


    // 扫描模式 连续扫
    public static int[] EX25_SCAN_CONT = {0x01, 0x70, 0x40, 0x00};

    // 扫描模式 自动扫*
    public static int[] EX25_SCAN_AUTO = {0x01, 0x70, 0x40, 0x04};

    // 扫描模式 按键扫
    public static int[] EX25_SCAN_KEY = {0x01, 0x70, 0x40, 0x01};


    //EX_25 编译器下的CG指令。 数组第2位 ，第3位，第4位分别对应SG,FID,PARM,第一位02表示为SG命令
    // 瞄准灯 扫描时关闭  length, opcode, messagesource
    public static int[] EX25_AIMED_OFF = {02, 0x04, 0xC4, 0x04};

    // 瞄准灯 开启  length, opcode, messagesource
    public static int[] EX25_AIMED_ON = {0x04, 0xc5, 0x04};

    // 补光灯扫描时开启
    public static int[] EX25_LEDOFF = {0x02, 0x30, 0x03};

    // 补光灯一直开启
    public static int[] EX25_LEDON = {0x02, 0x72, 0x45, 0x00};

}
