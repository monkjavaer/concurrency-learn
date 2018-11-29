# GET-Thread-

并发编程demo

**多线程的优点**
1资源利用率更好
2程序设计在某些情况下更简单
3程序响应更快

**幂等：**
在编程中，一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同。
同一个请求，使用相同参数重复执行，发送一次和发送N次效果是一样的。

 一.synchronized
 “非线程安全"是指发生在多个线程对同一个对象中的实例变量并发访问时，产生的”脏读“现象。
 synchronized同步处理可解决这一问题。
 非线程安全问题存在于实例变量中，不存在方法内部的私有变量。
 1、synchronized修饰方法的两种情况：
 (1).当A线程调用某个对象的synchronized方法，先持有某个对象的锁；这时B线程需要等待A线程执行完毕后释放这个对象锁才可调用这个对象的synchronized方法，即同步。synchronized是一个独占锁，每个锁请求之间是互斥的
 (2).当A线程调用某个对象的synchronized方法时，B线程调用这个对象的其他非synchronized方法，不需要等待。
 2、synchronized重入
 可重入锁：即某个线程可以获得一个它自己已持有的锁。在继承关系中子类可以通过可重入锁调用父类的同步方法，提升了加锁行为的封装性。如果没有可重入锁就会产生死锁。
 下面是维基百科对死锁的定义：
 死锁（英语：Deadlock），又译为死结，计算机科学名词。当两个以上的运算单元，双方都在等待对方停止运行，以获取系统资源，但是没有一方提前退出时，就称为死锁。
 死锁的四个条件是：
 禁止抢占 no preemption - 系统资源不能被强制从一个进程中退出
 持有和等待 hold and wait - 一个进程可以在等待时持有系统资源
 互斥 mutual exclusion - 只有一个进程能持有一个资源
 循环等待 circular waiting - 一系列进程互相持有其他进程所需要的资源
 死锁只有在这四个条件同时满足时出现。预防死锁就是至少破坏这四个条件其中一项，即破坏“禁止抢占”、破坏“持有等待”、破坏“资源互斥”和破坏“循环等待”。
 下面这张图片描述了死锁情况：
 
 死锁检查方法：
 1、jps
 2、jstack -l 端口
 
 
 
