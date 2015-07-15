package com.filetransfer.server;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	public static void main(String[] args) throws IOException {
		final int BUFFER = 8192;
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream out = null;
		DataOutputStream dos = null;

		try {
			serverSocket = new ServerSocket(9999);
			socket = serverSocket.accept();

			//TODO Figure out way to send multiple files
			ArrayList<File> files = new ArrayList<File>();
			files.add(new File("/home/jmcmillon/Desktop/Jonathan McMillon-Resume-.doc"));
			
			// Get the size of the file
			long length = files.get(0).length();
			if (length > Integer.MAX_VALUE) {
				System.out.println("File is too large.");
			}
			byte[] bytes = new byte[BUFFER];
			fis = new FileInputStream(files.get(0));
			bis = new BufferedInputStream(fis);
			out = new BufferedOutputStream(socket.getOutputStream());
			dos = new DataOutputStream(new BufferedOutputStream(
					socket.getOutputStream()));
			
            //send file name to the client
			 dos.writeUTF(files.get(0).getName());

			int count;
			int fileSizeRemainder = (int) files.get(0).length();
			dos.writeInt(fileSizeRemainder);
			dos.flush();

			// send the file by chunks of bytes across to the client. If the filesize is smaller
			// than the set number of bytes to send, then send the remaining filesize across.
			while ((count = bis.read(bytes, 0, bytes.length)) > 0) {
				System.out.println("count: " + count + " filesize: " + fileSizeRemainder);
				if (fileSizeRemainder > count) {
					out.write(bytes, 0, count);
				} else {
					out.write(bytes, 0, fileSizeRemainder);
				}
				out.flush();
				fileSizeRemainder -= count;
			}
		} catch (IOException ex) {
			System.out.println("Can't setup server on this port number. ");
		} finally {
		//close all streams	
			out.close();
			fis.close();
			bis.close();
			dos.close();
			socket.close();
		}

	}

}
