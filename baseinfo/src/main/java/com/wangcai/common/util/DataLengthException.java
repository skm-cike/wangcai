package com.wangcai.common.util;

/**
 * ����: ���ݳ����쳣
 * ����: ½��
 *
 * ʱ��: 2012 2012-8-2 ����5:00:40
 */
public class DataLengthException extends Exception {
	private static final long serialVersionUID = -4388463824196588481L;

	public DataLengthException() {
		super("���ݳ����쳣");
	}
	
	public DataLengthException(String msg) {
		super(msg);
	}
}