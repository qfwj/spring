 * ����Ϊʲôscheduler���治��ʹ��FutureTask��Ϊ��ʱ��task
 *
 * ԭ��futuretaskÿ��ִ����֮��Ὣ����״̬ת��Ϊ NORMAL�������ڵ���futureTask��run����ʱ�޷�ִ��callable.call����
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