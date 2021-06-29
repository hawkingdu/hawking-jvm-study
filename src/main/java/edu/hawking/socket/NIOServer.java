package edu.hawking.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 杜皓君 created by 2021/5/23
 * NIOServer
 **/
public class NIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(9000));
        serverSocket.configureBlocking(false);
        System.out.println("服务器启动成功。。。。");
        List<SocketChannel> socketChannels = new CopyOnWriteArrayList<>();
        while (true) {
            SocketChannel socketChannel =  serverSocket.accept();
            if (socketChannel != null) {
                System.out.println(socketChannel.getRemoteAddress().toString() + " 连接成功！");
                socketChannel.configureBlocking(false);
                socketChannels.add(socketChannel);

            }
            for (SocketChannel sc : socketChannels) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                try {
                    int read = sc.read(byteBuffer);
                    if (read > 0) {
                        System.out.println("接收消息："+ new String(byteBuffer.array()));
                    } else if (read == -1) {
                        System.out.println(sc.getRemoteAddress().toString()+" 已断开连接。");
                        socketChannels.remove(sc);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
