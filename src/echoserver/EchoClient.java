package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException {
		EchoClient client = new EchoClient();
		client.start();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("enter info");
		int myint = keyboard.nextInt();
	}

	public interface runnable{
		public void run();
		Thread t = new Thread(new keyboardReader());
		t start();
		t wait();
	}

	public class keyboardReader implements runnable;



	private void start() throws IOException {
		Socket socket = new Socket("localhost", PORT_NUMBER);
		InputStream socketInputStream = socket.getInputStream();
		OutputStream socketOutputStream = socket.getOutputStream();
		int readByte;
		while ((readByte = System.in.read()) != -1) {
			socketOutputStream.write(readByte);
			int socketByte = socketInputStream.read();
			System.out.write(socketByte);
		}
		System.out.flush();
	}
}
