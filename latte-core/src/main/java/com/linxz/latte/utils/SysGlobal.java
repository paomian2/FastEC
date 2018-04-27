package com.linxz.latte.utils;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年02月11日10:38  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class SysGlobal {
    private final static HashMap<Integer,ExecutorService> hsmpExecutorService = new HashMap<Integer,ExecutorService>();
    public static void execute(Runnable runnable, int priority) {
        synchronized(hsmpExecutorService) {
            ExecutorService executorService = hsmpExecutorService.get(priority);
            if(executorService == null) {
                executorService = Executors.newCachedThreadPool(new ThreadFactory() {
                    private int priority;
                    public ThreadFactory setPriority(int priority) {
                        this.priority = priority;
                        return this;
                    }
                    @Override
                    public Thread newThread(Runnable runnable) {
                        Thread thread = new Thread(runnable);
                        thread.setPriority(priority);
                        return thread;
                    }
                }.setPriority(priority));
                hsmpExecutorService.put(priority, executorService);
            }
            executorService.execute(runnable);
        }
    }
    public static void execute(Runnable runnable) {
        execute(runnable,Thread.NORM_PRIORITY);
    }
}
