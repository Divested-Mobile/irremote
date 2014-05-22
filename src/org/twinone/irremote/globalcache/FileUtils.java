/*
 * Copyright 2014 Luuk Willemsen (Twinone)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.twinone.irremote.globalcache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.util.Log;

public class FileUtils {

	private static final String TAG = "StorageManager";

	/**
	 * True if the file was removed (or it didn't exist)
	 */
	public static boolean remove(File file) {
		if (file.exists())
			return file.delete();
		else
			return true;
	}

	public static boolean isAvailable(File file) {
		return file.exists() && file.isFile();
	}

	public static String get(File file) {
		try {
			InputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			return sb.toString();
		} catch (Exception e) {
			Log.i(TAG, "File did not exist" + file);
		}
		return null;
	}

	public static void put(File file, String data) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
			outputStreamWriter.write(data);
			outputStreamWriter.close();
		} catch (Exception e) {
			Log.w(TAG, "Error writing file: " + file.getAbsolutePath());
		}
	}

}
