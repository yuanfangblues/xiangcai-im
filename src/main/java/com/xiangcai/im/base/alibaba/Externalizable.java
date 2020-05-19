package com.xiangcai.im.base.alibaba;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 1、 用两种方式实现java对象序列化反序列化。
 * 方法1：实现序列化接口Serializable
 * 方法2：实现接口Externlizable
 * <p>
 * 2、导游早上十点带着3位游客来到一景区，一共需要游览三个景点，分别为A、B、C, D为出口（终点）。
 * 现在所有人从A出发自有行，但是必须所有人上午11点在B景点集合完成后，再出发到C，
 * 最后13点在D出口处集合统一大巴去其他景区。
 * 请使用java多线程实现以上场景。
 * 思路：设3位游
 * <p>
 * <p>
 * <p>
 * 3、 输入一棵二元查找树，将该二元查找树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只调整引用（指针）的指向,只需要写出转换算法即可。
 *
 * @author :元放
 * @date :2020-04-28 22:55
 **/
@Slf4j
public class Externalizable {


    public static final String TMP_USER_OBJ = "/data/tmp/user.obj";

    public static void main(String[] args) {
        serialization();
        deserialization();
    }

    private static void deserialization()  {
        ObjectInputStream ins = null;
        try {
            try {
                ins = new ObjectInputStream(new FileInputStream(TMP_USER_OBJ));
            } catch (IOException e) {
                log.warn("deserialization error, :{}", e);
            }

            UserInfo2 userInfo = null;
            try {
                userInfo = (UserInfo2) ins.readObject();
            } catch (IOException e) {
                log.warn("io read error, :{}", e);
            } catch (ClassNotFoundException e) {
                log.warn("cast obj error, :{}", e);
            }
            log.info("read user obj :{}", userInfo.toString());
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    log.warn("close io stream error, :{}", e);
                }
            }
        }
    }

    private static void serialization() {
        UserInfo2 userInfo = new UserInfo2();
        userInfo.setUserId(0);
        userInfo.setUserName("");

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(TMP_USER_OBJ));
            out.writeObject(userInfo);
            log.info("write /data/tmp/user.obj success!");
        } catch (IOException e) {
            log.warn("serialization error, :{}", e);
        }finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.warn("close io stream error, :{}", e);
                }
            }
        }
    }
}
