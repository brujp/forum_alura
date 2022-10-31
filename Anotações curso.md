Curso de Spring Boot com Java:

Anotações importantes

- As classes de domínio (models) são as tabelas no banco de dados

- Para um método no controller não encaminhar a requisição a uma página JSP, ou Thymeleaf, 
devemos utilizar a anotação @ResponseBody;

- O Spring, por padrão, converte os dados no formato JSON, utilizando a biblioteca Jackson;

- Para não repetir a anotação @ResponseBody em todos os métodos do controller, 
devemos utilizar a anotação @RestController;

- Para não precisar reiniciar manualmente o servidor a cada alteração feita no código, 
basta utilizar o módulo Spring Boot DevTools;

- Não é uma boa prática retornar entidades JPA nos métodos dos controllers, sendo mais indicado 
retornar classes que seguem o padrão DTO (Data Transfer Object);

Repository:

- Boa prática é isolar o código que faz o acesso ao banco de dados em alguma outra classe e injetar
ela no Controller

