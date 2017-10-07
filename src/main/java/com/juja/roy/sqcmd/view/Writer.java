package com.juja.roy.sqcmd.view;

import java.util.Collection;
import java.util.List;

public interface Writer {
    String write(String string);

    String write(List<String> tables);

    String write(Collection<Collection<String>> tableData);
}
