package com.java.Utility;


public class ObjRepo {
	//public final String SingleFrame = "//input[@type='text']";
	public static final String FrameTextBox = constants.xpath+"&"+"//input[@type='text']";
	public static final String FrameWithInFrame = constants.xpath+"&"+"//a[contains(text(),'in an Iframe')";
	public static final String submitbuton = constants.Name+"&"+"bSubmit";
	public static final String QTY_BACKPACKS = constants.Name+"&"+"QTY_BACKPACKS";
	public static final String MuntipleFrames = constants.xpath+"&"+"//iframe[@src='MultipleFrames.html']";
	public static final String Single_Frame = constants.xpath+"&"+"//iframe[@src='SingleFrame.html']";
	public static final String WebTableAllLastNames = constants.xpath+"&"+"//table[@id='example']/tbody/tr/td[3]";
	
	public static final String MouseOperationRightClick = constants.xpath+"&"+"//span[contains(text(),'right click me')]";
	public static final String MouseOperationRCPaste = constants.xpath+"&"+"//*[@class='context-menu-list context-menu-root']/li[4]/span";
	
	public static final String MouseOperationframe = constants.tagName+"&"+"iframe";
	public static final String MouseOperationDoubleclickbox = constants.xpath+"&"+"//span[contains(text(),'Double click the block')]/preceding-sibling::div";
	
	public static final String MouseOperationSource = constants.ID+"&"+"draggable";
	public static final String MouseOperationTarget = constants.ID+"&"+"droppable";
	public static final String links = constants.tagName+"&"+"a";
	public static final String FileUpload = constants.xpath+"&"+"//input[@id='input-4']/preceding-sibling::span";
	public static final String FileUpload1 = constants.xpath+"&"+"/html/body/section/div[1]/div/div/div[1]/div[3]/span";
	public static final String FileDownload100kb = constants.xpath+"&"+"//a[@download='file-sample_100kB.doc']";
	
	public static final String DataDivenFirstName = constants.xpath+"&"+"//input[@placeholder='First Name']";
	public static final String DataDivenLastName = constants.xpath+"&"+"//input[@placeholder='Last Name']";
	public static final String DataDivenAddress = constants.xpath+"&"+"//textarea[@ng-model='Adress']";
	public static final String DataDivenEmail = constants.xpath+"&"+"//input[@type='email']";
	public static final String DataDivenTelephone = constants.xpath+"&"+"//input[@type='tel']";
	public static final String DataDivenGenderMale = constants.xpath+"&"+"//input[@value='Male']";
	public static final String DataDivenGenderFeMale = constants.xpath+"&"+"//input[@value='FeMale']";
	public static final String DataDivenHobbiesCricket = constants.ID+"&"+"checkbox1";
	public static final String DataDivenHobbiesMovies = constants.ID+"&"+"checkbox2";
	public static final String DataDivenHobbiesHockey = constants.ID+"&"+"checkbox3";
	public static final String DataDivenLanguage = constants.ID+"&"+"msdd";
	public static final String DataDivenList_languages = constants.xpath+"&"+"//div[@id='msdd']/following-sibling::div/ul/li/a";
	
	public static final String DataDrivenskillslabel = constants.xpath+"&"+"//label[contains(text(),'Skills')]";
	public static final String DataDivenSkills = constants.ID+"&"+"Skills";
	public static final String DataDiven_AllSkills = constants.xpath+"&"+"//select[@id='Skills']/option";
	
	public static final String DataDrivenCountry= constants.ID+"&"+"countries";
	public static final String DataDrivenSelectCntry= constants.xpath+"&"+"//span[@role='combobox']";
	public static final String DataDrivenAllCntries= constants.xpath+"&"+"	//*[@id='select2-country-results']/li";

	public static final String DataDrivenTextAreacountry= constants.xpath+"&"+"//input[@type='search']";
	
	
	public static final String DataDrivenDOB_YY= constants.xpath+"&"+"//select[@placeholder='Year']";
	public static final String DataDrivenAllYears = constants.xpath + "&"+ "//*[@id='yearbox']/option";
	
	
	public static final String DataDrivenDOB_MM= constants.xpath+"&"+"//select[@placeholder='Month']";
	public static final String DataDrivenAllMonths= constants.xpath+"&"+"//select[@placeholder='Month']/option";
	
	public static final String DataDrivenDOB_DD= constants.xpath+"&"+"//select[@placeholder='Day']";
	public static final String DataDrivenAllDays= constants.xpath+"&"+"//select[@id='daybox']/option";
	
	public static final String DataDrivenPwd= constants.ID+"&"+"firstpassword";
	public static final String DataDrivenConfirmPassword= constants.ID+"&"+"secondpassword";
	
	public static final String DataDrivencloseIconLanguage= constants.xpath+"&"+"//span[@class='ui-icon ui-icon-close']";
	
	
	
