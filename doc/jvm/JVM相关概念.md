# JVM相关概念

1. 系统知识

   * 寄存器卸载

     当活跃状态（Live State，即变量）数超过CPU寄存器数，多出的活跃状态只能存放在内存中，就会发生寄存器卸载。寄存器卸载时，某些活跃状态必须从CPU寄存器“卸载”到内存中。因此避免寄存器卸载可以让程序执行的更快。

2. JVM特性

   * 压缩指针

     ```shell
     -XX:+UseCompressedOops
     ```

     压缩指针通过对齐和偏移量将64位指针压缩成32位。性能提高是因为使用了更小更节省空间的压缩指针而不是完整长度的64为指针，CPU缓存使用率由此得以改善。

3. JVM实现

   * GC堆、栈大小等默认情况下是在HotSpot VM内部选择的

4. HotSpot VM垃圾收集器

   HotSpot VM使用分代垃圾收集器

   垃圾收集算法基于一下两个观察事实（弱分代假设）

   * 大多数分配对象的存活时间都很短
   * 存活时间久的对象很少引用存活时间短的对象

   HotSpot VM将`堆`分成2个物理区，即`分代`

   * **新生代**：大多数新创建的对象被分配在新生代中，与整个java堆相比，通常新生代的空间比较小而且收集频繁。新生代中大部分对象的存活时间很短，所以通常来说，新生代收集（也称为`次要垃圾收集`，记为`Minor GC`）之后存活的对象很少。因为Minor GC关注小并且有大量垃圾对象的空间，所以通常垃圾收集的效率很高。
   * **老年代**：新生代中长期存活的对象最后会被`提升`或`晋升`到老年代。通常来说，老年代的空间比新生代大，而空间占用的增长速度比新生代慢。相比Minor GC而言，老年代收集（也称为`主要垃圾收集`或`完全垃圾收集`，记为`Full GC`）的执行频率比较低，但是一旦发生，执行时间就会很长。
   * **永久代**：**用户创建的对象最终并会从老年代移送到永久带**。JVM只是用它来存储元数据，例如类的数据结构、保留字符串等。

   **G1垃圾收集器**

   G1是一个并行、并发和增量式压缩低停顿的垃圾收集器。

   G1的堆内存结构和别的垃圾收集器不一样，G1把内存分为大小相同的区域Region，每个Region拥有各自的分代属性，但这些分代不需要连续。

   G1的垃圾收集器将区域中的存活对象转移到另外一些区域，然后收集前者（通常是更大）。大部分时候只收集新生区域（这些形成G1的新生代），它们相当于Minor GC。G1也定期执行并发标记，以表识那些空或几乎空的非新生区域。

5. 应用程序对垃圾收集器的影响

   * 内存分配
   * 存活数据的多少
   * 老年代中的引用更新

6. JVM监控

   生产环境中应该自始至终地监控应用JVM。JVM的监控范围包括垃圾收集、JIT编译以及类加载。

   **性能监控**：指一种在生产、质量评估或者开发环境中实施的带有预防或主动性的活动。

   **性能分析**：以一种侵入方式收集运行性能数据的活动，它会影响应用的吞吐量或响应性。性能分析很少在生产环境中进行，通常是在质量评估、测试或开发环境中，常常是性能监控之后再采取行动。

   **性能调优**：一种为改善响应性或吞吐量而更改参数、源代码或属性配置的活动。性能调优通常是在性能监控或性能分析之后进行。

   **重要的垃圾回收数据**

   * 当前使用的垃圾收集器
   * Java堆的大小
   * 新生代和老年代的大小
   * 永久代的大小
   * Minor GC的持续时间
   * Minor GC的频率
   * Minor GC的空间回收量
   * Full GC的持续时间
   * Full GC的频率
   * 每个并发垃圾收集周期内的空间回收量
   * 垃圾收集前后Java堆的占用量
   * 垃圾收集前后新生代和老年代的占用量
   * 垃圾收集前后永久代的占用量
   * 是否老年代或永久代的占用触发了Full GC
   * 应用是否显式调用了System.gc()

   **建议开启HotSpot VM报告垃圾收集数据**
   ```shell
   -XX:+PrintGCDetails # 打印垃圾收集信息
   -XX:+PrintGCDetails -Xloggc:/home/das/gc.log # 指定垃圾收集信息存储到日志文件
   ```

   ```shell
   [23.494s][info   ][gc,start      ] GC(68) Pause Young (Normal) (G1 Evacuation Pause)
   [23.494s][info   ][gc,task       ] GC(68) Using 10 workers of 10 for evacuation
   [23.495s][info   ][gc,phases     ] GC(68)   Pre Evacuate Collection Set: 0.0ms
   [23.495s][info   ][gc,phases     ] GC(68)   Evacuate Collection Set: 0.3ms
   [23.495s][info   ][gc,phases     ] GC(68)   Post Evacuate Collection Set: 0.2ms
   [23.495s][info   ][gc,phases     ] GC(68)   Other: 0.2ms
   [23.495s][info   ][gc,heap       ] GC(68) Eden regions: 178->0(178)
   [23.495s][info   ][gc,heap       ] GC(68) Survivor regions: 1->1(23)
   [23.495s][info   ][gc,heap       ] GC(68) Old regions: 12->12
   [23.495s][info   ][gc,heap       ] GC(68) Humongous regions: 0->0
   [23.495s][info   ][gc,metaspace  ] GC(68) Metaspace: 44173K->44173K(1089536K)
   [23.495s][info   ][gc            ] GC(68) Pause Young (Normal) (G1 Evacuation Pause) 379M->23M(598M) 0.815ms
   [23.495s][info   ][gc,cpu        ] GC(68) User=0.00s Sys=0.00s Real=0.01s
   ```

   