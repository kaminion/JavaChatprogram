package socketclassPractice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainClass {

	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		InputStream inputStream = null;
		DataInputStream dataInputStream = null;
		
		OutputStream outputStream = null;
		DataOutputStream dataOutputStream = null;
		
		try {
			serverSocket = new ServerSocket(9000);
			System.out.println("server socket 설정완료");
			
			socket = serverSocket.accept(); // 클라이언트 연결 시 클라이언트 소켓을 반환함
			// 엑셉트 메소드가 클라이언트 writeUTF에서 오는 값에 반응함 
			System.out.println("클라이언트 연결");
			System.out.println("socket : " + socket);
			
			
			inputStream = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			
			while(true) {
				String clientMessage = dataInputStream.readUTF();
				System.out.println("clientMessage : " + clientMessage);
				
				dataOutputStream.writeUTF("메세지 전송 완료");
				dataOutputStream.flush();
				
				if(clientMessage.equals("STOP")) break;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(dataOutputStream != null) dataOutputStream.close();
				if(outputStream != null) outputStream.close();
				if(dataInputStream != null) dataInputStream.close();
				if(inputStream != null) inputStream.close(); 
				if(socket != null) socket.close();
				if(serverSocket != null) serverSocket.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}

	}

}
