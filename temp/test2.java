package test;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.host.Iterator;
import com.gargoylesoftware.htmlunit.javascript.host.media.webkitMediaStream;


public class test2 {
	public static void main(String[] args) throws InterruptedException{
		 System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
		 WebDriver driver=new FirefoxDriver();//启动浏览器
		 driver.manage().window().maximize();//浏览器最大化
		 driver.get("http://120.27.227.212/login");//get网页
		 driver.manage().addCookie(new Cookie("JSESSIONID", "30645539F611E01BE848809BA8A769DF"));//调用方法获取session
		 driver.get("http://120.27.227.212/main");//用session登录后get首页
		 System.out.println("---------------------------------------------------------------");
		List<WebElement> list = driver.findElements(By.className("tree-node"));//用list取出所有classname为tree-node的标签
		 for(WebElement we:list){//循环取出list中的值来做判断
			//你想单击哪个
			 if(we.getText().equals("系统管理")){//如果文本值等于系统管理就单击
				 we.click();
				 Thread.currentThread().sleep(50);//线程休眠，防止代码运行过快导致不能正确操作
			 }
			 if(we.getText().equals("用户权限")){//如果文本值等于用户权限就单击
				 we.click();
				 Thread.currentThread().sleep(50);//线程休眠，防止代码运行过快导致不能正确操作
			 }
			 if(we.getText().equals("用户列表")){//如果文本值等于用户列表就单击
				 we.click();
				 Thread.currentThread().sleep(50);//线程休眠，防止代码运行过快导致不能正确操作
				 break;
			 }
		 }
		 WebDriverWait wait = new WebDriverWait(driver,10);    
	        wait.until(new ExpectedCondition<Boolean>() {  
	            public Boolean apply(WebDriver webDriver) {  
	                System.out.println("Searching ...");  
	                List<WebElement> frames = driver.findElements(By.tagName("iframe"));
	                for(WebElement frame:frames){
	                	String src = frame.getAttribute("src");
	                	if(src.endsWith("/sys/users")){
	                		webDriver.switchTo().frame(frame);
	                		return true;
	                	}
	                	//System.out.println(driver.getPageSource());
	                }
	                return webDriver.findElement(By.id("dg_fn_add")).getText().length() != 0;  
	            }  
	        });  
	        Thread.currentThread().sleep(5000);
		 WebElement xinzeng=driver.findElement(By.id("dg_fn_add"));//.findElement(By.tagName("span"));
		 System.out.println(xinzeng.getAttribute("class"));
		 System.out.println(xinzeng.getText());
		 xinzeng.click();
		/* List<WebElement> frames=driver.findElements(By.tagName("iframe"));
		 for(WebElement we:frames){
			 if(we.getAttribute("src").equals("/sys/users")){
				 controlPanelFrame = we;
                 break;
			 }
			 System.out.println(we.getText());
	     if(controlPanelFrame!=null){
	    	 driver.switchTo().frame(controlPanelFrame);
	     }
		 }
		 WebElement xinzeng=driver.findElement(By.id("dg_fn_add"));
		 System.out.println(xinzeng.getText());*/
		 /*List<WebElement> top=driver.findElements(By.className("l-btn"));
		 	for(WebElement we:top){
		 		if(we.getText().equals("新增")){
		 			we.click();
		 			break;
		 		}	 		
		 	}*/
		 //driver.quit();
	}
}
