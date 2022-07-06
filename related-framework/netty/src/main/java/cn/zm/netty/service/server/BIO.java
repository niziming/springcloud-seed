package cn.zm.netty.service.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * BIO
 */
public class BIO {
  final static Integer port = 8000;

  /**
   * server
   */
  public static class Server {
    static ServerSocket serverSocket;

    static {
      try {
        serverSocket = new ServerSocket(port);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    public static void runServer() throws IOException {
      // 阻塞获取新连接
      Socket socket = serverSocket.accept();
      //(2)每一个新的连接都创建一个线程,负责读取数据
      new Thread(() -> {
        try {
          byte[] data = new byte[1024];
          InputStream inputStream = socket.getInputStream();
          while (true) {
            int len;
            //(3)按照字节流方式读取数据
            while ((len = inputStream.read(data)) != -1)
              System.out.println(new String(data, 0, len));
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }).start();
    }

    public static void main(String[] args) throws IOException {
      Server.runServer();
    }
  }

  /**
   * client
   */
  public static class Client {

    public static void runClient() {
      new Thread(() -> {
        try {
          Socket socket = new Socket("127.0.0.1", 8000);
          while (true) {
            try {
              socket.getOutputStream().write((new Date() + ": hello world").getBytes());
              socket.getOutputStream().flush();
              Thread.sleep(2000);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }).start();
    }

    public static void main(String[] args) {
      Client.runClient();
    }

  }



}
