package com.spike.enums;

/**
 * 状态常量枚举
 *
 * @author wangxu
 *         blog：http://www.cnblogs.com/wxisme/
 *         github：https://github.com/wxisme
 */
public enum StateEnum {
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPEAT(-1, "重复秒杀"),
    INNER_ERROR(-2, "秒杀出错"),
    URL_DATA_OVERWRITE(-3, "秒杀地址篡改");

    private int state;

    private String stateInfo;

    StateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }


    public static StateEnum stateOf(int state) {
        for (StateEnum se : values()) {
            if (se.getState() == state) {
                return se;
            }
        }
        return null;
    }
}
