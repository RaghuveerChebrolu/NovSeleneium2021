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
	
}
