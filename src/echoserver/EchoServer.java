package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException, InterruptedException {
		EchoServer server = new EchoServer();
		server.start();
	}
	private class Threader implements Runnable{
		Socket socket;
		OutputStream outputStream;
		InputStream inputStream;

		public Threader (Socket socket){
			try{
				this.socket = socket;
				this.inputStream = socket.getInputStream();
				this.outputStream = socket.getOutputStream();
			}catch (IOException ioe) {
				System.out.println(ioe);
		}
	}
	public void run(){
		//Runs until KeyboardReader is done.
		try{
			int b;
			while ((b = inputStream.read()) != -1){
			outputStream.write(b);
		}
		socket.shutdownOutput();
	}catch (IOException ioe){
		System.out.println(ioe);
	}
	}
}

	private void start() throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
		while (true) {
			Socket socket = serverSocket.accept();
			Thread Client = new Thread(new Threader(socket));
			Client.start();
		}
	}
}
