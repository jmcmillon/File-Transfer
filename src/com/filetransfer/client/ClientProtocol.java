package com.filetransfer.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.filetransfer.progressbar.FileProgressBar;

public class ClientProtocol {

	final int BUFFER = 8192;

	private Socket socket = null;
	private String host = "localhost";
	private InputStream is = null;
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private DataInputStream dis = null;

	public void download() throws IOException {

		try {
			socket = new Socket(host, 9999);
			is = socket.getInputStream();
			dis = new DataInputStream(new BufferedInputStream(
					socket.getInputStream()));

			String filename = dis.readUTF(); // name given from the server

			fos = new FileOutputStream("/home/jmcmillon/Documents/"
					+ filename); // download location
			bos = new BufferedOutputStream(fos);

			byte[] bytes = new byte[BUFFER];

			int count;
			int fileSize = dis.readInt();

			FileProgressBar progressBar = new FileProgressBar();

			double lengthPerPercent = 100.0 / fileSize; // percent of file
														// download
			int readLength = 0;

			// send the file by chunks of bytes across to the client. If the
			// filesize is smaller than the set number of bytes to send, then
			// send the remaining filesize across. Update the progressBar as 
			//you receive the bytes

			while ((count = is.read(bytes, 0, bytes.length)) > 0) {
				if (fileSize > count) {
					bos.write(bytes, 0, count);
					readLength += count;
				} else {
					bos.write(bytes, 0, fileSize);
				}
				bos.flush();
				fileSize -= count;
				progressBar.update((int) Math.round(lengthPerPercent * readLength));
			}
			progressBar.update((int) Math.round(lengthPerPercent * readLength));

		} catch (IOException ex) {
			System.out.println("Can't connect to the server");
		} finally {
			// close all streams
			bos.close();
			is.close();
			socket.close();
		}

	}

}
