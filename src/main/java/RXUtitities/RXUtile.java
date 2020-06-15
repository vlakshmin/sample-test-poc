package RXUtitities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import RXBaseClass.RXBaseClass;


public class RXUtile extends RXBaseClass {

	public static long PAGELOAD_TIME = 200;
	public static long IMPLI_WAIT = 50;
	/*
	 * protected Configuration config=new Configuration(); ResultSet rs; Statement
	 * stmt;
	 * 
	 * public Statement createConnection(String host,String dbName,String
	 * username,String password) { try { host = config.getProperty("DBHOST"); dbName
	 * = config.getProperty("DBNAME"); username = config.getProperty("DBUSERNAME");
	 * password = config.getProperty("DBPASSWORD"); String dbUrl =
	 * "jdbc:mysql://"+host+"/"+dbName+"?rewriteBatchedStatements=true"; Connection
	 * con = DriverManager.getConnection(dbUrl,username,password); stmt =
	 * con.createStatement(); }catch (Exception e) { e.printStackTrace(); } return
	 * stmt; }
	 * 
	 * 
	 * public ResultSet executeSQL(String sqlQuery) {
	 * 
	 * try { String SQL = "SELECT TOP 10 * FROM Person.Contact"; rs =
	 * stmt.executeQuery(sqlQuery); } catch (SQLException e) { e.printStackTrace();
	 * } return rs; }
	 */
	// Handle any errors that may have occurred.
	int rownum = 1;

	public int getRandomNumberForPassword()
	{
		Random rand = new Random();
		return(rand.nextInt(50));
	}

//	public static int getRandomNumberFourDigit()
//	{
//		Random rand = new Random();
//		return(rand.nextInt(1000));
//	}

	public static String getRandomNumberFourDigit()
	{
		String emailChacter = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder eStr = new StringBuilder();
		Random rnd = new Random();
		while (eStr.length() < 5) { // length of the random string.
			int index = (int) (rnd.nextFloat() * emailChacter.length());
			eStr.append(emailChacter.charAt(index));
		}
		String emailStr = eStr.toString();
		return emailStr;
	}

	public String getEmailString() {
		String emailChacter = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder eStr = new StringBuilder();
		Random rnd = new Random();
		while (eStr.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * emailChacter.length());
			eStr.append(emailChacter.charAt(index));
		}
		String emailStr = eStr.toString()+"@testqar.com";
		return emailStr;

	}

	public int getTotalRowCount(WebElement elem) {
		String str = elem.getText();
		String[] srt = str.split(" ");
		return(Integer.parseInt(srt[2]));

	}

	// Back space for deleting the text value 
	public void clearTextBox(WebElement element) {

		int len = element.getAttribute("value").length();
		for(int i =0; i < len; i++) {
			element.sendKeys(Keys.BACK_SPACE);
		}
	}

	// Get the value of specified column.
	public ArrayList<WebElement> getParticularRowData(int pubId,int tableRows,int tableColumn,String coulmnNmeBfr,String coulmnNmeAft,String coulmnNmeLst,String frwBtn ) {

		//			String str = totalRows.getText();
		//			String[] srt = str.split(" ");
		//			totRow = Integer.parseInt(srt[2]);
		boolean flag=true;
		WebElement forWardBtn= driver.findElement(By.xpath(frwBtn));
		/*
		 * for(int i=0;i<=srt.length-1;i++) {
		 * System.out.println("Total Number of Rows "+srt[i]); }
		 */
		ArrayList<WebElement> pubElementvalues = new ArrayList<WebElement>();
		//								while(rownum <= totRow)
		while(flag == true)
		{
//			flag=false;
			for(int i=1;i<=tableRows;i++)
			{
				String actualColumnXpath=coulmnNmeBfr+i+coulmnNmeAft+2+coulmnNmeLst;
				WebElement columnValue= driver.findElement(By.xpath(actualColumnXpath));
				if(Integer.parseInt(columnValue.getText())== pubId)
				{
					for(int j=2;j<=tableColumn;j++) 
					{
						String checkStr=coulmnNmeBfr+i+coulmnNmeAft+j+coulmnNmeLst;
						WebElement checkClick= driver.findElement(By.xpath(checkStr));
						pubElementvalues.add(checkClick);


					}
					return pubElementvalues;
				}

			}
			if(forWardBtn.isEnabled())
			{
				flag = true;
				forWardBtn.click();
			}else if(!forWardBtn.isEnabled())
			{
				flag = false;
			}
		}
		return null;

	}

	// Screenshot function 

	public static String takeScreenshotAtEndOfTest() throws IOException {
		final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"target/Screenshot/"+System.currentTimeMillis()+".png";

		File destination=new File(path);

		try 
		{
			FileUtils.copyFile(scrFile, destination);
		} catch (IOException e) 
		{
			System.out.println("Capture Failed "+e.getMessage());
		}

		return path;
	}

	// Get the value of specified column.
	public int selectParticularRowData(int tableRows,int tableColumn,String coulmnNmeBfr,String coulmnNmeAft,String coulmnNmeLst,String frwBtn,int columnNum,String colmValue ) {

		boolean flag=true;
		WebElement forWardBtn= driver.findElement(By.xpath(frwBtn));

		while(flag == true)
		{
			flag=false;
			for(int i=1;i<=tableRows;i++)
			{
				String actualColumnXpath=coulmnNmeBfr+i+coulmnNmeAft+columnNum+coulmnNmeLst;
				WebElement columnValue= driver.findElement(By.xpath(actualColumnXpath));
				if(columnNum==2)
				{
					if(Integer.parseInt(columnValue.getText())== Integer.parseInt(colmValue))
					{
						String checkStr=coulmnNmeBfr+i+coulmnNmeAft+1+coulmnNmeLst;
						WebElement checkBox= driver.findElement(By.xpath(checkStr));
						checkBox.click();
						String checkStrBox=coulmnNmeBfr+i+coulmnNmeAft+2+coulmnNmeLst;
						return Integer.parseInt(driver.findElement(By.xpath(checkStrBox)).getText());
					}

				}else if(columnNum >= 3)
				{
					String tmpStr=columnValue.getText();

					if(tmpStr.equalsIgnoreCase(colmValue))
					{
						String checkStr=coulmnNmeBfr+i+coulmnNmeAft+1+coulmnNmeLst;
						WebElement checkBox= driver.findElement(By.xpath(checkStr));
						checkBox.click();
						String checkStrBox=coulmnNmeBfr+i+coulmnNmeAft+2+coulmnNmeLst;
						System.out.println("Column value :" + tmpStr);
						System.out.println("Column ID :" + driver.findElement(By.xpath(checkStrBox)).getText());
						return Integer.parseInt(driver.findElement(By.xpath(checkStrBox)).getText());
					}

				}

			}
			if(forWardBtn.isEnabled())
			{
				flag = true;
				forWardBtn.click();
			}
		}
		return (Integer) null;
	}
	
	//update the RXTestdata proertiies 
	public void updateTestData(String testDataName,String newValue)
	{
		try {
		String propFile = System.getProperty("user.dir") + "/src/main/java/RXConfig/RX.properties"; 
		  FileOutputStream out = new FileOutputStream(propFile);
		  prop.setProperty(testDataName,newValue);
		  prop.store(out, null); 
		  out.close();
	} 
	catch (FileNotFoundException e) 
	{
		e.printStackTrace();
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	}
	

	
}
