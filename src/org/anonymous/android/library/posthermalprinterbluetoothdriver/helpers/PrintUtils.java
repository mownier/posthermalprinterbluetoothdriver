package org.anonymous.android.library.posthermalprinterbluetoothdriver.helpers;

import java.io.IOException;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;

public enum PrintUtils {
	INSTANCE;

	/**
	 * 
	 * @param text
	 * @param socket
	 * @return
	 */
	public boolean printText(String text, BluetoothSocket socket) {

		boolean printed = false;
		if (text != null && socket != null) {

			try {
				OutputStream os = socket.getOutputStream();
				if (os != null) {
					os.write(text.getBytes(), 0, text.getBytes().length);
					os.write(new byte[] { 0x0a }, 0, 1);
					printed = true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return printed;
	}
}
