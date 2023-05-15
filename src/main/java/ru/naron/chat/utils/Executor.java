package ru.naron.chat.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public interface Executor {

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    static Future<?> async(Runnable runnable){
        return executorService.submit(runnable);
    }
}
