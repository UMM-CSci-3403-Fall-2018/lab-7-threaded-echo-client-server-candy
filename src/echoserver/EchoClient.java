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
	}

	Public class keyboardReader implements runnable{
		OutputStream stream;
		Socket socket;

		public keyboardReader(Socket socket) throws IOException{
			try{
			this.output = socket.getOutputStream;
			this.socket = socket();
		}
		catch(IOException ioe){
			System.out.println("The keyboardReader has an error");
			System.out.println(ioe);
			}
		}
	}
		public void run(){
			try{
				int readByte;
				while((readByte = System.in.read()) != -1){
					output.write(readByte);
				}
			}
				catch(IOException ioe){
					System.out.println("Error");
					System.out.println(ioe);
			}
		}

		public class screenWriter implements runnable{
			InputStream stream;

			public screenWriter(InputStream stream){
				this.stream = stream;
			}
			public void run(){
				int inByte;
				try{
					while ((inbyte = stream.read()) != -1){
						System.out.write(inByte);
					}
				}
				catch(IOException ioe){
					System.out.println("Error");
					System.out.println(ioe);
			}
		}

		private void start() throws IOException, InterruptedException {
			Socket socket = new Socket("localhost", PORT_NUMBER);

			InputStream socketInputStream = socket.getInputStream();
			Thread inputThread = new Thread(new ScreenWriter(socketInputStream));

			OutputStream socketOutputStream = socket.getOutputStream();
			Thread outputThread = new Thread(new KeyboardReader(socketOutputStream));

			outputThread.start();
			inputThread.start();
			outputThread.join();

					socket.shutdownOutput();
	        inputThread.join();
	        System.out.flush();
	        socket.shutdownInput();

	    }
	}
}
