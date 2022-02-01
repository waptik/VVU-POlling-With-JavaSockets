package io.github.waptik;

import java.net.*;
import java.io.*;
import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) throws IOException {
		Socket socket = null;
		
		InputStreamReader isr = null;
		OutputStreamWriter osw = null;
		
		BufferedWriter bw = null;
		BufferedReader br = null;
		
		try {
			socket = new Socket("localhost", 1111);
			
			isr = new InputStreamReader(socket.getInputStream());
			osw = new OutputStreamWriter(socket.getOutputStream());
			
			bw = new BufferedWriter(osw);
			br = new BufferedReader(isr);
			
			Scanner sc = new Scanner(System.in);
			boolean isNumber = false;
			
			
			System.out.println("Welcome to the Polling system. \n" +
					                   "Answer the question using 1,2 0r 3.\nTo see total votes, enter 0.\nTo stop, enter: BYE\n\n");
			
			while (true) {
				
				System.out.println("Question: Do you like this system?\n" +
						                   "1. yes\n" +
						                   "2. no\n" +
						                   "3. don't care");
				String ans = sc.nextLine();
				
				
				if (ans.startsWith("0") || ans.startsWith("1") || ans.startsWith("2") | ans.startsWith("3")) {
					
					isNumber = true;
					
					ans = "ans_" + ans;
					
				}
				
				bw.write(ans);
				bw.newLine();
				bw.flush();
				
				
				if (ans.equalsIgnoreCase("BYE")) {
					System.out.println("Good bye");
					break;
				}
				
				if (isNumber && Integer.parseInt(ans.replace("ans_", "")) == 0) {
					String line;
					while ((line = br.readLine()) != null) {
						System.out.println(line);
					}
					break;
				}
				
				System.out.println("Thanks for participating");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				if (socket !=null){
//					socket.close();
//				}
//				if (isr !=null){
//					isr.close();
//				}
//				if (osw !=null){
//					osw.close();
//				}
//				if (br !=null){
//					br.close();
//				}
//				if (bw !=null){
//					bw.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
		
	}
}
