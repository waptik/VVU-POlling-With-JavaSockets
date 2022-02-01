package io.github.waptik;


import java.net.*;
import java.io.*;


public class Server {
	public static void main(String[] args) throws IOException {
		
		Socket socket = null;
		
		InputStreamReader isr = null;
		OutputStreamWriter osw = null;
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		ServerSocket server = null;
		int votes = 0, yes = 0, no = 0, idc = 0;
		
		server = new ServerSocket(1111);
		
		while (true) {
			
			try {
				
				socket = server.accept();
				
				isr = new InputStreamReader(socket.getInputStream());
				osw = new OutputStreamWriter(socket.getOutputStream());
				
				br = new BufferedReader(isr);
				bw = new BufferedWriter(osw);
				
				
				// client communication
				
				while (true) {
					String clientMsg = br.readLine();
					
					System.out.println("Client sent: " + clientMsg);
					if (clientMsg == null) {
						break;
					}
					
					if (clientMsg.equalsIgnoreCase("BYE")) {
						break;
					} else if (clientMsg.startsWith("ans_")) {
						
						String msg = "";
						clientMsg=clientMsg.replace("ans_", "");
						
						int num = Integer.parseInt(clientMsg);
						
						switch (num) {
							case 1:
								yes += 1;
								msg = "Vote received";
								break;
							case 2:
								no += 1;
								msg = "Vote received";
								break;
							case 3:
								idc += 1;
								msg = "Vote received";
								break;
							case 0: {
								
								System.out.println("Client requested to view votes count.");
								
								votes = yes + no + idc;
								
								msg = "Voting results:\n\t\t\ttotal votes: " + votes + "\n" +
										      "\t\t\tYes: " + yes + "\n" +
										      "\t\t\tNo: " + no + "\n" +
										      "\t\t\tI don't care: " + idc;
								
							}
							break;
							
						}
						
						
						
						bw.write(msg);
						bw.newLine();
						
					}
					
					bw.flush();
				}
				
				server.close();
				isr.close();
				osw.close();
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
}

