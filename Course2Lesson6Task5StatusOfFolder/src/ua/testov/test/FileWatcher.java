package ua.testov.test;

import java.io.File;
import java.text.SimpleDateFormat;

public class FileWatcher implements Runnable {
	private File mainFolder;

	public FileWatcher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public File getMainFolder() {
		return mainFolder;
	}

	private String getFolderInfo() {
		if (mainFolder == null) {
			return "None";
		}
		StringBuilder sb = new StringBuilder();
		File[] fileArray = this.mainFolder.listFiles();
		for (File file : fileArray) {
			sb.append(file.getName() + "\t" + file.length()).append(System.lineSeparator());
		}
		return sb.toString();
	}

	public FileWatcher(File mainFolder) {
		super();
		this.mainFolder = mainFolder;
	}

	@Override
	public void run() {
		Thread thr = Thread.currentThread();
		SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy   hh:mm:ss");
		
		for (; !thr.isInterrupted();) {
			String str=getFolderInfo();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
			if(!str.equals(getFolderInfo())) {
			System.out.println(sdf.format(System.currentTimeMillis()));
			System.out.println(getFolderInfo());
			System.out.println();
			}
			
		}
		System.out.println("File watcher is stop.");
	}
}
