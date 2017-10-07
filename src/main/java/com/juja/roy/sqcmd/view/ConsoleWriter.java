package com.juja.roy.sqcmd.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConsoleWriter implements Writer {

    @Override
    public String write(String string){
        System.out.println(string);
        return string;
    }

    @Override
    public String write(List<String> tables){

        StringBuilder sb = new StringBuilder();
        if(tables == null || tables.size() == 0){
            System.out.println("Empty list.");
            return "Empty list.";
        }
        int length = 0;
        for (String s:tables) {
            if(length < s.length()){
                length = s.length();
            }
        }
        sb.append("+-");
        for(int i=0; i<length; i++){
            sb.append("-");
        }
        sb.append("-+");
        sb.append("\n");
        sb.append("+ ");
        sb.append(tables.get(0));
        for(int i=tables.get(0).length(); i<length; i++) {sb.append(" ");};
        sb.append(" +");
        sb.append("\n");
        sb.append("+-");
        for(int i=0; i<length; i++){
            sb.append("-");
        }
        sb.append("-+");
        sb.append("\n");

        for(int i=1; i<tables.size(); i++){
            sb.append("+ ");
            sb.append(tables.get(i));
            for(int j=tables.get(i).length(); j<length; j++){
                sb.append(" ");
            }
            sb.append(" +");
            sb.append("\n");
        }
        sb.append("+-");
        for(int i=0; i<length; i++){
            sb.append("-");
        }
        sb.append("-+");
        System.out.println(sb.toString());
        return sb.toString();
    }

    @Override
    public String write(Collection<Collection<String>> tableData) {

        StringBuilder sb = new StringBuilder();
        if(tableData == null || tableData.isEmpty() || tableData.size() == 0){
            System.out.println("Empty tableData.");
            return "Empty tableData.";
        }
        List<List<String>> rowsList = (ArrayList)tableData;
        Integer[] rowsLength = new Integer[rowsList.get(0).size()];
        for(int i=0; i<rowsLength.length; i++){
            rowsLength[i] = 0;
        }
        for(List<String> row:rowsList){
            for(int i=0; i<rowsLength.length; i++){
                if(rowsLength[i] < row.get(i).length()){
                    rowsLength[i] = row.get(i).length();
                }
            }
        }

        sb.append("+-");
        for(int i=0; i<rowsLength.length; i++){
            for(int j=0; j<rowsLength[i]; j++) {
                sb.append("-");
            }
            sb.append("-+");
            if(i<rowsLength.length-1) {
                sb.append("-");
            }
        }
        sb.append("\n");

        for(int i=0; i<rowsList.size(); i++){
            sb.append("+ ");
            for(int j=0; j<rowsList.get(i).size(); j++) {
                sb.append(rowsList.get(i).get(j));
                for(int k=rowsList.get(i).get(j).length(); k<rowsLength[j]; k++){
                    sb.append(" ");
                }
                sb.append(" +");
                if(j<rowsLength.length-1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
            if(i==0 || i==rowsList.size()-1) {
                sb.append("+-");
                for (int k = 0; k < rowsLength.length; k++) {
                    for (int j = 0; j < rowsLength[k]; j++) {
                        sb.append("-");
                    }
                    sb.append("-+");
                    if(k<rowsLength.length-1) {
                        sb.append("-");
                    }
                }
                sb.append("\n");
            }

        }


        System.out.println(sb.toString());
        return sb.toString();
    }
}
