package util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static void createFolder(String folderPath) {
		// Set the path of Folders to be created
		File files = new File(folderPath);
		if (!files.exists()) {
			if (files.mkdirs()) {
				Log.info("Multiple directories are created: " + folderPath);
			} else {
				Log.info("Failed to create multiple directories: " + folderPath);
			}
		}
	}

	public static String GetCurrentDate(String sDateFormat) {
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat(sDateFormat);

		// get current date time with Date()
		Date date = new Date();

		// Now format the date
		String sDate = dateFormat.format(date);
		return sDate;
	}

	/*public static void takeSnapshot() {
		String sSnapshotFileName = DriverScript.sTestCaseID + "_"
				+ DriverScript.sTestStepID + "_" + System.currentTimeMillis()
				+ ".jpg";
		// create folder before generate snapshot
		String sPath = DriverScript.sPathContainingSnapshot;
		Utils.createFolder(sPath);

		try {
			// as press print screen button
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit
							.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "jpg", new File(sPath + "//"
					+ sSnapshotFileName));

		} catch (Exception e) {

			Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "
					+ e.getMessage());
		}
	}*/
}
