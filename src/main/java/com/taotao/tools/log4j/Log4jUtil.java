package com.taotao.tools.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jUtil {
	public static Logger commLogger = LoggerFactory.getLogger(Log4jUtil.class.getClass());

	/**
	 * info日志
	 * 
	 * @param logger
	 *            log4j的对象
	 * @param msgs
	 *            待输出的日志
	 */
	public static void i(Logger logger, Object... msgs)
	{
		if (null == logger)
			logger = commLogger;
		String msg = getMsgStr(msgs);
		logger.info(msg);
	}

	/**
	 * info日志
	 * 
	 * @param logger
	 *            log4j的对象
	 * @param e
	 *            异常
	 * @param msgs
	 *            待输出的日志
	 */
	public static void i(Logger logger, Throwable e, Object... msgs)
	{
		if (null == logger)
			logger = commLogger;
		String msg = getMsgStr(msgs);
		logger.info(msg, e);
	}

	/**
	 * debug日志
	 * 
	 * @param logger
	 *            log4j的对象
	 * @param msgs
	 *            待输出的日志
	 */
	public static void d(Logger logger, Object... msgs)
	{
		if (null == logger)
			logger = commLogger;
		String msg = getMsgStr(msgs);
		logger.debug(msg);
	}

	/**
	 * debug日志
	 * 
	 * @param logger
	 *            log4j的对象
	 * @param e
	 *            异常
	 * @param msgs
	 *            待输出的日志
	 */
	public static void d(Logger logger, Throwable e, Object... msgs)
	{
		if (null == logger)
			logger = commLogger;
		String msg = getMsgStr(msgs);
		logger.debug(msg, e);
	}

	/**
	 * error日志
	 * 
	 * @param logger
	 *            log4j的对象
	 * @param msgs
	 *            待输出的日志
	 */
	public static void e(Logger logger, Object... msgs)
	{
		if (null == logger)
			logger = commLogger;
		String msg = getMsgStr(msgs);
		logger.error(msg);
	}

	/**
	 * error日志
	 * 
	 * @param logger
	 *            log4j的对象
	 * @param e
	 *            异常
	 * @param msgs
	 *            待输出的日志
	 */
	public static void e(Logger logger, Throwable e, Object... msgs)
	{
		if (null == logger)
			logger = commLogger;
		String msg = getMsgStr(msgs);
		logger.error(msg, e);
	}

	/**
	 * info日志
	 * 
	 * @param logger
	 *            log4j的对象
	 * @param msgs
	 *            待输出的日志
	 */
	public static void i(Logger logger, String... msgs)
	{
		if (null == logger)
			logger = commLogger;
		String msg = getMsgStr(msgs);
		logger.info(msg);
	}

	/**
	 * info日志
	 * 
	 * @param logger
	 *            log4j的对象
	 * @param e
	 *            异常
	 * @param msgs
	 *            待输出的日志
	 */
	public static void i(Logger logger, Throwable e, String... msgs)
	{
		if (null == logger)
			logger = commLogger;
		String msg = getMsgStr(msgs);
		logger.info(msg, e);
	}

	/**
	 * debug日志
	 * 
	 * @param logger
	 *            log4j的对象
	 * @param msgs
	 *            待输出的日志
	 */
	public static void d(Logger logger, String... msgs)
	{
		if (null == logger)
			logger = commLogger;
		String msg = getMsgStr(msgs);
		logger.debug(msg);
	}

	/**
	 * debug日志
	 * 
	 * @param logger
	 *            log4j的对象
	 * @param e
	 *            异常
	 * @param msgs
	 *            待输出的日志
	 */
	public static void d(Logger logger, Throwable e, String... msgs)
	{
		if (null == logger)
			logger = commLogger;
		String msg = getMsgStr(msgs);
		logger.debug(msg, e);
	}

	/**
	 * error日志
	 * 
	 * @param logger
	 *            log4j的对象
	 * @param msgs
	 *            待输出的日志
	 */
	public static void e(Logger logger, String... msgs)
	{
		if (null == logger)
			logger = commLogger;
		String msg = getMsgStr(msgs);
		logger.error(msg);
	}

	/**
	 * error日志
	 * 
	 * @param logger
	 *            log4j的对象
	 * @param e
	 *            异常
	 * @param msgs
	 *            待输出的日志
	 */
	public static void e(Logger logger, Throwable e, String... msgs)
	{
		if (null == logger)
			logger = commLogger;
		String msg = getMsgStr(msgs);
		logger.error(msg, e);
	}

	/**
	 * 对象转文本
	 * 
	 * @param obj
	 * @return
	 */
	public static String toStr(Object obj)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(obj);
		return sb.toString();
	}

	/**
	 * 通过StringBuilder组装msg信息
	 * 
	 * @param msgs
	 *            日志数组
	 * @return
	 */
	private static String getMsgStr(String... msgs)
	{
		StringBuilder sb = new StringBuilder();
		StackTraceElement className = getMethodName();
		sb.append("[ ").append(className).append(" ] - ");
		for (int i = 0; i < msgs.length; i++)
		{
			if (null != msgs[i])
			{
				sb.append(msgs[i]);
				sb.append(" ");
			}
		}

		return sb.toString();
	}

	/**
	 * 通过StringBuilder组装msg信息
	 * 
	 * @param msgs
	 *            日志数组
	 * @return
	 */
	private static String getMsgStr(Object... msgs)
	{
		StringBuilder sb = new StringBuilder();
		StackTraceElement className = getMethodName();
		sb.append("[ ").append(className).append(" ] - ");
		for (int i = 0; i < msgs.length; i++)
		{
			if (null != msgs[i])
			{
				sb.append(msgs[i]);
				sb.append(" ");
			}
		}

		return sb.toString();
	}

	/**
	 * 获取调用者的文件路径及文件名
	 * 
	 * @return
	 */
	private static StackTraceElement getMethodName()
	{
		StackTraceElement[] temp = Thread.currentThread().getStackTrace();
		if (temp.length >= 5)
		{
			StackTraceElement st = (StackTraceElement) temp[4];
			if (null != st)
				return st;
		}

		return null;
	}
}
	
