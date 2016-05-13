package junit.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 服务端进行编程
 * @author Administrator
 *
 */
public class NioTestServer {
    
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        
        
        //1.创建网络Socket通道,设为非阻塞模式
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        //2.由通道创建ServerSocket,并bind域名和端口
        ServerSocket server = channel.socket();
        server.bind(new InetSocketAddress("www.baidu.com",8080));
       //3.生成一个选择器，将通道注册到选择器上
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        
        System.err.println("ServerSocketChannel is start.....");
        
        
        //4.选择器开始选择客户端通道
        while(selector.select()>0){
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectedKeys.iterator();
            while(it.hasNext()){
                SelectionKey key = it.next();
                it.remove();//处理完就删除一个
                if(key.isAcceptable()){ 
                   //接受了客户端请求,要注册客户端通道
                   ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
                   SocketChannel clientChannel1 = serverChannel.accept();
                   clientChannel1.configureBlocking(false);
                   clientChannel1.register(selector, SelectionKey.OP_READ);
                }else if(key.isReadable()){//读取客户端数据
                    SocketChannel clientChannel2 = (SocketChannel)key.channel();
                    buffer.clear();
                    clientChannel2.read(buffer);
                }else if(key.isWritable()){//向客户端写数据
                    
                }
            }
        }
        
    }

}
