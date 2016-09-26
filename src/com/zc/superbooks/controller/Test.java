package com.zc.superbooks.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		
		Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String integer = iterator.next();
            if(!integer.equals(""))
                iterator.remove();
            for (String string : list) {
				System.out.println(string);
			}
            if(integer.isEmpty())
            	break;
        }
		System.out.println("remove over");
	}
}
