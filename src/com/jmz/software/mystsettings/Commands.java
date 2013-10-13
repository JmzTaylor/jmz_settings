package com.jmz.software.mystsettings;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.exceptions.RootDeniedException;
import com.stericson.RootTools.execution.CommandCapture;

import android.app.Activity;

public class Commands extends Activity {

	public static void sweep2wake(int direct) {
		if (direct == 0) {
			CommandCapture command = new CommandCapture(0, "echo 0 >> /sys/android_touch/sweep2wake");
			try {
				RootTools.getShell(true).add(command).waitForFinish();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (RootDeniedException e) {
				e.printStackTrace();
			}
		} else {
			CommandCapture command = new CommandCapture(0, "echo 1 >> /sys/android_touch/sweep2wake");
			try {
				RootTools.getShell(true).add(command).waitForFinish();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (RootDeniedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void forcecharge(int direct) {
		if (direct == 0) {
			CommandCapture command = new CommandCapture(0, "echo 0 >> /sys/kernel/fast_charge/force_fast_charge");
			try {
				RootTools.getShell(true).add(command).waitForFinish();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (RootDeniedException e) {
				e.printStackTrace();
			}		
		} else {
			CommandCapture command = new CommandCapture(0, "echo 1 >> /sys/kernel/fast_charge/force_fast_charge");
			try {
				RootTools.getShell(true).add(command).waitForFinish();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (RootDeniedException e) {
				e.printStackTrace();
			}
		}
	}
}
