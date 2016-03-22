package com.wangcai.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @描述: 字节处理,所有处理若无特殊说明，皆是低位在前，高位在后
 * @author Administrator
 *
 */
public class ByteConvert {
    /**
     * 
     * 描述: 两位数的整数转换为byte     
     * @author: 陆华
     * 2012-12-2 上午10:10:13
     * @param value
     * @return
     * byte
     * @throws DataLengthException 
     *
     */
    public static byte intToBcd(int value) throws DataLengthException {
    	if (value >= 100) {
    		throw new DataLengthException();
    	}
		byte a = ByteConvert.intToByte(value % 10);
		byte b = ByteConvert.intToByte(value / 10);
		return (byte) ((b << 4) | a);
	}
	
    /**
     * 
     * 描述: byte转化为2位数的整数形式
     * @author: 陆华
     * 2012-12-2 上午10:11:02
     * @param b
     * @return
     * int
     *
     */
	public static int bcdToInt(byte b) {
		return (int)(b & 0xf) + (int)(b >> 4 & 0xf) * 10;
	}
	
	/**
	 * @描述: 单个字节转换为整形
	 * @param b
	 * @return
	 */
	public static int byteToInt(byte b) {
		return b & 0xff;
	}
	
	/**
	 * @描述: 255以内的整数转化为byte
	 * @param n
	 * @return
	 */
	public static byte intToByte(int n) {
		return (byte) (n & 0xff);
	}
	
	/**
	 * @描述: byte[] 转化为16进制整形字符串
	 * @param bytes
	 * @return
	 */
	public static String bytesToInt16 (byte[] bytes) {
		String strs = "";
		String a = "";
		for (int i = 0 ; i < bytes.length; i++) {
			a = Integer.toHexString(bytes[i]&0xff).toUpperCase();
			if (a.length() == 1) {
				strs += "0" + a;
			} else {
				strs += a;
			}
		}
		return strs;
	}
	
