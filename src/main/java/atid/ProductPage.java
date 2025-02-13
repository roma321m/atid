package atid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ProductPage {
	private WebDriver driver;
	private Logger logger;

	private By addToCart = By.xpath("//*[@id=\"product-160\"]/div[2]/form/button");
	private By addedMSG = By.xpath("//*[@id=\"main\"]/div/div[1]/div");
	private By count = By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div[2]/div[2]/form/div/input");
	private By priceSlider = By.xpath("//*[@id=\"woocommerce_price_filter-2\"]/form/div/div[1]");
	private By minPriceSlider = By.xpath("//*[@id=\"woocommerce_price_filter-2\"]/form/div/div[1]/span[1]");
	private By maxPriceSlider = By.xpath("//*[@id=\"woocommerce_price_filter-2\"]/form/div/div[1]/span[2]");
	private By filterBTN = By.xpath("//*[@id=\"woocommerce_price_filter-2\"]/form/div/div[2]/button");
	private By singleMSG = By.xpath("//*[@id=\"main\"]/div/p");
	private By singleItem = By.xpath("//*[@id=\"main\"]/div/ul/li");
	private By allItems = By.xpath("//*[@id=\"main\"]/div/p");
	private By accessoriesBTN = By.xpath("/html/body/div/div[1]/div/div[1]/div/div[3]/ul/li[1]/a");
	private By menBTN = By.xpath("/html/body/div/div[1]/div/div[1]/div/div[3]/ul/li[2]/a");
	private By womenBTN = By.xpath("/html/body/div/div[1]/div/div[1]/div/div[3]/ul/li[3]/a");
	private By allCategoryTitle = By.xpath("/html/body/div/div[1]/div/div[2]/main/div/nav[1]");
	private By categoryTitle = By.xpath("/html/body/div/div[1]/div/div[2]/main/div/header/h1");

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		logger = LogManager.getLogger(ProductPage.class);
		logger.info("product page opened");
	}

	public void clickAddToCartBTN() {
		driver.findElement(addToCart).click();
		logger.debug("add to cart clicked");
	}

	public boolean checkAddToCart() {
		if (driver.findElement(addedMSG).isDisplayed()) {

			String str = driver.findElement(addedMSG).getText();
			String arr[] = str.split("�");
			logger.debug("�" + arr[1]);
			return true;
		} else {
			logger.debug("The item did not added to card");
			return false;
		}
	}

	public int getAddToCartCount() {
		if (driver.findElement(addedMSG).isDisplayed()) {
			String str = driver.findElement(addedMSG).getText();
			try {
				String arr[] = str.split(" ");
				String arr2[] = arr[1].split("\n");
				logger.debug("The item added to the cart " + arr2[1] + " times");
				return Integer.parseInt(arr2[1]);
			} catch (Exception e) {
				return -1;
			}
		} else {
			logger.debug("The item did not added to card");
			return -1;
		}
	}

	public void setCount(int number) {
		driver.findElement(count).clear();
		driver.findElement(count).sendKeys(number + "");
	}

	public boolean checkAlertShowing() {
		try {
			driver.switchTo().activeElement().findElement(count);
			logger.debug("The input is not valid");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void movePriceFilterToMin() {
		WebElement slider = driver.findElement(maxPriceSlider);
		WebElement priceSlider1 = driver.findElement(priceSlider);

		Actions SliderAction = new Actions(driver);
		SliderAction.clickAndHold(slider).moveByOffset(-priceSlider1.getSize().getWidth(), 0).moveByOffset(0, 0)
				.release().perform();
		logger.debug("price filter moved to min");
	}

	public void movePriceFilterToMax() {
		WebElement slider = driver.findElement(minPriceSlider);
		WebElement priceSlider1 = driver.findElement(priceSlider);

		Actions SliderAction = new Actions(driver);
		SliderAction.clickAndHold(slider).moveByOffset(priceSlider1.getSize().getWidth(), 0).moveByOffset(0, 0)
				.release().perform();
		logger.debug("price filter moved to max");
	}

	public void movePriceFilterToMid() {
		WebElement leftSlider = driver.findElement(minPriceSlider);
		WebElement rightSlider = driver.findElement(maxPriceSlider);
		WebElement priceSlider1 = driver.findElement(priceSlider);

		Actions SliderAction = new Actions(driver);
		SliderAction.clickAndHold(leftSlider).moveByOffset(priceSlider1.getSize().getWidth() / 2 - 10, 0)
				.moveByOffset(0, 0).release().perform();
		SliderAction.clickAndHold(rightSlider).moveByOffset(-priceSlider1.getSize().getWidth() / 2, 0)
				.moveByOffset(0, 0).release().perform();
		logger.debug("price filter moved to middle");
	}

	public void clickFilterBTN() {
		driver.findElement(filterBTN).click();
		logger.debug("filter button clicked");
	}

	public boolean checkSingleMSG() {
		if (driver.findElement(singleMSG).isDisplayed()) {
			logger.debug(driver.findElement(singleMSG).getText());
			return true;
		} else {
			logger.debug("filter failed");
			return false;
		}
	}

	public boolean checkSingleItem() {
		if (driver.findElement(singleItem).isDisplayed()) {
			logger.debug("\n" + driver.findElement(singleItem).getText());
			return true;
		} else {
			logger.debug("filter failed");
			return false;
		}
	}

	public boolean checkAllItems() {
		if (driver.findElement(allItems).isDisplayed()) {
			logger.debug(driver.findElement(allItems).getText());
			return true;
		} else {
			logger.debug("filter failed");
			return false;
		}
	}

	public void clickAccessoriesBTN() {
		driver.findElement(accessoriesBTN).click();
		logger.debug("filter by Accessories clicked");
	}

	public void clickMenBTN() {
		driver.findElement(menBTN).click();
		logger.debug("filter by Men clicked");
	}

	public void clickWomenBTN() {
		driver.findElement(womenBTN).click();
		logger.debug("filter by Women clicked");
	}

	public boolean checkCategoryTitle(String title) {
		if (driver.findElement(categoryTitle).isDisplayed()
				&& driver.findElement(categoryTitle).getText().equals(title)) {
			logger.debug(driver.findElement(categoryTitle).getText());
			return true;
		} else {
			logger.debug("filter failed");
			return false;
		}
	}
	
	public boolean checkAllTitle() {
		if (driver.findElement(allCategoryTitle).isDisplayed() &&
				driver.findElement(allCategoryTitle).getText().equals("Home / Store")) {
			logger.debug(driver.findElement(allCategoryTitle).getText());
			return true;
		} else {
			logger.debug("filter failed");
			return false;
		}
	}
}
