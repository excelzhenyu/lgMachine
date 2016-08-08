package com.k2data.platform.common.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理类
 * 
 * @author lidong
 * @since 2016-5-23
 */
public class ExecutorManager {

	private static ExecutorService executor = new ThreadPoolExecutor(10, 30,
								            30L, TimeUnit.SECONDS,
								            new LinkedBlockingQueue<Runnable>());
	
	public static void submit(Runnable task) {
		executor.submit(task);
	}
	
	public static <T> Future<T> execute(Callable<T> task) {
		return executor.submit(task);
	}
	
}
