 * 测试为什么scheduler里面不能使用FutureTask作为定时的task
 *
 * 原因：futuretask每次执行完之后会将自身状态转换为 NORMAL；导致在调用futureTask的run方法时无法执行callable.call方法
 *
 * Possible state transitions:
 * NEW -> COMPLETING -> NORMAL
 * NEW -> COMPLETING -> EXCEPTIONAL
 * NEW -> CANCELLED
 * NEW -> INTERRUPTING -> INTERRUPTED
 * private volatile int state;
 *private static final int NEW          = 0;
 *private static final int COMPLETING   = 1;
 *private static final int NORMAL       = 2;
 *private static final int EXCEPTIONAL  = 3;
 *private static final int CANCELLED    = 4;
 *private static final int INTERRUPTING = 5;
 *private static final int INTERRUPTED  = 6;
 *