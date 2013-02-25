package org.anonymous.android.library.posthermalprinterbluetoothdriver.helpers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;

public enum BluetoothUtils {
	INSTANCE;

	/**
	 * Get the paired bluetooth devices on the phone
	 * 
	 * @return List of paired bluetooth devices name
	 */
	public ArrayList<BluetoothDevice> getPairedBluetoothDevices() {

		ArrayList<BluetoothDevice> bluetoothDevices = null;

		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter
				.getBondedDevices();

		if (pairedDevices != null && pairedDevices.size() > 0) {
			bluetoothDevices = new ArrayList<BluetoothDevice>(pairedDevices);
		}
		return bluetoothDevices;
	}

	/**
	 * Get the names for the given bluetooth devices
	 * 
	 * @param bluetoothDevices
	 *            list of bluetooth devices
	 * @return list of names of the given bluetooth devices
	 */
	public ArrayList<String> getNamesForBluetoothDevices(
			ArrayList<BluetoothDevice> bluetoothDevices) {

		if (bluetoothDevices != null && bluetoothDevices.size() > 0) {

			ArrayList<String> names = new ArrayList<String>(0);
			for (BluetoothDevice bluetoothDevice : bluetoothDevices) {
				if (bluetoothDevice != null) {
					names.ensureCapacity(names.size() + 1);
					names.add(bluetoothDevice.getName());
				}
			}

			return names;
		}
		return null;
	}

	/**
	 * 
	 * @param context
	 * @param deviceNames
	 * @param listener
	 * @return
	 */
	public Dialog getSimpleDialogForBluetoothDeviceNames(Context context,
			ArrayList<String> deviceNames,
			DialogInterface.OnClickListener listener) {

		Dialog dialog = null;
		if (context != null && deviceNames != null && deviceNames.size() > 0) {

			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			CharSequence[] items = deviceNames
					.toArray(new CharSequence[deviceNames.size()]);
			builder.setItems(items, listener);
			builder.setTitle("Paired Bluetooth Devices");
			dialog = builder.create();
		}

		return dialog;
	}

	/**
	 * 
	 * @param device
	 * @return
	 */
	public BluetoothSocket connectToBluetoothDevice(BluetoothDevice device) {
		Method m = null;
		BluetoothSocket socket = null;
		try {
			m = device.getClass().getMethod("createRfcommSocket",
					new Class[] { int.class });
			socket = (BluetoothSocket) m.invoke(device, 1);
			socket.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return socket;
	}
}
