package org.fourgeeks.dbadmin.server;

import java.io.File;
import java.util.ArrayList;

import org.fourgeeks.dbadmin.shared.Record;

import jxl.Sheet;
import jxl.Workbook;

public class DataLoader {

	public static void debug(String msg) {
		System.out.println(msg);
	}
	
	private ArrayList<Record> list;
	private ArrayList<String> columnList;
	
	public ArrayList<Record> getList() {
		return list;
	}
	
	public ArrayList<String> getColumns() {
		return columnList;
	}
	
	public DataLoader(File file) {
		try {
			debug("Cargando archivo xls...");
			Workbook wb = Workbook.getWorkbook(file);
			Sheet sheet = wb.getSheet(0);
			Integer offset = 1;
			String table_name = sheet.getCell(1, 1).getContents();
			columnList = new ArrayList<String>();
			columnList.add(sheet.getCell(0, offset + 1).getContents());
			String columns = " (" + sheet.getCell(0, offset + 1).getContents();
			debug("Obteniendo el nombre de las columnas...");
			for (Integer i = 1; i < sheet.getColumns(); i++) {
				String s = sheet.getCell(i, 2).getContents();
				if (s.equals(""))
					throw new Exception("Error: la celda (" + i.toString() + ", 2) esta en blanco");
				columns += ", "+ s;
				columnList.add(s);
			}
			columns += ") ";
			debug("Obteniendo valores de la tabla...");
			list = new ArrayList<Record>();
			for (Integer i = offset + 2; i < sheet.getRows(); i++) {
				Record r = new Record();
				String tmp = sheet.getCell(0, i).getContents();
				r.add(tmp);
				String out = "INSERT INTO " + table_name + columns + "VALUES ('" + tmp + "'";
				for (Integer j = 1; j < sheet.getColumns(); j++) {
					tmp = sheet.getCell(j, i).getContents();
					if (tmp.equals(""))
						throw new Exception("Error: la celda (" + j.toString() + ", " + i.toString() + ") esta en blanco");
					out += ",'" + tmp + "'";
					r.add(tmp);
				}
				out += ");";
				r.setQuery(out);
				list.add(r);
			}
			debug("Finalizado!");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
