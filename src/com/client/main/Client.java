package com.client.main;

import java.io.IOException;

public class Client {
	public static void main(String[] args) throws IOException {

		ClientProtocol protocol = new ClientProtocol();
		protocol.download();
	}
}
