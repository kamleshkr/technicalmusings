package com.technicalmusings.hazelcast.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Kamlesh Kumar (<a href="https://kamlesh-kumar.com">Technical Musings and Beyond</a>)
 */
@Service
public class NameItService {

    private final HazelcastInstance hzInstance;

    public NameItService(HazelcastInstance hzInstance) {
        this.hzInstance = hzInstance;
    }

    public void triggerExec() {
        IExecutorService executorService = hzInstance.getExecutorService( "executorService" );

        Callable<String> callableTask = () -> {
            // Do some work
            return "Task's execution";
        };

        Runnable runnableTask = (Runnable & Serializable)() -> {
            System.out.println("Executing....");
        };

        executorService.execute(runnableTask);




    }
}
