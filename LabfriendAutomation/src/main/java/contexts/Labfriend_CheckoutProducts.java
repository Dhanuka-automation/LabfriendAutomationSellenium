package contexts;

import java.util.Properties;

import functions.LabFriend_CommonFun;

public class Labfriend_CheckoutProducts {

	private String sku;
	public String propertyFileName;
	public String folderPath;
	private String skuSingapore;

	public Labfriend_CheckoutProducts(String propertyFileName, String folderPath) {
		this.propertyFileName = propertyFileName;
		this.folderPath = folderPath;

		Properties properties = LabFriend_CommonFun.getPropertyData(propertyFileName, folderPath);
		sku = properties.getProperty("sku");
		skuSingapore = properties.getProperty("sku_for_singapore");
	}

	public String getSku() {
		
		return sku;
	}
	
    public String getSingaporeSku() {
		
		return skuSingapore;
	}

}
