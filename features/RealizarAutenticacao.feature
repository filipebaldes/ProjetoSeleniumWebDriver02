#language: pt
#encoding: iso-8859-1

Funcionalidade: Realizar Autentica��o
	como um usu�rio da loja de livros
	eu quero acessar minha conta
	de modo que eu possa comprar livros

Cen�rio: Autenticar usu�rio com sucesso
	Dado Acessar a p�gina de autentica��o de usu�rio
	E Informar o email de acesso "sergio.coti@gmail.com"
	E Informar a senha de acesso "adminadmin"
	Quando Solicitar o acesso ao sistema
	Ent�o Sistema autentica o usu�rio com sucesso

Cen�rio: Acesso negado de usu�rio
	Dado Acessar a p�gina de autentica��o de usu�rio
	E Informar o email de acesso "teste@gmail.com"
	E Informar a senha de acesso "teste123"
	Quando Solicitar o acesso ao sistema
	Ent�o Sistema exibe a mensagem "Usu�rio ou Senha Inv�lido."

Cen�rio: Valida��o de campos obrigat�rios
	Dado Acessar a p�gina de autentica��o de usu�rio
	Quando Solicitar o acesso ao sistema
	Ent�o Sistema informa que email e senha s�o campos obrigat�rios