package com.juja.roy.sqcmd.view;

import java.util.Collection;
import java.util.List;

public interface Writer {
    void write(String string);

    void write(List<String> tables);

    void write(Collection<Collection<String>> tableData);
}
