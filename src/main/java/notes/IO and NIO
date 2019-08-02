Java NIO

IO

1.基于流，流只能读或者写







NIO

1.基于channel，可以把数据读到buffer中，通道既可以读也可以写

    FileChannel：读写文件
    DatagramChannel: UDP协议网络通信
    SocketChannel：TCP协议网络通信
    ServerSocketChannel：监听TCP连接
    
    
2.Buffer，封装后的buffer

    ByteBuffer、CharBuffer、IntBuffer，区别在于读写缓冲区的单位长度不一样

buffer的三个变量

    capacity （总容量）
    position （指针当前位置）
    limit （读/写边界位置）
    
buffer的几个常见用法
    
    flip(): 设置 limit 为 position 的值，然后 position 置为0。对Buffer进行读取操作前调用。
    rewind(): 仅仅将 position 置0。一般是在重新读取Buffer数据前调用，比如要读取同一个Buffer的数据写入多个通道时会用到。
    clear(): 回到初始状态，即 limit 等于 capacity，position 置0。重新对Buffer进行写入操作前调用。
    compact(): 将未读取完的数据（position 与 limit 之间的数据）移动到缓冲区开头，并将 position 设置为这段数据末尾的下一个位置。其实就等价于重新向缓冲区中写入了这么一段数据。
    

3.selector，用于采集各个通道的状态或事件

    通道有如下4个事件可供我们监听：
    
    Accept：有可以接受的连接
    Connect：连接成功
    Read：有数据可读
    Write：可以写入数据了
    

NIO擅长1个线程管理多条连接，节约系统资源，但是如果每条连接要传输的数据量很大的话，因为是同步I/O，会导致整体的响应速度很慢；而传统I/O为每一条连接创建一个线程，能充分利用处理器并行处理的能力，但是如果连接数量太多，内存资源会很紧张。

连接数多数据量小用NIO，连接数少用I/O
