package com.atguigu.container_notsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContainerNotSafe {

    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        for (int i = 0; i <30 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
    /*
      1,故障现象
    *   java.util.ConcurrentModificationException
    * 2 导致原因
    *
    * 3 解决方案
    *    3.1 new Vector<>();
    *    3.2 Collections.synchronizedList(new ArrayList<>());
    *    3.3 new CopyOnWriteArrayList<>();
    *    写时复制CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器
    *    Object[]添加，而是现将当前容器Object[]控模型Cppy，复制出一个新的容器Object[] newElements，
    *    然后新的容器Object[] newElements里添加元素，添加完元素之后，再将原容器的引用指向新的容器
    *    setArray(newElements);这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，
    *    因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
    * 4 优化建议
    * */
}
