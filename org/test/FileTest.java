package org.test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class FileTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileTest f = new FileTest();
        //12
        f.fileReadByByte("org/test/hello.txt");
        f.fileReadByChar("org/test/hello.txt");
        f.writeReadByByte("abc.txt","word hello111!");
        f.writeReadByChar("org/test/hello3.txt","word hello111!");
        //3
        f.twoThreadWork();
    }

    public void fileReadByByte(String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        int len = 0;
        //缓冲区 每次读入1个字节 随意设置
        byte[] buf = new byte[1];
        StringBuilder sb = new StringBuilder();
        while ((len=is.read(buf)) != -1) {
            //读一个拼接一个
            sb.append(new String(buf, 0, len));
        }
        System.out.println("对字节数据文件读取"+sb);
    }
    public void fileReadByChar(String filePath) throws IOException {
        Reader is = new FileReader(filePath);
        int len = 0;
        //缓冲区 每次读入1个字符 随意设置
        char[] buf = new char[1];
        StringBuilder sb = new StringBuilder();
        while ((len=is.read(buf)) != -1) {
            //读一个拼接一个
            sb.append(new String(buf, 0, len));
        }
        System.out.println("对字符数据文件读取" + sb);
    }
    public void writeReadByByte(String filePath,String content)throws IOException{
        OutputStream os = new FileOutputStream(filePath);
        byte[] buf = content.getBytes(StandardCharsets.UTF_8);
        os.write(buf,0,buf.length);
        System.out.println("成功写入字节文件 "+filePath+" 内容为 "+content);
    }
    public void writeReadByChar(String filePath,String content)throws IOException{
        Writer os = new FileWriter(filePath);
        char[] buf = content.toCharArray();
        os.write(buf,0,buf.length);
        System.out.println("成功写入字符文件 "+filePath+" 内容为 "+content);
    }

    public void twoThreadWork() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            Random r = new Random();
            System.out.println("打印随机数线程");
            while (true) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(r.nextInt(100));

            }
        });
        Thread t2 = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            System.out.println("输入线程");
            String in = sc.next();
            Writer os = null;
            try {
                os = new FileWriter("org/test/hello4.txt",true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.write(in);
                os.flush();
                System.out.println("输入完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
    //前4个方法分别代表对字节和字符文件的读取，读取的文件的相对路径org/test/hello.txt ，是对字节和字符文件的写入，写入文件的相对路径是org/test/hello2.txt和org/test/hello3.txt,。最后一个方法开辟两个线程，一个生产随机数，一个读取键盘输入，然后写入org/test/hello4.txt
    //

}