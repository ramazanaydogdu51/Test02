package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HurriyetPage {
    public HurriyetPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (xpath = "//a[@class='header__external--account hurriyet-premium-box']")
    public WebElement girisButonu;
    @FindBy (xpath = "//input[@id='signinMail']")
    public WebElement kullaniciAdiInput;
    @FindBy (xpath = "//input[@id='signinPassword']")
    public WebElement sifreInput;
    @FindBy (xpath = "//a[@id='sign-in-button']")
    public WebElement girisYapButonu;
    @FindBy (xpath = "//a[@class='header__external--account hurriyet-premium-box login']")
    public WebElement hesabim;
    @FindBy (xpath = "//a[normalize-space()='Profili Gör']")
    public WebElement profiliGor;
    @FindBy (xpath = "//span[@id='userLogin']")
    public WebElement kullaniciyaGit;
    @FindBy (xpath = "//span[normalize-space()='Üyelik Bilgileri']")
    public WebElement uyelikBilgileri;
    @FindBy (xpath = "//a[normalize-space()='Düzenle']")
    public WebElement uyelikBilgileriDuzenle;
    @FindBy (xpath = "//input[@name='FirstName']")
    public WebElement ad;
    @FindBy (xpath = "//input[contains(@name,'LastName')]")
    public WebElement soyAd;
    @FindBy (xpath = "//input[@value='DEĞİŞİKLİKLERİ KAYDET']")
    public WebElement degisiklikleriKaydet;

}
