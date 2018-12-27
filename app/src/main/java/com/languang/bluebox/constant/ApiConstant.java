package com.languang.bluebox.constant;

/**
 * 接口
 *
 * @author 49829
 * @date 2018/4/8
 */
public class ApiConstant {
    /**
     * 云端基础地址
     */
    public static final String CLOUD_BASE_URL = "http://cloud.haotuwei.com";
    /**
     * 盒子基础地址
     */
    public static  String BOX_BASE_URL = "http://box.haotuwei.com";
    //云端api
    /**
     * 刷新
     */
    public static final String CLOUD_REFRESH = CLOUD_BASE_URL + "/refresh";
    /**
     * 注册
     */
    public static final String CLOUD_SIGNUP = CLOUD_BASE_URL + "/signup";
    /**
     * 云端登录
     */
    public static final String CLOUD_LOGIN = CLOUD_BASE_URL + "/login";
    /**
     * 验证码
     */
    public static final String CLOUD_SENSMS = CLOUD_BASE_URL + "/sensms";
    /**
     * 检查手机
     */
    public static final String CLOUD_CHECK_MOBILE = CLOUD_BASE_URL + "/checkmobile";
    /**
     * 设置密码
     */
    public static final String CLOUD_SET_PWD = CLOUD_BASE_URL + "/setpwd";
    /**
     * 盒子列表
     */
    public static final String CLOUD_BLUES = CLOUD_BASE_URL + "/blues";
    /**
     * 新增盒子
     */
    public static final String CLOUD_BLUE_INSERT = CLOUD_BASE_URL + "/blueinsert";
    /**
     * 设置盒子
     */
    public static final String CLOUD_BLUE_SET = CLOUD_BASE_URL + "/blueset";
    /**
     * 删除盒子
     */
    public static final String CLOUD_BLUE_DEL = CLOUD_BASE_URL + "/bluedel";
    /**
     * 发送消息
     */
    public static final String CLOUD_PULL_MSG = CLOUD_BASE_URL + "/pullmsg";
    /**
     * 接收消息
     */
    public static final String CLOUD_PUSH_COM = CLOUD_BASE_URL + "/pushcom";
    //box端api
    /**
     * 盒子登录
     */
    public static final String BOX_LOGIN = BOX_BASE_URL + "/boxlogin";
    /**
     * 图片列表
     */
    public static final String BOX_LIST_IMG = BOX_BASE_URL + "/listimg";
    /**
     * 图片数量
     */
    public static final String BOX_COUNT_IMG = BOX_BASE_URL + "/countimg";
    /**
     * 外设数据
     */
    public static final String BOX_WAISHE_INFO = BOX_BASE_URL + "/waisheinfo";
    /**
     * 新列表数据
     */
    public static final String BOX_NEW_LIST = BOX_BASE_URL + "/newlist";
    /**
     * 更新
     */
    public static final String BOX_WAISHE_UP = BOX_BASE_URL + "/waisheup";
    /**
     * 目标文件
     */
    public static final String BOX_TAG_FILES = BOX_BASE_URL + "/tagfiles";
    /**
     * 删除文件
     */
    public static final String BOX_DELETE_FILES = BOX_BASE_URL + "/deletefiles";
    /**
     * 文件数据
     */
    public static final String BOX_FILE_META = BOX_BASE_URL + "/filemeta";
    /**
     * 开始归档
     */
    public static final String BOX_START_GUIDANG = BOX_BASE_URL + "/startguidang";
    /**
     * USB导出
     */
    public static final String BOX_OUT_USB = BOX_BASE_URL + "/outusb";
    /**
     * 手机导出
     */
    public static final String BOX_OUT_PHONE = BOX_BASE_URL + "/outphone";
    /**
     * 文件导出
     */
    public static final String BOX_FILE_OUT = BOX_BASE_URL + "/fileout";
    /**
     * 列表导出
     */
    public static final String BOX_LIST_OUT = BOX_BASE_URL + "/listout";
    /**
     * 归档
     */
    public static final String BOX_GUIDANG = BOX_BASE_URL + "/guidang";
    /**
     * 上传
     */
    public static final String BOX_UPLOAD = BOX_BASE_URL + "/upload";
    /**
     * 开始导出
     */
    public static final String BOX_START_OUT = BOX_BASE_URL + "/startout";
    /**
     * 移除导出
     */
    public static final String BOX_DEL_OUT = BOX_BASE_URL + "/startout";
    /**
     * 目标列表
     */
    public static final String BOX_LIST_TAGGED = BOX_BASE_URL + "/listtagged";
    //wifi api
    /**
     * 信息
     */
    public static final String WLAN_INFO = BOX_BASE_URL + "/info";
    public static final String CLOUD_WLAN_INFO = CLOUD_BASE_URL + "/info";
    /**
     * wifi登录
     */
    public static final String WLAN_SYS_LOGIN = BOX_BASE_URL + "/syslogin";
    /**
     * 设置盒子
     */
    public static final String WLAN_SET_BOX = BOX_BASE_URL + "/setbox";
    //(域名为手机的硬件网关，将最后一位改为1(例如192.168.0.1),后拼
    // 接/info进行是否连接判断，配对盒子连接盒子后设置盒子的登录密码、昵称等)
    /**
     * 识别宝盒
     */
    public static final String MATCH_BLUE_BOX_URL = CLOUD_BASE_URL + "/blueBoxMatch";
    /**
     * 获取蓝光宝盒信息地址
     */
    public static final String GET_BOX_META_URL = CLOUD_BASE_URL + "/boxMeta";
    /**
     * 获取硬盘信息地址
     */
    public static final String GET_DISK_META_URL = CLOUD_BASE_URL + "/diskMeta";
    //初始化流程
    /**
     * 设置WAN
     */
    public static final String SETTING_WAN_URL = CLOUD_BASE_URL + "/updateWAN";
    /**
     * 设置WiFi
     */
    public static final String SETTING_WIFI_URL = CLOUD_BASE_URL + "/updateWifi";
    /**
     * 设置pwd
     */
    public static final String SETTING_PWD_URL = CLOUD_BASE_URL + "/updatePassword";
    //云端用户
    /**
     * 云端用户注册
     */
    public static final String SIGN_UP_URL = CLOUD_BASE_URL + "/signup";
    /**
     * 云端用户登录
     */
    public static final String LOGIN_URL = CLOUD_BASE_URL + "/login";
    /**
     * 发送手机验证码
     */
    public static final String SEND_SMS_URL = CLOUD_BASE_URL + "/sensms";
    /**
     * 重置密码
     */
    public static final String SET_PWD_URL = CLOUD_BASE_URL + "/setpwd";
    /**
     * 检测手机号
     */
    public static final String CHECK_MOBILE_URL = CLOUD_BASE_URL + "/checkmobile";
    //云端设备
    /**
     * 设备列表
     */
    public static final String BLUES_LIST = CLOUD_BASE_URL + "/blues";
    /**
     * 删除设备
     */
    public static final String DELETE_BLUE = CLOUD_BASE_URL + "/bluedel";
    /**
     * 添加设备
     */
    public static final String INSERT_BLUE = CLOUD_BASE_URL + "/blueinsert";
    /**
     * 设置昵称
     */
    public static final String SET_BLUE_NICK = CLOUD_BASE_URL + "/blueset";
}
