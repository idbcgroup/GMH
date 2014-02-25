package main;

import java.io.File;
import java.io.PrintWriter;

import jxl.Sheet;
import jxl.Workbook;

public class DataLoader {

	public static void debug(String msg) {
		System.out.println(msg);
	}
	
	public static void main(String[] args) {
		try {
			PrintWriter fileOut = new PrintWriter("generated.sql", "UTF-8");
			debug("Cargando archivo xls...");
			Workbook wb = Workbook.getWorkbook(new File("workbook.xls"));
			Sheet sheet = wb.getSheet(0);
			Integer offset = 1;
			String table_name = sheet.getCell(1, 1).getContents();
			String columns = " (" + sheet.getCell(0, offset + 1).getContents();
			debug("Obteniendo el nombre de las columnas...");
			for (Integer i = 1; i < sheet.getColumns(); i++) {
				String s = sheet.getCell(i, 2).getContents();
				if (s.equals(""))
					throw new Exception("Error: la celda (" + i.toString() + ", 2) esta en blanco");
				columns += ", "+ s;
			}
			columns += ") ";
			debug("Obteniendo valores de la tabla...");
			for (Integer i = offset + 2; i < sheet.getRows(); i++) {
				String out = "INSERT INTO " + table_name + columns + "VALUES ('" + sheet.getCell(0, i).getContents() + "'";
				for (Integer j = 1; j < sheet.getColumns(); j++) {
					String s = sheet.getCell(j, i).getContents();
					if (s.equals(""))
						throw new Exception("Error: la celda (" + j.toString() + ", " + i.toString() + ") esta en blanco");
					out += ",'" + s + "'";
				}
				out += ");";
				fileOut.println(out);
			}
			debug("Finalizado!");
			fileOut.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
