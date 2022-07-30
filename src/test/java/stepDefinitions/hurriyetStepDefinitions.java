package stepDefinitions;
import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;
import pages.HurriyetPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.Set;
public class hurriyetStepDefinitions   {
    Actions actions=new Actions(Driver.getDriver());
    Faker faker=new Faker();
    HurriyetPage hp=new HurriyetPage();
    SoftAssert softAssert=new SoftAssert();
    String yeniAd=        faker.name().firstName();
    String yeniSoyAd=        faker.name().lastName();


    @Given("kullanici {string} sitesine gider")
    public void kullanici_sitesine_gider(String string) {
        Driver.getDriver().get(ConfigReader.getProperty("hurriyetUrl"));
    }
    @Then("kullanici login yapar")
    public void kullanici_login_yapar() {
        hp.girisButonu.click();
        hp.kullaniciAdiInput.sendKeys(ConfigReader.getProperty("hurriyetEmail"));
        hp.sifreInput.sendKeys(ConfigReader.getProperty("hurriyetSifre"));
        hp.girisYapButonu.click();

    }
    @And("kullanici hesabina gider")
    public void kullaniciHesabinaGider() throws InterruptedException {


        hp.hesabim.click();
        String ilkSayfaWindowHandle= Driver.getDriver().getWindowHandle();
        hp.profiliGor.click();//yeni sekmeye acildi driver ilk sekmede kaldi
        Set<String> windowHandleSeti=Driver.getDriver().getWindowHandles();//ikinci sekmenin WindowHandle'i alınıyor...
        String ikinciSayfaWindowHandle="";
        for (String each: windowHandleSeti){
            if(!each.equals(ilkSayfaWindowHandle)){
                ikinciSayfaWindowHandle=each;////ikinci sekmenin WindowHandle'i alindi.
            }
        }
        Driver.getDriver().switchTo().window(ikinciSayfaWindowHandle);//ikinci sekmeye gecildi
        hp.kullaniciyaGit.click();
    }
    @And("uyelik bilgilerine tiklar")
    public void uyelikBilgilerineTiklar() {
        ReusableMethods.waitForVisibility(hp.uyelikBilgileri,10);//en az 10 saniye bekler gelince beklemeyi durdurur isleme gecer
        hp.uyelikBilgileri.click();
        ReusableMethods.waitFor(2);
    }
    @And("bilgileri degistirmek icin duzenleye tiklar")
    public void bilgileriDegistirmekIcinDuzenleyeTiklar() {
        hp.uyelikBilgileriDuzenle.click();
        ReusableMethods.waitFor(2);
    }
    @And("kullanici  bilgileri degistirir")
    public void kullaniciBilgileriDegistirir() throws InterruptedException {
        hp.ad.clear();
        hp.ad.sendKeys(yeniAd);
        hp.soyAd.clear();
        hp.soyAd.sendKeys(yeniSoyAd);
        hp.degisiklikleriKaydet.click();
    }
    @When("bilgilerinin degistigini dogrular")
    public void bilgilerininDegistiginiDogrular() throws InterruptedException {
        Driver.getDriver().navigate().refresh();
        ReusableMethods.waitFor(1);
        ReusableMethods.waitForVisibility(hp.uyelikBilgileri,10);//en az 10 saniye bekler gelince beklemeyi durdurur isleme gecer
        hp.uyelikBilgileri.click();
        ReusableMethods.waitForVisibility(hp.uyelikBilgileriDuzenle,10);
        hp.uyelikBilgileriDuzenle.click();
        ReusableMethods.waitForVisibility(hp.ad,10);
        String actualName=hp.ad.getAttribute("value");
        String expectedName=yeniAd;
        String actualLastName=hp.soyAd.getAttribute("value");
        String expectedLastName=yeniSoyAd;
        softAssert.assertEquals(actualName,expectedName,"kullanicinin ad degisikligi basarisiz olmustur");
        softAssert.assertEquals(actualLastName,expectedLastName,"kullanicinin soyad degisikligi basarisiz olmustur");
        softAssert.assertAll("\nBasarisiz bir test gerceklesti simdi sunlari yapacaksin: \n1) Kodlarda mi bir sikinti var yoksa yoksa ikiye gec" +
                " \n2)Manuel Test Yap eger gercekse bunu raporla" +
                "\n Manuel bir sekilde test yapildi kullanici saglikli sekilde bilgilerini guncelleyemiyor");
    }
}
