package socketclassPractice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientClass {
	
	public static void main(String[] args) {
		Socket socket = null;
		
		OutputStream outputStream = null;
		DataOutputStream dataOutputStream = null; // output�� Ȯ��
		
		InputStream inputStream = null;
		DataInputStream dataInputStream = null;
		
		Scanner scanner = null;
		
		try {
			socket = new Socket("localhost", 9000);
			System.out.println("������ ����");
			System.out.println("socket" + socket);
			
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			inputStream = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			
			scanner = new Scanner(System.in);
			
			while(true)
			{
				System.out.println("�޼����� �Է��Ͻÿ�");
				String outMessage = scanner.nextLine();
				dataOutputStream.writeUTF(outMessage);
				dataOutputStream.flush();
				
				String inMessage = dataInputStream.readUTF();
				System.out.println("inMessage : " + inMessage);
				
				if(outMessage.equals("STOP")) break;
			}
			
			
		} catch (IOException e)
		{
			
		} finally {
			
			try {
				if(dataOutputStream != null) dataOutputStream.close();
				if(outputStream != null) outputStream.close();
				if(dataInputStream != null) dataInputStream.close();
				if(inputStream != null) inputStream.close(); 
				if(outputStream != null) outputStream.close();
				if(socket != null) socket.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
