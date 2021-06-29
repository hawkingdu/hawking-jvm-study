package edu.hawking.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 杜皓君 created by 2021/5/23
 * NIOSelectorServer
 **/
public class NIOSelectorServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(9000));
        serverSocket.configureBlocking(false);
        System.out.println("服务器启动........");
        Selector selector = Selector.open();
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            System.out.println("收到事件");
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                System.out.println("事件类型："+sk.channel().toString());
                if (sk.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) sk.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println(socketChannel.getRemoteAddress().toString() + " 连接成功！");
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);

                }else if (sk.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) sk.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                    int read = socketChannel.read(byteBuffer);
                    if (read > 0) {
                        System.out.println("接受消息："+ new String(byteBuffer.array()));
                    }
                }
                iterator.remove();
            }

        }
    }
}
