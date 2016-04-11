package com.wangcai.common.util;

/**
 * 描述: 数据长度异常
 * 作者: 陆华
 *
 * 时间: 2012 2012-8-2 下午5:00:40
 */
public class DataLengthException extends Exception {
    private static final long serialVersionUID = -4388463824196588481L;

    public DataLengthException() {
        super("数据长度异常");
    }

    public DataLengthException(String msg) {
        super(msg);
    }
}