import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class AllTests extends Megatest {

    private final SelenideElement citilinkBtnVhod = $x(" //div[@class=\"HeaderMenu__button HeaderMenu__button_auth IconAndTextWithCount_mainHeader js--HeaderMenu__button_auth IconAndTextWithCount js--IconAndTextWithCount\"]");
    private final SelenideElement citiGorodViborBtn = $x("//button[@class=\"js--CitiesSearch-trigger MainHeader__open-text TextWithIcon\"]");
    private final SelenideElement HVALINSK = $x("//span[@class=\"CitiesSearch__highlight\"]");
    private final SelenideElement inputGorod = $x("//input[@class=\" InputBox__input js--InputBox__input js--CitiesSearch__input  InputSearch__container-input\"]");
    private final SelenideElement provHval = $x("//button[@class=\"js--CitiesSearch-trigger MainHeader__open-text TextWithIcon\"]");
    private final SelenideElement citiInputPhon = $x("//input[@class=\" InputBox__input js--InputBox__input  js--SignIn__login__container-input\"]");
    private final SelenideElement citiInputPass = $x("//input[@class=\" InputBox__input js--InputBox__input  js--SignIn__password js--InputPassword InputPassword__container-input\"]");


    private final SelenideElement citiVhodBtn = $x("//button[@class=\"SignIn__button js--SignIn__action_sign-in  Button  jsButton Button_theme_primary Button_size_m Button_full-width\"]");
    private final SelenideElement citiError = $x("//div[@class=\"LoginPageLayout__error-message\"]");
    private final SelenideElement citiKatalogBtn = $x("//button[@class=\"js--PopupCatalogMenu__button-open PopupCatalogMenu__button-open  Button  jsButton Button_theme_primary-transparent Button_size_m Button_with-icon\"]");
    private final ElementsCollection citilinkKatalog = $$x("//div[@class='CatalogMenu__subcategory-item']//a[@class='CatalogMenu__subcategory-link']");
    private final SelenideElement citiCapcha = $x("//input[@class=\" InputBox__input js--InputBox__input  CaptchaMic__input__container-input\"]");
    private final ElementsCollection citilinkKatalogList = $$x("//div[@class=\"CatalogMenu__category js--CatalogMenu__category ps\"]");
    private final SelenideElement citiKrasota = $x(".//a[@data-title=\"Красота и здоровье\"]");
    private final SelenideElement citiBrushBtn = $x("//a[@data-title=\"Зубные щетки\"]");


    private final ElementsCollection FilterMax = $$x("//input[@name=\"input-max\"]");
    private final ElementsCollection FilterMin = $$x("//input[@name=\"input-min\"]");
    private final SelenideElement citiDiapText = $x("//p[@class=\"FilterTags__name js--FilterTags__name\"]");
    private final ElementsCollection citiBrushes = $$x("//span[@class=\" IconFont IconFont_size_m IconFont_cart_add\"]");
    private final SelenideElement citiBrushe = $x("//a[@class=\" ProductCardVertical__name  Link js--Link Link_type_default\"]");
    private final ElementsCollection citiCorzina = $$x("//button[@data-label=\"В корзину\"]");
    private final SelenideElement citiIntoCorzina = $x("//button[@data-label=\"Перейти в корзину\"]");
    private final SelenideElement citiFinalOrderPrice = $x("//span[@class=\"OrderFinalPrice__price-current_current-price js--OrderFinalPrice__price-current_current-price \"]");
    private final SelenideElement citiProductPrice = $x("//span[@class=\"ProductCardForBasket__price-current_current-price js--ProductCardForBasket__price-current_current-price \"]");


    @Test
    public void LeraCitilinkHvalinsk() { //проверка хвалынска
        Selenide.open("https://www.citilink.ru/");
        citiGorodViborBtn.click();
        inputGorod.setValue("Хвалынск");
        HVALINSK.click();
        System.out.println(provHval.getText());
        Assert.assertTrue(provHval.getText().contains("Хвалынск"));

    }

    @Test
    public void LeraCitilinkVhod(){ //проверка на неклибальноть кнопки входа при некорректных входных
        Selenide.open("https://www.citilink.ru/");
        citilinkBtnVhod.click();
        citiInputPhon.setValue("888");
        citiInputPass.setValue("cheburashka&gena");
        Assert.assertFalse(citiVhodBtn.shouldBe(Condition.visible, Duration.ofSeconds(3)).isEnabled()); //, Duration.ofSeconds(5)
    }


    @Test
    public void LeraToothbrush(){ //зубыне щетки
        Selenide.open("https://www.citilink.ru/");
        citiKatalogBtn.click();
        actions().moveToElement(citiKrasota).perform(); //Перейти в каталог электрических зубных щеток
        citiBrushBtn.click();
        FilterMin.get(1).clear();
        FilterMin.get(1).setValue("999");
        FilterMax.get(1).clear();
        FilterMax.get(1).setValue("1999"); //выполнить поиск с диапазоном цен от 999 до 1999 рублей
        FilterMax.get(1).sendKeys(Keys.ENTER);

        citiDiapText.shouldHave(text("от 999 ₽ до 1 999 ₽"));
        Assert.assertTrue(citiDiapText.getText().contains("от 999 ₽ до 1 999 ₽"));

        citiBrushe.click(); //Добавить предпоследнюю щетку в коризину
        citiCorzina.get(1).click(); //перейти в нее
        citiIntoCorzina.click();
        Assert.assertTrue(citiProductPrice.text().contains(citiFinalOrderPrice.text())); //сравнить цену в корзине и цену товара

    }

}
