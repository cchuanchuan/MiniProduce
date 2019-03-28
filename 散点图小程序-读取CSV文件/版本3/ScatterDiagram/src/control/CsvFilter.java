package control;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CsvFilter extends FileFilter {

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
		// TODO Auto-generated method stub
		return null;
	}

}
