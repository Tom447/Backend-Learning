package com.itheima;

/**
 * 死锁的案例
 * 死锁的产生: 线程1拿着objA的锁去获取objB的锁, 线程2拿着objB的锁去获取objA的锁, 两者互不相让就产生了死锁
 */
public class DeadLock{
    public static void main(String[] args) {
        DeadLock.DieLock d1 = new DeadLock.DieLock(true);
        DeadLock.DieLock d2 = new DeadLock.DieLock(false);

        d1.start();//true
        d2.start();//false
    }

    public static class DieLock extends Thread{
        private boolean flag;

        public DieLock(boolean flag){
            this.flag = flag;
        }

        public void run() {
            if(flag){
                while(true){
                    synchronized (Mylock.objA) {
                        System.out.println("if objA");
                        synchronized (Mylock.objB) {
                            System.out.println("if objB");
                        }
                    }
                }
            }else{
                while(true){
                    synchronized (Mylock.objB) {
                        System.out.println("else objB");
                        synchronized (Mylock.objA) {
                            System.out.println("else objA");
                        }
                    }
                }
            }
        }
    }

    public static class Mylock {
        public static final Object objA = new Object();
        public static final Object objB = new Object();
    }
}
