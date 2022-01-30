package DemoTest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Practical_GenericUtility.BaseAnnotationClass;
import Practical_pomrepository.CreateLeadsPage;
import Practical_pomrepository.HomePage;
import Practical_pomrepository.LeadInfoPage;
import Practical_pomrepository.LeadPage;

public class CreateLeadTest extends BaseAnnotationClass {
	@Test(groups="smokeTest")
	public void createLead() throws Throwable
	{
		HomePage hp = new HomePage(driver);
	   	hp.getLeads().click();
		
		LeadPage lp = new LeadPage(driver);
		wLib.waitForElementToBeClickable(driver,lp.getCreateContact() );
		lp.getCreateContact().click();
		
		String lastname = eLib.getDataFromExcel("Lead", 1, 2)+jLib.getRandomNum();
		String company = eLib.getDataFromExcel("Lead", 1, 3)+jLib.getRandomNum();
		
		CreateLeadsPage cp = new CreateLeadsPage(driver);
		cp.createLead(lastname, company);
		
		LeadInfoPage ln = new LeadInfoPage(driver);
		String actMsg = ln.getSuccessMsg().getText();
		System.out.println(actMsg);
		Assert.assertTrue(actMsg.contains(lastname));
		
	}
  
	@Test(groups="regressionTest")
	public void createLeadType() throws Throwable
	{
		HomePage hp = new HomePage(driver);
		hp.getLeads().click();
		
		LeadPage lp = new LeadPage(driver);
		wLib.waitForElementToBeClickable(driver,lp.getCreateContact() );
		lp.getCreateContact().click();
		
		String lastname = eLib.getDataFromExcel("Lead", 4, 2)+jLib.getRandomNum();
		String company = eLib.getDataFromExcel("Lead", 4, 3)+jLib.getRandomNum();
		String leadsrc = eLib.getDataFromExcel("Lead", 4, 4);
		
		CreateLeadsPage cp = new CreateLeadsPage(driver);
		cp.createLead(lastname, company,leadsrc);
		
		LeadInfoPage ln = new LeadInfoPage(driver);
		String actMsg = ln.getSuccessMsg().getText();
		System.out.println(actMsg);
		Assert.assertTrue(actMsg.contains(lastname));
		
	}
}
