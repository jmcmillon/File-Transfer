package com.client.main;

import java.io.IOException;

public class TestClient {
	public static void main(String[] args) throws IOException {

		ClientProtocol protocol = new ClientProtocol();
		protocol.download();
	}
}
