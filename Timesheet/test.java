import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelType {
	
	public static List getValues(String columnname){
		String columnName = columnname;
		List<String> dates  = new ArrayList<>();
		Connection con = null;
		ResultSet ds=null;
		PreparedStatement preparedStatement = null;
		try{
		con = ConnectionFactory.createConnection();
		preparedStatement=con.prepareStatement("SELECT" + " " + columnName + " " + " from view_dailyrecord WHERE  YEARWEEK(`date`, 1) = YEARWEEK(CURDATE(), 1) AND user_id = '" + 1 + "' ORDER BY date" );
		ds= preparedStatement.executeQuery(); 
		ResultSetMetaData rsmd=ds.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while(ds.next()){
			for (int i = 1; i <= columnsNumber; i++) {
		      dates.add(ds.getString(i));
		      
			}
		}
		}
		catch(SQLException e){
		e.printStackTrace();
		}finally{
		  con.close();
		}
		return dates;
	}
	
	
	public static void writeXLSXFile() throws IOException {
		try{
		
		String excelFileName = "/home/nikhilbhole/Documents/Testsheet.xlsx";//name of excel file
		List<String> dates  = getValues("date");
		List<String> dayStart  = getValues("daystart");
		List<String> lunchStart  = getValues("lunchstart");
		List<String> lunchEnd  = getValues("lunchend");
		List<String> breakStart  = getValues("breakstart");
		List<String> breakEnd  = getValues("breakend");
		List<String> dayend  = getValues("dayend");
		List<String> officeHours  = getValues("officehours");
		List<String> lunchHours  = getValues("lunchhours");
		List<String> breakHours  = getValues("breakhours");
		List<String> hoursWorked  = getValues("workhours");
		List<String> casualLeave  = getValues("casualleave");
		List<String> sickLeave  = getValues("sickleave");
		List<String> holidays  = getValues("holidays");
		List<String> lwp  = getValues("leavewithoutpay");
		
		FileInputStream file = new FileInputStream(new File(excelFileName));
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheetAt(0);
		
		Cell cell = null;
		for(int i=0;i<dates.size();i++){
			cell = sheet.getRow(5).getCell(i+1);
			cell.setCellValue(dates.get(i));
		}
		for(int i=0;i<dayStart.size();i++){
			cell = sheet.getRow(6).getCell(i+1);
			cell.setCellValue(dayStart.get(i));
		}
		for(int i=0;i<lunchStart.size();i++){
			cell = sheet.getRow(7).getCell(i+1);
			cell.setCellValue(lunchStart.get(i));
		}
		for(int i=0;i<lunchEnd.size();i++){
			cell = sheet.getRow(8).getCell(i+1);
			cell.setCellValue(lunchEnd.get(i));
		}
		for(int i=0;i<breakStart.size();i++){
			cell = sheet.getRow(9).getCell(i+1);
			cell.setCellValue(breakStart.get(i));
		}
		for(int i=0;i<breakEnd.size();i++){
			cell = sheet.getRow(10).getCell(i+1);
			cell.setCellValue(breakEnd.get(i));
		}
		for(int i=0;i<dayend.size();i++){
			cell = sheet.getRow(11).getCell(i+1);
			cell.setCellValue(dayend.get(i));
		}
		for(int i=0;i<officeHours.size();i++){
			cell = sheet.getRow(12).getCell(i+1);
			cell.setCellValue(officeHours.get(i));
		}
		for(int i=0;i<lunchHours.size();i++){
			cell = sheet.getRow(13).getCell(i+1);
			cell.setCellValue(lunchHours.get(i));
		}
		for(int i=0;i<breakHours.size();i++){
			cell = sheet.getRow(14).getCell(i+1);
			cell.setCellValue(breakHours.get(i));
		}
		for(int i=0;i<hoursWorked.size();i++){
			cell = sheet.getRow(15).getCell(i+1);
			cell.setCellValue(hoursWorked.get(i));
		}
		for(int i=0;i<casualLeave.size();i++){
			cell = sheet.getRow(16).getCell(i+1);
			cell.setCellValue(casualLeave.get(i));
		}
		for(int i=0;i<sickLeave.size();i++){
			cell = sheet.getRow(17).getCell(i+1);
			cell.setCellValue(sickLeave.get(i));
		}
		for(int i=0;i<holidays.size();i++){
			cell = sheet.getRow(18).getCell(i+1);
			cell.setCellValue(holidays.get(i));
		}
		for(int i=0;i<lwp.size();i++){
			cell = sheet.getRow(19).getCell(i+1);
			cell.setCellValue(lwp.get(i));
		}
		for(int i=0;i<hoursWorked.size();i++){
			cell = sheet.getRow(22).getCell(i+1);
			cell.setCellValue(hoursWorked.get(i));
		}
		
		FileOutputStream outFile =new FileOutputStream(new File(excelFileName));
		wb.write(outFile);
		outFile.close();
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		
		
		
	}

public static void main(String[] args) throws IOException {
	writeXLSXFile();
	
}
}

