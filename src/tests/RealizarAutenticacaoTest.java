package tests;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Ent�o;
import cucumber.api.java.pt.Quando;
public class RealizarAutenticacaoTest {
	
	WebDriver driver; //Selenium WebDriver
	@Dado("^Acessar a p�gina de autentica��o de usu�rio$")
	public void acessar_a_p�gina_de_autentica��o_de_usu�rio() {
	//definindo o local onde est� o driver utilizado para abrir o navegador
	System.setProperty("webdriver.chrome.driver", "c:\\drivers\\chromedriver.exe");
	
	//abrindo o navegador
	driver = new ChromeDriver();
	
	//maximizando o navegador
	driver.manage().window().maximize();
	
	//acessar a p�gina de login de usu�rios
	driver.get("https://lojaexemplod.lojablindada.com/customer/account/login/");
	}
	
	@Dado("^Informar o email de acesso \"([^\"]*)\"$")
	public void informar_o_email_de_acesso(String email) {
	//localizar o campo email no formul�rio e preenche-lo
	driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
	}
	
	@Dado("^Informar a senha de acesso \"([^\"]*)\"$")
	public void informar_a_senha_de_acesso(String senha) {
	//localizar o campo senha no formul�rio e preenche-lo
	driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(senha);
	}
	
	@Quando("^Solicitar o acesso ao sistema$")
	public void solicitar_o_acesso_ao_sistema() {
	//clicar no bot�o 'Entrar']
	driver.findElement(By.xpath("//*[@id=\"send2\"]")).click();
	}
	
	@Ent�o("^Sistema autentica o usu�rio com sucesso$")
	public void sistema_autentica_o_usu�rio_com_sucesso() {
	//CRIT�RIO: Verificar se o usuario esta
	//na pagina de acesso restrito
	//obter a URL da pagina para o qual
	//o usuario foi redirecionado..
	String urlpagina = driver.getCurrentUrl();
	//comparando se a p�gina para o qual o usuario foi redirecionado
	//� igual a https://lojaexemplod.lojablindada.com/customer/account/
	assertEquals(urlpagina, "https://lojaexemplod.lojablindada.com/customer/account/");
	//fechando o navegador
	driver.close();
	}
	
	@Ent�o("^Sistema exibe a mensagem \"([^\"]*)\"$")
	public void sistema_exibe_a_mensagem(String mensagem) {
	//CRIT�RIO: Verificar se o sistema exibe
	//a mensagem 'Usu�rio ou Senha Inv�lido.'
	//ler a mensagem exibida pelo sistema
	String resultadoObtido = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/div/ul/li/ul/li/span")).getText();
	//verificando se o resultado obtido � igual
	//a mensagem definida na feature
	assertEquals(resultadoObtido, mensagem);
	//fechando o navegador
	driver.close();
	}
	
	@Ent�o("^Sistema informa que email e senha s�o campos obrigat�rios$")
	public void sistema_informa_que_email_e_senha_s�o_campos_obrigat�rios() {
	//CRIT�RIO: Verificar se o sistema informa que
	//os campos email e senha
	//s�o de preenchimento obrigat�rio
	//capturando as mensagens de erro de cada campo..
	String mensagemErroEmail = driver.findElement(By.xpath("//*[@id=\"advice-required-entry-email\"]")).getText();
	
	String mensagemErroSenha = driver.findElement(By.xpath
	("//*[@id=\"advice-required-entry-pass\"]")).getText();
	//comparando se cada mensagem � igual a:
	//'Este � um campo obrigat�rio.'
	assertEquals(mensagemErroEmail, "Este � um campo obrigat�rio.");
	assertEquals(mensagemErroSenha, "Este � um campo obrigat�rio.");
	//fechando o navegador
	driver.close();
	}
	}