package org.fourgeeks.gha.dbadmin.server;

import java.io.File;
import java.util.ArrayList;

import org.fourgeeks.gha.dbadmin.shared.GHARecord;

import jxl.Sheet;
import jxl.Workbook;

public class DataLoader {

	public static void debug(String msg) {
		System.out.println(msg);
	}
	
	public DataLoader(File file) {
		try {
			debug("Cargando archivo xls...");
			Workbook wb = Workbook.getWorkbook(file);
			Sheet sheet = wb.getSheet(0);
			Integer offset = 1;
			TableServiceImpl.table.setTableName(sheet.getCell(1, 1).getContents());
			ArrayList<String> columnList = new ArrayList<String>();
			TableServiceImpl.table.setColumns(columnList);
			columnList.add(sheet.getCell(0, offset + 1).getContents());
			for (Integer i = 1; i < sheet.getColumns(); i++) {
				String s = sheet.getCell(i, 2).getContents();
				if (s.equals(""))
					throw new Exception("Error: la celda (" + i.toString() + ", 2) esta en blanco");
				columnList.add(s);
			}
			debug("Obteniendo valores de la tabla...");
			ArrayList<GHARecord> list = new ArrayList<GHARecord>();
			TableServiceImpl.table.setRecords(list);
			for (Integer i = offset + 2; i < sheet.getRows(); i++) {
				GHARecord r = new GHARecord();
				String tmp = sheet.getCell(0, i).getContents();
				r.add(tmp);
				for (Integer j = 1; j < sheet.getColumns(); j++) {
					tmp = sheet.getCell(j, i).getContents();
					if (tmp.equals(""))
						throw new Exception("Error: la celda (" + j.toString() + ", " + i.toString() + ") esta en blanco");
					r.add(tmp);
				}
				list.add(r);
			}
			debug("Finalizado!");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
