package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException, InterruptedException {
		EchoClient client = new EchoClient();
		client.start(); //starts client
	}
// Make a class for KeyboardReader
	private class KeyboardReader implements Runnable {
	    OutputStream stream;

	    public KeyboardReader(OutputStream stream) {
	       this.stream = stream;
        }
		public void run(){
				int readByte;
				try{
				while((readByte = System.in.read()) != -1){
					stream.write(readByte);
				}
			}
				catch (IOException ioe){
					System.out.println("Error in KeyboardReader");
					System.out.println(ioe);
			}
		}
	}

// Make a class for ScreenWriter
		private class ScreenWriter implements Runnable{
			InputStream stream;

			public ScreenWriter(InputStream stream){
				this.stream = stream;
			}
			public void run(){
				int inByte;
				try{
				while ((inByte = stream.read()) != -1){
						System.out.write(inByte);
					}
				}
				catch (IOException ioe){
					System.out.println("Error in ScreenWriter");
					System.out.println(ioe);
			}
		}
	}


	private void start() throws IOException, InterruptedException {
		Socket socket = new Socket("localhost", PORT_NUMBER); //Creates the socket
		InputStream socketInputStream = socket.getInputStream(); //Creates InputStream
		OutputStream socketOutputStream = socket.getOutputStream(); //Creates OutputStream

		Thread outputThread = new Thread(new KeyboardReader(socketOutputStream));

		Thread inputThread = new Thread(new ScreenWriter(socketInputStream));

		outputThread.start();
		inputThread.start();
		outputThread.join();

//Tells client I'm done
				socket.shutdownOutput();
        inputThread.join();
        System.out.flush();
        socket.shutdownInput();

    }
}
