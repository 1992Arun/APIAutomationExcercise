package org.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.pojo.Category;
import org.pojo.Parents;
import org.pojo.Products;
import org.pojo.Usertype;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import 	io.restassured.builder.RequestSpecBuilder;
import 	  io.restassured.builder.ResponseSpecBuilder ;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;


public class UtilityClass {

	public static    PrintStream p;

	public static   Sheet sheet;

	public static String properties(String key)   {

		Properties p = new Properties();

		try {

			FileReader f = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");



			p.load(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p.getProperty(key);

	}


	public static RequestSpecification RequestSpecBuilder() {

		if(p==null) {

			try {
				p = new PrintStream("log.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		RequestSpecification build = new RequestSpecBuilder()

				.setBaseUri(properties("BaseURI"))
				.setContentType("application/x-www-form-urlencoded")
				.addFilter(RequestLoggingFilter.logRequestTo(p))
				.addFilter(ResponseLoggingFilter.logResponseTo(p))
				.build();

		return build;


	}

	public static ResponseSpecification ResponseSpecBuilder(int code ) {

		ResponseSpecification build = new ResponseSpecBuilder()

				.expectStatusCode(code).build();

		return build;

	}

	public static void PostRequestPayload() throws JsonProcessingException {	

		String[][] readXL = readXL("New");

		Map<String, String > mp = new LinkedHashMap();

		for(int i=0; i<readXL("New").length;i++) {

			mp.put("name", readXL[i][0]);

			mp.put("email", readXL[i][1]);

			mp.put("password", readXL[i][2]);

			mp.put("title", readXL[i][3]);

			mp.put("birth_date", readXL[i][4]);

			mp.put("birth_month", readXL[i][5]);

			mp.put("birth_year", readXL[i][6]);

			mp.put("firstname", readXL[i][7]);

			mp.put("lastname", readXL[i][8]);

			mp.put("company", readXL[i][9]);

			mp.put("address1", readXL[i][10]);

			mp.put("address2", readXL[i][11]);

			mp.put("country", readXL[i][12]);

			mp.put("zipcode", readXL[i][13]);

			mp.put("state", readXL[i][14]);

			mp.put("city", readXL[i][15]);

			mp.put("mobile_number", readXL[i][16]);

			System.out.println(mp);

			// ObjectMapper ne = new ObjectMapper();

		}

	}




	public static Map<String, String> PutRequestPayload() throws JsonProcessingException {

		String[][] readXL = readXL("put");



		Map<String, String > mp = new LinkedHashMap();

		for(int i=0; i<readXL.length;i++) {

			mp.put("name", readXL[i][0]);

			mp.put("email", readXL[i][1]);

			mp.put("password", readXL[i][2]);

			mp.put("title", readXL[i][3]);

			mp.put("birth_date", readXL[i][4]);

			mp.put("birth_month", readXL[i][5]);

			mp.put("birth_year", readXL[i][6]);

			mp.put("firstname", readXL[i][7]);

			mp.put("lastname", readXL[i][8]);

			mp.put("company", readXL[i][9]);

			mp.put("address1", readXL[i][10]);

			mp.put("address2", readXL[i][11]);

			mp.put("country", readXL[i][12]);

			mp.put("zipcode", readXL[i][13]);

			mp.put("state", readXL[i][14]);

			mp.put("city", readXL[i][15]);

			mp.put("mobile_number", readXL[i][16]);

			// ObjectMapper ne = new ObjectMapper();
		}

		return mp;



	}

	public static Map<String, String> deleteRequest() {

		Map<String, String > mp = new LinkedHashMap();

		mp.put("email", "arun61@mgai.com");

		mp.put("password", "1243434");

		return mp;

	}


	public static Map<String, String> searchProduct(String product) {

		Map<String, String > mp = new LinkedHashMap();

		mp.put("search_product",product);

		return mp;

	}


	public static Map<String, String> verifyUserExist(String email, String password) {

		Map<String, String > mp = new LinkedHashMap();

		mp.put("email", email);

		mp.put("password", password);

		return mp;

	}

	public static String[][] readXL(String sheet1) {

		XSSFWorkbook wb = null;

		try {
			FileInputStream f = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\NewUsers.xlsx");

			wb = new XSSFWorkbook(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sheet = wb.getSheet(sheet1);

		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();

		Row row = sheet.getRow(0);

		int lastCellNum = row.getLastCellNum();

		String[][] data = new String[physicalNumberOfRows-1][lastCellNum];

		for(int i=1; i<=data.length;i++) {

			data[i-1][0] = cell(i,0);
			data[i-1][1] = cell(i,1);
			data[i-1][2] = cell(i,2);
			data[i-1][3] = cell(i,3);
			data[i-1][4] = cell(i,4);
			data[i-1][5] = cell(i,5);
			data[i-1][6] = cell(i,6);
			data[i-1][7] = cell(i,7);
			data[i-1][8] = cell(i,8);
			data[i-1][9] = cell(i,9);
			data[i-1][10] = cell(i,10);
			data[i-1][11] = cell(i,11);
			data[i-1][12] = cell(i,12);
			data[i-1][13] = cell(i,13);
			data[i-1][14] = cell(i,14);
			data[i-1][15] = cell(i,15);
			data[i-1][16] = cell(i,16);

		}

		return data;

	}


	public static String cell(int row, int column) {

		String value;

		Row row2 = sheet.getRow(row);

		Cell cell = row2.getCell(column);

		int cellType = cell.getCellType();


		if(cellType==1) {

			value = cell.getStringCellValue();

		} else if(DateUtil.isCellDateFormatted(cell)) {

			Date dateCellValue = cell.getDateCellValue();

			SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");

			value= s.format(dateCellValue);


		} else {

			double numericCellValue = cell.getNumericCellValue();

			long l =(long)numericCellValue;

			value = String.valueOf(l);


		}


		return value;

	}


	public static void JVMReport(String json) {

		File f = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Reports\\JVM.html");

		Configuration conf = new Configuration(f, "JVMReport");

		conf.getProjectName();

		conf.addClassifications("UserName", System.getProperty("user.name"));

		List<String> li = new ArrayList();

		li.add(json);

		ReportBuilder r =  new ReportBuilder(li, conf);

		r.generateReports();

	}

	

	public static void getUserAccount() throws FileNotFoundException, com.fasterxml.jackson.core.JsonProcessingException {


		Usertype us = new Usertype();

		us.setUsertype("Women");

		Category c = new Category();

		c.setUsertype(us);

		c.setCategory("Tops");

		List li = new ArrayList();

		Products pr = new Products();

		pr.setCategory(c);

		pr.setBrand("Polo");

		pr.setId(2);

		pr.setPrice("Rs. 500");

		pr.setName("Blue Top");


		Products pr1 = new Products();

		pr1.setCategory(c);

		pr1.setBrand("Polo");

		pr1.setId(2);

		pr1.setPrice("Rs. 500");

		pr1.setName("Blue Top");


		li.add(pr);

		li.add(pr1);

		Parents par = new Parents();

		par.setProducts(li);

		par.setResponseCode(200);


		ObjectMapper ob = new ObjectMapper();

		String write = ob.writeValueAsString(par);


//		PrintWriter p = new PrintWriter(System.getProperty("user.dir")+"\\src\\test\\resources\\Reports\\new.json");
//
//		p.write(write);
//
//		p.flush();


	}

}
