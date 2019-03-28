package control;

import java.io.File;

import javax.swing.filechooser.FileFilter;

//FileFilter子类，使FileChooser只能选区csv文件
public class CsvFileFilter extends FileFilter {
	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
            return true;
        }

        String extension = file.getName().trim().substring(file.getName().trim().indexOf(".")+1);
        if (extension != null) {
            if (extension.equals("csv")) {
                return true;
            }else {
                return false;
            }
        }
        return false;
	}
	@Override
	public String getDescription() {
		return "*.csv";
	}

}
