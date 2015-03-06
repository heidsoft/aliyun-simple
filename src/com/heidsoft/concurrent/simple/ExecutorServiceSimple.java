package com.heidsoft.concurrent.simple;

import javax.sound.sampled.Port;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by heidsoft on 2015/3/6.
 * @author jake.liu
 * @version 1.0
 */
public class ExecutorServiceSimple {
    private static final int POOL_SIZE = 3;
    private static final int PORT = 8888;

    public static void main(String[] args) throws IOException{
        NetworkService networkService = new NetworkService(PORT,POOL_SIZE);
        ClientService clientService = new ClientService("127.0.0.1",8888);
        Thread serverThread = new Thread(networkService,"serverThread");
        serverThread.start();

        Thread clientThread = new Thread(clientService,"clientThread");
        clientThread.start();
    }
}

class NetworkService implements Runnable{
    private final ServerSocket serverSocket;
    private final ExecutorService pool;

    public NetworkService(int port, int poolSize)
        throws IOException{
        this.serverSocket = new ServerSocket(port);
        this.pool = Executors.newFixedThreadPool(poolSize);
    }

    public void run(){
        try{
            for (;;){
                pool.execute(new Handler(serverSocket.accept()));
            }
        }catch (IOException ex){
            pool.shutdown();
        }
    }
}

class Handler implements Runnable{
    private Socket socket;

    Handler(Socket socket){
        this.socket = socket;
    }
    public void run(){
       try{
           InputStream inputStream = socket.getInputStream();
           Reader reader = new InputStreamReader(inputStream);
           char[] buf = new char[1024];
           while ( reader.read(buf) !=-1 ){
                System.out.println("server accept message:"+buf);
           }
       }catch (IOException ex){
           ex.printStackTrace();
       }
    }
}

class ClientService implements Runnable{
    private Socket socket;
    private int port;
    private String host;

    ClientService(String host,int port) throws IOException{
        this.socket = new Socket(host,port);
    }

    public void run(){
        try{
            OutputStream outputStream = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(outputStream);
            for (;;){
                writer.write("test send some message:"+System.currentTimeMillis());
                System.out.println("test send some message:"+System.currentTimeMillis());
               // Thread.sleep(1000);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}



