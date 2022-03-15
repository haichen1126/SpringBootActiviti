package com.hc.config;

import java.io.Serializable;

public class RedisKeyUUID  implements Serializable {

    /**
     * 用户信息缓存24小时，redis过期时间为秒
     */
   public static final String MP_KEY_USER = "MP:KEY:USER#" + 60 * 60;

   //不设置默认就是1小时
   public static final String MP_KEY_List = "MP:KEY:List";

}
