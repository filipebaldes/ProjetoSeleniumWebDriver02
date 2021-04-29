package tests;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
public class RealizarAutenticacaoTest {
	
	WebDriver driver; //Selenium WebDriver
	@Dado("^Acessar a página de autenticação de usuário$")
	public void acessar_a_página_de_autenticação_de_usuário() {
	//definindo o local onde está o driver utilizado para abrir o navegador
	System.setProperty("webdriver.chrome.driver", "c:\\drivers\\chromedriver.exe");
	
	//abrindo o navegador
	driver = new ChromeDriver();
	
	//maximizando o navegador
	driver.manage().window().maximize();
	
	//acessar a página de login de usuários
	driver.get("https://lojaexemplod.lojablindada.com/customer/account/login/");
	}
	
	@Dado("^Informar o email de acesso \"([^\"]*)\"$")
	public void informar_o_email_de_acesso(String email) {
	//localizar o campo email no formulário e preenche-lo
	driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
	}
	
	@Dado("^Informar a senha de acesso \"([^\"]*)\"$")
	public void informar_a_senha_de_acesso(String senha) {
	//localizar o campo senha no formulário e preenche-lo
	driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(senha);
	}
	
	@Quando("^Solicitar o acesso ao sistema$")
	public void solicitar_o_acesso_ao_sistema() {
	//clicar no botão 'Entrar']
	driver.findElement(By.xpath("//*[@id=\"send2\"]")).click();
	}
	
	@Então("^Sistema autentica o usuário com sucesso$")
	public void sistema_autentica_o_usuário_com_sucesso() {
	//CRITÉRIO: Verificar se o usuario esta
	//na pagina de acesso restrito
	//obter a URL da pagina para o qual
	//o usuario foi redirecionado..
	String urlpagina = driver.getCurrentUrl();
	//comparando se a página para o qual o usuario foi redirecionado
	//é igual a https://lojaexemplod.lojablindada.com/customer/account/
	assertEquals(urlpagina, "https://lojaexemplod.lojablindada.com/customer/account/");
	//fechando o navegador
	driver.close();
	}
	
	@Então("^Sistema exibe a mensagem \"([^\"]*)\"$")
	public void sistema_exibe_a_mensagem(String mensagem) {
	//CRITÉRIO: Verificar se o sistema exibe
	//a mensagem 'Usuário ou Senha Inválido.'
	//ler a mensagem exibida pelo sistema
	String resultadoObtido = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div/div/ul/li/ul/li/span")).getText();
	//verificando se o resultado obtido é igual
	//a mensagem definida na feature
	assertEquals(resultadoObtido, mensagem);
	//fechando o navegador
	driver.close();
	}
	
	@Então("^Sistema informa que email e senha são campos obrigatórios$")
	public void sistema_informa_que_email_e_senha_são_campos_obrigatórios() {
	//CRITÉRIO: Verificar se o sistema informa que
	//os campos email e senha
	//são de preenchimento obrigatório
	//capturando as mensagens de erro de cada campo..
	String mensagemErroEmail = driver.findElement(By.xpath("//*[@id=\"advice-required-entry-email\"]")).getText();
	
	String mensagemErroSenha = driver.findElement(By.xpath
	("//*[@id=\"advice-required-entry-pass\"]")).getText();
	//comparando se cada mensagem é igual a:
	//'Este é um campo obrigatório.'
	assertEquals(mensagemErroEmail, "Este é um campo obrigatório.");
	assertEquals(mensagemErroSenha, "Este é um campo obrigatório.");
	//fechando o navegador
	driver.close();
	}
	}