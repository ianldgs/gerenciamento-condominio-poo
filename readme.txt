O trabalho consiste em criar um sistema para gerenciamento de condomínios com as seguintes
funcionalidades:

1. Login no sistema (usuário e senha);

2. Cadastro de Morador, possibilitando informar o número do apartamento (Cadastrar, consultar, excluir e atualizar)

3. Uma tela permitindo o síndico informar o somatório das despesas do mês/ano 
(Apenas o total do mês). 

a. Ao confirmar o somatório, o valor do condomínio deverá ser rateado(dividido) entre os condôminos.

4. A confirmação do pagamento será feita por meio de um arquivo texto, gerado manualmente. O sistema deve ler o arquivo e dar baixa no pagamento do morador no determinado mês e ano. Segue abaixo o layout do arquivo.

CNPJ do Condomínio
14 Dígitos 

CPF do Condômino
11 Dígitos 

Mês e Ano
6 Dígitos 

Valor Pago
8 Dígitos 

74713443000166 88360130663 092014 00030059 
Arquivo completo 

747134430001668836013066309201400030059 

A aplicação NÃO gera este arquivo, ele seria gerado por “instituições financeiras”.

5. Uma tela de consulta para verificar os pagamentos em um determinado mês/ano.