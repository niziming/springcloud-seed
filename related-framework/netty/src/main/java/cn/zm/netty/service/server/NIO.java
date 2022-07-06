package cn.zm.netty.service.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO
 * @author DELL
 */
public class NIO {
  final static Integer port = 8000;
  static Selector serverSelector;
  static Selector clientSelector;

  // init
  static {
    try {
      serverSelector = Selector.open();
      clientSelector = Selector.open();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * serverSelector负责轮询是否有新的连接,clientSelector负责轮询连接是否有数据可读.
   * 服务端监测到新的连接不再创建一个新的线程,而是直接将新连接绑定到clientSelector上,这样不用IO模型中1w个while循环在死等
   * clientSelector被一个while死循环包裹,如果在某一时刻有多条连接有数据可读通过 clientSelector.select(1)方法轮询出来进而批量处理
   * 数据的读写以内存块为单位
   *
   * @throws IOException
   */
  public static class Server {
    public static void runServer() throws IOException {
      ServerSocketChannel ssc = ServerSocketChannel.open();
      ssc.socket().bind(new InetSocketAddress(port));
      ssc.configureBlocking(false);
      ssc.register(serverSelector, SelectionKey.OP_ACCEPT);

      while (true) {
        // 轮询监测是否有新的连接
        if (serverSelector.select(1) > 0) {
          Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
          Iterator<SelectionKey> iterator = selectionKeys.iterator();
          while (iterator.hasNext()) {
            SelectionKey nextKey = iterator.next();
            SocketChannel channel = ((ServerSocketChannel) nextKey.channel()).accept();
            try {
              // 是可接收的
              if (nextKey.isAcceptable()) {
                //(1)每来一个新连接不需要创建一个线程而是直接注册到clientSelector
                channel.configureBlocking(false);
                // channel.register(clientSelector, SelectidengjionKey.OP_READ);
              }
              // 是可读的
              ByteBuffer buffer = (ByteBuffer) nextKey.attachment();
              if (nextKey.isReadable()) {
                channel.read(buffer);
                System.out.println(new String(buffer.array()));
              }
              channel.write(buffer.put((LocalDateTime.now()+":hello").getBytes()));
            } finally {
              iterator.remove();

            }
          }
        }
      }
    }

    public static void main(String[] args) throws IOException {
      Server.runServer();
    }
  }

  /**
   * client
   */
  public static class Client {
    public static void clientWrite(String msg) throws IOException, InterruptedException {
      SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress("localhost", port));
      while (true) {
        clientChannel.configureBlocking(false);
        ByteBuffer allocate = ByteBuffer.allocate(1<<8);
        clientChannel.write(allocate.put(msg.getBytes(StandardCharsets.UTF_8)));
        Thread.sleep(2000);
      }
    }

    public static void runClient() throws IOException, InterruptedException {
      while (true) {
        clientWrite(LocalDateTime.now()+":hello");
        Thread.sleep(2000);
        // (2)批量轮询是否有哪些连接有数据可读
        if (clientSelector.select(1) > 0) {
          Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
          Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
          while (keyIterator.hasNext()) {
            SelectionKey selectionKey = keyIterator.next();
            if (selectionKey.isReadable()) {
              try {
                SocketChannel channel = (SocketChannel) selectionKey.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                //(3)读取数据以块为单位批量读取
                channel.read(byteBuffer);
                byteBuffer.flip();
                channel.write(byteBuffer.put((LocalDateTime.now() + "hello").getBytes()));
                System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer));
              } catch (CharacterCodingException e) {
                e.printStackTrace();
              } catch (IOException e) {
                e.printStackTrace();
              } finally {
                keyIterator.remove();
                selectionKey.interestOps(SelectionKey.OP_READ);
              }
            }
          }
        }
      }
    }

    public static void main(String[] args) {
      try {
        Client.runClient();
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

}
