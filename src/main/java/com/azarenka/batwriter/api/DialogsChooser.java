package com.azarenka.batwriter.api;

import java.io.File;

public interface DialogsChooser {

    File showDialog(String path, boolean isFolder);
}
