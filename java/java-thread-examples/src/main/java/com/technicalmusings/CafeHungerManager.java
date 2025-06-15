package com.technicalmusings;

import java.util.concurrent.Semaphore;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class TheTechnicalMusingsCafe {
    public static final Logger logger = LoggerFactory.getLogger(TheTechnicalMusingsCafe.class);

    private final Semaphore tables;
    private final LinkedBlockingQueue<String> waitingQueue;
    private final long diningTime;

    public TheTechnicalMusingsCafe(int tableCount, int diningTime) {
        this.tables = new Semaphore(tableCount, true);
        this.waitingQueue = new LinkedBlockingQueue<>();
        this.diningTime = diningTime;
    }

    public void requestTable(String customerName) {
        int customerPositionInQueue = waitingQueue.size();
        int availableTables = tables.availablePermits();
        long waitTime = (availableTables > 0)
                ? (customerPositionInQueue / availableTables) * diningTime
                : customerPositionInQueue * diningTime;
        logger.info("⚡ {} enters, evaluating their hunger. Estimated wait:: {} seconds.", customerName, waitTime);

        waitingQueue.offer(customerName);
        new Thread(this::dine).start();
    }

    public void dine() {
        String customerName = "unknown";
        try {
            customerName = waitingQueue.take();
            tables.acquire();
            logger.info("✅ {} got a table and is enjoying their coffee☕ with code\uD83D\uDC68\u200D\uD83D\uDCBB.", customerName);
            Thread.sleep(diningTime * 1000);
        } catch (InterruptedException e) {
            logger.error("🚨 {} faced unexpected hunger-induced interruption!", Thread.currentThread().getName(), e);
        } finally {
            logger.info("\uD83D\uDEAA {} finished dining \uD83D\uDE0A and left, freeing a table.", customerName);
            tables.release();
        }
    }
}

public class CafeHungerManager {

    public static final Logger logger = LoggerFactory.getLogger(CafeHungerManager.class);
    
    public static void main(String[] args) {
        logger.info("🍽️ The Technical Musings Cafe's CafeHungerManager Booting Up...");
        logger.info("🚀 Preparing tables for incoming hungry customers...");

        int totalTables = 5, diningTime = 10, noOfCustomersArrived = 12;

        TheTechnicalMusingsCafe cafe = new TheTechnicalMusingsCafe(totalTables, diningTime);

        IntStream.rangeClosed(1, noOfCustomersArrived)
                .mapToObj(i -> "Customer-" + i)
                .forEach(cafe::requestTable);

    }
}