	/**
	 * 
	 * 描述: 16进制字符串转化为byte[]
	 * @author: 陆华
	 * 2012-12-2 下午5:27:47
	 * @param hexString
	 * @return
	 * byte[]
	 *
	 */
	public static byte[] int16ToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {  
			return null;
		}
		hexString = hexString.toUpperCase();
		if ((hexString.length() & 1) == 1) {
			hexString = 0 + hexString;
		}
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;

	}
	/**
	 * @param c
	 * @return
	 */
	private static byte charToByte(char c) {  
		return (byte) "0123456789ABCDEF".indexOf(c);  
	}
	
	
	/**
	 * @描述: 10进制数转化为16进制字符串
	 * @param number
	 * @return
	 */
	public static String int10ToInt16(int number) {
		  ArrayList<Integer> newnum = new ArrayList<Integer>();
		  int j = number % 16;
		  int i = number / 16;
		  newnum.add(j);
		  while (i != 0) {
		   j = i % 16;
		   i = i / 16;
		   newnum.add(j);
		  }
		  int length = newnum.size();
		  String[] out = new String[length];
		  for (int k = 0; k < length; k++) {
		   switch (newnum.get(length-k-1)) {
		   case 10:out[k]="A" ;break;
		   case 11:out[k]="B" ;break;
		   case 12:out[k]="C" ;break;
		   case 13:out[k]="D" ;break;
		   case 14:out[k]="E" ;break;
		   case 15:out[k]="F" ;break;
		   default: out[k]=newnum.get(length-k-1).toString();
		   }   
		  }
		  String str = "";
		  for (int l = 0; l < out.length; l++) {
			  str += out[l];
		  }
		  return str;
	}
	
	/**
	 * @描述: 2进制字符串转化为byte[]
	 * @param binary_str
	 * @return
	 */
	public static byte[] binaryStringToBytes(String binary_str) {
		byte[] bytes = new byte[Math.round(binary_str.length() / 8)];
		String temp = null;
		int index = 0;
		while (!"".equals(binary_str)) {
			if (binary_str.length() <= 8) {
				temp = binary_str;
				binary_str = "";
			} else {
				temp = binary_str.substring(0, 8);
				binary_str = binary_str.substring(8, binary_str.length());
			}
			try {
				bytes[index] = binaryStringToByte(temp);
				index++;
			} catch (DataLengthException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}
	
	/**
	 * @throws DataLengthException 
	 * 
	 * 描述: 把一个字节的2进制字符串 转化为 byte
	 * @author: 陆华
	 * 2012-12-2 下午1:01:40
	 * @param str
	 * @return
	 * byte
	 * @throws  
	 *
	 */
	public static byte binaryStringToByte(String binary_str) throws DataLengthException {
		if (binary_str.length() > 8) {
			throw new DataLengthException();
		}
		
		byte b = 0;
		for (int i = 0; i < binary_str.length(); i++) {
			b = (byte) (b << 1);
			if (binary_str.charAt(i) == '1') {
				b = (byte) (b | 0x1);
			}
		}
		return b;
	}
	
	/**
	 * 把byte[]转化成2进制字符串
	 * 
	 * @param bArr
	 * @return
	 */
	public static String bytesToBinaryString(byte[] bArr) {
		String result = "";
		for (byte b : bArr) {
			result += byteToBinaryString(b);
		}
		return result;
	}

	/**
	 * 把byte转化成2进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToBinaryString(byte b) {
		String result = "";
		byte a = b;
		
		for (int i = 0; i < 8; i++) {
			byte c = a;
			a = (byte) (a >> 1);// 每移一位如同将10进制数除以2并去掉余数。
			a = (byte) (a << 1);
			if (a == c) {
				result = "0" + result;
			} else {
				result = "1" + result;
			}
			a = (byte) (a >> 1);
		}
		return result;
	}
	
	/**
	 * @描述: 8个字节的无符号整 型转化为byte[]
	 * @param n
	 * @return
	 */
	public static byte[] longToBytes(long n) {
        byte[] b = new byte[8];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8  & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        b[4] = (byte) (n >> 32 & 0xff);
        b[5] = (byte) (n >> 40 & 0xff);
        b[6] = (byte) (n >> 48 & 0xff);
        b[7] = (byte) (n >> 56 & 0xff);
        return b;
    }
	
	
	/**
	 * @描述: 4个字节的无符号int 型转化为byte[]
	 * @param n
	 * @return
	 */
    public static byte[] intToBytes(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }
    
    /**
     * @描述: 占用2个字节的byte[] 转化为无符号整型
     * @param b
     * @return
     */
    public static int bytesToInt(byte b[]) {
    	if (b.length == 2) {
    		byte[] temp = new byte[4];
    		System.arraycopy(b, 0, temp, 0, 2);
    		b = temp;
    	}
        return    b[0] & 0xff 
               | (b[1] & 0xff) << 8 
               | (b[2] & 0xff) << 16
               | (b[3] & 0xff) << 24;
    }
    
    /**
     * 
     * 描述: byte[]转化为占用2个字节的无符号整形
     * @author: 陆华
     * 2012-12-3 上午10:34:26
     * @param b  两个字节
     * @return
     * int
     *
     */
    public static int bytesToInt2(byte[] b) {
    	return  bytesToInt(b);
    }
    
    /**
     * @描述: 有符号的,2个字节的整形转换
     * @return
     */
    public static short bytesToInt2WithSign(byte[] b) {
    	return   (short)(b[0] & 0xff 
				| (b[1] & 0xff) << 8);
    }
    
    /**
     * @描述:  带符号的 2个字节整形转化为byte[]
     * @return
     */
    public static byte[] int2ToBytesWithSign(short n) {
    	byte[] b = new byte[2];
    	b[0] = (byte) (n & 0xff);
    	b[1] = (byte) ((n >> 8) & 0xff);
    	return b;
    }
    
    /**
     * 
     * 描述: 2个字节的无符号整形转化为byte[]
     * @author: 陆华
     * 2012-12-3 上午10:36:32
     * @param n
     * @return  2个字节
     * byte[]
     *
     */
    public static byte[] int2ToBytes(int n) {
    	byte[] b = new byte[2];
    	b[0] = (byte) (n & 0xff);
    	b[1] = (byte) ((n >> 8) & 0xff);
    	return b;
    }
    
    /**
     * 
     * 描述: 4个字节的无符号整形转化为byte[]
     * @author: 陆华
     * 2012-12-3 上午10:36:54
     * @param b  4个字节
     * @return
     * long
     *
     */
    public static long bytesToLong4(byte[] b) {
    	return  bytesToLong(b);
    }
    
    /**
     * @描述: byte[] 转化为无符号整形
     * @param array
     * @return
     */
	public static long bytesToLong(byte[] array)
    {
		if (array.length == 4) {
			byte[] temp = new byte[8];
			System.arraycopy(array, 0, temp, 0, 4);
			array = temp;
		}
        return ((((long) array[ 7] & 0xff) << 56)
              | (((long) array[ 6] & 0xff) << 48)
              | (((long) array[ 5] & 0xff) << 40)
              | (((long) array[ 4] & 0xff) << 32)
              | (((long) array[ 3] & 0xff) << 24)
              | (((long) array[ 2] & 0xff) << 16)
              | (((long) array[ 1] & 0xff) << 8) 
              | (((long) array[ 0] & 0xff) << 0));        
    }
    
    /**
     * 
     * 描述: 4个字节的整形转化为byte[]
     * @author: 陆华
     * 2012-12-3 上午10:37:58
     * @param n
     * @return
     * byte[]
     *
     */
    public static byte[] long4ToBytes(long n) {
    	 byte[] b = new byte[4];
         b[0] = (byte) (n & 0xff);
         b[1] = (byte) (n >> 8 & 0xff);
         b[2] = (byte) (n >> 16 & 0xff);
         b[3] = (byte) (n >> 24 & 0xff);
         return b;
    }
    
    /**
     * @描述: 2个字节带符号整形转化为byte[]
     * @param n
     * @return
     */
    public static byte[] shortToBytes(short n) {
        byte[] b = new byte[2];
        b[0] = (byte) ( n & 0xff);
        b[1] = (byte) ((n >> 8) & 0xff);
        return b;
    }
    
    /**
     * @描述: 2个字节byte[]整形转化为带符号整形
     * @param b
     * @return
     */
    public static short bytesToShort(byte[] b){
        return (short)( b[0] & 0xff
                      |(b[1] & 0xff) << 8 ); 
    }  
    
    /**
     * @描述: 字符串转化为ascii码 byte[]
     * @param str
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static byte[] StringToAscii(String str) throws UnsupportedEncodingException {
    	return str.getBytes("US-ASCII");
    }
    
    /**
     * @描述: asscii码byte[] 转化为字符串
     * @param b
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String asciiToString(byte[] b) throws UnsupportedEncodingException {
    	return new String(b, "US-ASCII");
    }
    
    /**
	 * 
	 * 描述: 通过1970年1月1日到现在的毫秒数， 以BCD码返回现在的时间 ，格式为 yy-MM-dd HH:mm:ss
	 * @author: 陆华
	 * 2012-8-6 上午10:41:44
	 * @param time 1970年1月1日到现在的毫秒数
	 * @return BCD码返回现在的时间 ，格式顺序为 yy MM dd HH mm ss
	 * byte[]
	 *
	 */
	public static byte[] dateToBcd(Long time) {
		Date date = new Date(time);
		String str_date = DateUtil.format(date, "yyyy-MM-dd-HH-mm-ss");
		String[] dates = str_date.split("-");
		byte[] datas = new byte[6];
		
		
		datas[0] = ByteConvert.intToByte(Integer.parseInt(dates[0]) - 2000);
		datas[1] = ByteConvert.intToByte(Integer.parseInt(dates[1]));
		datas[2] = ByteConvert.intToByte(Integer.parseInt(dates[2]));
		datas[3] = ByteConvert.intToByte(Integer.parseInt(dates[3]));
		datas[4] = ByteConvert.intToByte(Integer.parseInt(dates[4]));
		datas[5] = ByteConvert.intToByte(Integer.parseInt(dates[5]));
		
		return datas;
	}
	
	/**
	 * @描述: bcd码转化为日期
	 * @param datas
	 * @return
	 */
	public static Long bcdToDate(byte[] datas) {
		byte b_year = datas[0];
		byte b_month = datas[1];
		byte b_day = datas[2];
		byte b_hour = datas[3];
		byte b_minute = datas[4];
		byte b_second = datas[5];
		
		int year = ByteConvert.byteToInt(b_year);
		int month = ByteConvert.byteToInt(b_month);
		int day = ByteConvert.byteToInt(b_day);
		int hour = ByteConvert.byteToInt(b_hour);
		int minute = ByteConvert.byteToInt(b_minute);
		int second = ByteConvert.byteToInt(b_second);
		
		return DateUtil.parse((year + 2000) + "-" + month + "-" + day + "-" + hour + "-" + minute + "-" + second, 
				"yyyy-MM-dd-HH-mm-ss").getTime();
	}
	
	/**
	 * @描述: bcd码转化为字符串
	 * @param purseno1availabletime
	 * @return
	 */
	public static String bcdToString(byte[] purseno1availabletime) {
		String str = "";
		int temp = 0;
		for (int i = 0; i < purseno1availabletime.length; i++) {
			temp = bcdToInt(purseno1availabletime[i]);
			if (temp < 10) {
				str += "0" + temp;
			} else {
				str += temp;
			}
		}
		return str;
	}
	
	/**
	 * 
	 * @描述: 转换成bcd码
	 * @作者: 陆华
	 * @时间: 2012-11-30 下午3:50:00
	 * @param str  必须传入偶数长度的字符串
	 * @return
	 * @throws NumberFormatException
	 * @throws DataLengthException
	 * byte[]
	 */
	public static byte[] StringToBcd(String str) throws NumberFormatException, DataLengthException {
		int index = 0;
		byte[] temp = new byte[str.length() / 2];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = intToBcd(Integer.parseInt(str.substring(index, index+2)));
			index = index+2;
		}
		return temp;
	}
	
	public static void main(String[] args) throws NumberFormatException, DataLengthException {
		System.out.println(ByteConvert.bcdToString(ByteConvert.StringToBcd("0700")));
		System.out.println(ByteConvert.bytesToInt16(ByteConvert.StringToBcd("0700")));
	}
}

