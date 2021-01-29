package com.azarenka.batwriter.api;

import com.azarenka.batwriter.domain.CommandToAppend;
import com.azarenka.batwriter.domain.Document;

import java.io.IOException;

public interface Builder {

    Document buildDocument(CommandToAppend command) throws IOException;
}
