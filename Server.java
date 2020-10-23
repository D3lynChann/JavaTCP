package TCPs;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // 监听的端口号
    public static final int PORT = 8000;

    public static void main(String[] args) {
        Server server = new Server();
        server.init();
    }

    // 初始化过程
    public void init() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket client = serverSocket.accept();
                // 一个客户端连接则开两个县城分别处理读写任务
                new Thread(new ReadHandlerThread(client)).start();
                new Thread(new WriteHandlerThread(client)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}