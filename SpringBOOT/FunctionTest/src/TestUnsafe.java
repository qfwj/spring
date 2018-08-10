/**
 * Copyright(c) 2013-2018 by Puhuifinance Inc.
 * All Rights Reserved
 */

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName: TestUnsafe
 * @Description:
 * @author: zhbo
 * @date: 2018/6/28 上午10:21
 * @Copyright: 2017 . All rights reserved.
 */
public class TestUnsafe {


    static class Target{
        public int value = 10;
    }

    public static void main(String[] args)  throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        //通过反射获得Unsafe实例，仅BootstrapClassLoader加载的类
        //（$JAVA_HOME/lib目录下jar包包含的类，如java.util.concurrent.atomic.AtomicInteger）
        //才能通过Unsafe.getUnsafe静态方法获取
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);

        //获得Target实例域value
        Field valueField = Target.class.getDeclaredField("value");
        //实例化Target
        Target t = new Target();
        System.out.println("原始value值:" + valueField.get(t));

        //获得实例域在class文件里的偏移量
        final long valueOffset = unsafe.objectFieldOffset(valueField);

        //第一次swap
        System.out.println("第一次swap(10,20)函数返回值:" + unsafe.compareAndSwapInt(t, valueOffset, 10, 20));
        System.out.println("第一次swap(10,20)后value值:" + valueField.get(t));

        //第二次swap
        System.out.println("第一次swap(10,20)函数返回值:" + unsafe.compareAndSwapInt(t, valueOffset, 20, 30));
        System.out.println("第一次swap(10,20)后value值:" + valueField.get(t));




    }


    public static Unsafe getUnsafe() {
        return Unsafe.getUnsafe();
    }
}