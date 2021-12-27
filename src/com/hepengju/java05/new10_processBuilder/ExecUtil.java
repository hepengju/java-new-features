//package com.hepengju.java05.new10_processBuilder;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * 执行系统命令
// *
// *
// * <br> 注意死锁问题
// * <br> 解决: 只要主进程在waitfor之前，能不断处理缓冲区中的数据就可以
// *
// * @see <a href="https://blog.csdn.net/seapeak007/article/details/69668600">调用Process.waitfor导致的进程挂起</a>
// *
// * @author he_pe
// *
// */
//public class ExecUtil {
//
//	private static Logger logger = LoggerFactory.getLogger(ExecUtil.class);
//
//	public static void execCommand(String command) {
//		logger.info("begin exec os command: " + command);
//		Process process;
//		try {
//			process = Runtime.getRuntime().exec(command);
//
//			//开启新线程处理正常输出
//			new Thread(() ->  {
//				try(InputStream inputStream = process.getInputStream();
//				BufferedReader bufr = new BufferedReader(new InputStreamReader(inputStream));){
//				String out = null;
//					while ((out = bufr.readLine()) != null) {
//						logger.info(out);
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}).start();
//
//			//开启新线程处理错误输出
//			new Thread(() ->  {
//				try(InputStream inputStream = process.getErrorStream();
//						BufferedReader bufr = new BufferedReader(new InputStreamReader(inputStream));){
//					String out = null;
//					while ((out = bufr.readLine()) != null) {
//						logger.info(out);
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}).start();
//
//
//			// 阻塞当前进程,直到命令结束
//			process.waitFor();
//
//
//			// 不会阻塞进程,但是调用时如果没有完成会报错
//			if (process.exitValue() != 0) {
//				logger.error("exec os command failure: " + command);
//			} else {
//				logger.info("exec os command success: " + command);
//			}
//		} catch (IOException | InterruptedException e) {
//			logger.error("exec os command exception: " + command);
//			e.printStackTrace();
//		}
//	}
//
//
//
//}
