package TCPs;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/*
 * 处理写操作的线程
 */
class WriteHandlerThread implements Runnable, HandlerInterface {
    private Socket client;

    public WriteHandlerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        DataOutputStream dataOutputStream = null;
        BufferedReader bufferedReader = null;
        try {
            while (true) {
                // 取得输出流
                dataOutputStream = new DataOutputStream(client.getOutputStream());
                System.out.println("请输入：");
                // 键盘录入
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String send = bufferedReader.readLine();
                System.out.println();
                // 发送数据
                dataOutputStream.writeUTF(send);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (client != null) {
                    client = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
