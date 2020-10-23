package TCPs;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * 处理读操作的线程
 */
public class ReadHandlerThread implements Runnable, HandlerInterface {
    private Socket client;

    public ReadHandlerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        DataInputStream dataInputStream = null;
        try {
            while (true) {
                // 读取服务器端数据
                dataInputStream = new DataInputStream(client.getInputStream());
                String receive = dataInputStream.readUTF();
                System.out.println("返回的是：" + receive);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                if (client != null) {
                    client = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
