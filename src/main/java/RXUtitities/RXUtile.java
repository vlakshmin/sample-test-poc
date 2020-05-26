package RXUtitities;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import RXBaseClass.RXBaseClass;


public class RXUtile extends RXBaseClass {

	public static long PAGELOAD_TIME = 50;
	public static long IMPLI_WAIT = 30;
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

	public static int getRandomNumberFourDigit()
	{
		Random rand = new Random();
		return(rand.nextInt(1000));
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
				flag=false;
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
				}
			}
			return null;

		}
	
	// Screenshot function 
		
		public static String takeScreenshotAtEndOfTest() throws IOException {
			final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path=System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";
			
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
	
	
	
}
