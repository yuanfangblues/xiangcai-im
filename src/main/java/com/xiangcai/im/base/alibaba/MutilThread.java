package com.xiangcai.im.base.alibaba;

import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 2、导游早上十点带着3位游客来到一景区，一共需要游览三个景点，分别为A、B、C, D为出口（终点）。
 * 现在所有人从A出发自有行，但是必须所有人上午11点在B景点集合完成后，再出发到C，
 * 最后13点在D出口处集合统一大巴去其他景区。
 * 请使用java多线程实现以上场景。
 * <p>
 * <p>
 * 思路：设3个人为Thread1, Thread2, Thread3线程, 景点为 AView, BView, CView,
 * D为 ViewExport
 *
 * @author :元放
 * @date :2020-04-28 23:12
 **/
@Slf4j
public class MutilThread {

    public static void main(String[] args) {
        List<Job> jobList = new LinkedList<>();
        jobList.add(new AView());
        jobList.add(new BView());
        jobList.add(new CView());
        jobList.add(new DView());

        new Thread(new Thread1(jobList)).start();
        new Thread(new Thread2(jobList)).start();
        new Thread(new Thread3(jobList)).start();
    }

}


interface Job{
  void  handle();
}


@Slf4j
class AView implements Job{
    @Override
    public void handle() {
        log.info("current thread Arrivals A:{} ", Thread.currentThread().getName());
    }
}

@Slf4j
class BView implements Job {
    CyclicBarrier BTask = new CyclicBarrier(3);
    @Override
    public void handle() {
        log.info("current thread Arrivals B:{} ", Thread.currentThread().getName());
        try {
            BTask.await();
        } catch (Exception e) {
            log.error("BTask await error");
        }
    }
}

@Slf4j
class CView implements Job{
    @Override
    public void handle() {
        log.info("current thread Arrivals C:{} ", Thread.currentThread().getName());
    }
}

@Slf4j
class DView implements Job{
    CyclicBarrier DTask = new CyclicBarrier(3);

    @Override
    public void handle() {
        log.info("current thread Arrivals D:{} ", Thread.currentThread().getName());
        try {
            DTask.await();
        } catch (Exception e) {
            log.error("BTask await error");
        }
    }
}

class Thread1 implements Runnable {

    private List<Job> jobList;

    public Thread1(List<Job> jobList) {
        this.jobList = jobList;
    }

    @Override
    public void run() {
        jobList.forEach(Job::handle);
    }
}

class Thread2 implements Runnable {

    private List<Job> jobList;
    public Thread2(List<Job> jobList) {
        this.jobList = jobList;
    }

    @Override
    public void run() {
        jobList.forEach(Job::handle);
    }
}

class Thread3 implements Runnable {

    private List<Job> jobList;

    public Thread3(List<Job> jobList) {
        this.jobList = jobList;
    }
    @Override
    public void run() {
        jobList.forEach(Job::handle);
    }
}
