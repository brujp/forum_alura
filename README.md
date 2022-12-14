# API Fórum da Alura - Curso Spring Boot API REST: construa uma API

API Desenvolvida durante o curso de Spring Boot API Rest na Alura 👾

 ✍🏻 Anotações importantes

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

Trabalhando com POST:

- Para evitar repetir a URL em todos os métodos, devemos utilizar a anotação @RequestMapping em cima da classe controller;

- Para mapear requisições do tipo POST, devemos utilizar a anotação @PostMapping;

- Para receber dados enviados no corpo da requisição, a boa prática é criar uma classe que também siga o padrão DTO (Data Transfer Object);

- Boa prática para métodos que cadastram informações é devolver o código HTTP 201, ao invés do código 200;

- Para montar uma resposta a ser devolvida ao cliente da API, devemos utilizar a classe ResponseEntity do Spring;

- Para testar requisições do tipo POST, precisamos utilizar alguma ferramenta de testes de API Rest;

Validação com Bean Validation:

- Para fazer validações das informações enviadas pelos clientes da API, podemos utilizar a especificação Bean Validation, com as anotações @NotNull, @NotEmpty, @Size, dentre outras;

- Para o Spring disparar as validações do Bean Validation e devolver um erro 400, caso alguma informação enviada pelo cliente esteja inválida, devemos utilizar a anotação @Valid;

- Para interceptar as exceptions que forem lançadas nos métodos das classes controller, devemos criar uma classe anotada com @RestControllerAdvice;

- Para tratar os erros de validação do Bean Validation e personalizar o JSON, que será devolvido ao cliente da API, com as mensagens de erro, devemos criar um método na classe @RestControllerAdvice e anotá-lo com @ExceptionHandler e @ResponseStatus.

Métodos PUT, DELETE e tratamento de erro:

- Para receber parâmetros dinâmicos no path da URL, devemos utilizar a anotação @PathVariable;

- Para mapear requisições do tipo PUT, devemos utilizar a anotação @PutMapping;

- Para fazer o controle transacional automático, devemos utilizar a anotação @Transactional nos métodos do controller;

- Para mapear requisições do tipo DELETE, devemos utilizar a anotação @DeleteMapping;

- Para tratar o erro 404 na classe controller, devemos utilizar o método findById, ao invés do método getOne, e utilizar a classe ResponseEntity para montar a resposta de not found;

- O método getOne lança uma exception quando o id passado como parâmetro não existir no banco de dados;

- O método findById retorna um objeto Optional<>, que pode ou não conter um objeto.

Segurança da API, Cache e Monitoramento:

- Para realizar paginação com Spring Data JPA, devemos utilizar a interface Pageable;

- Nas classes Repository, os métodos que recebem um pageable como parâmetro retornam objetos do tipo Page<>, ao invés de List<>;

- Para o Spring incluir informações sobre a paginação no JSON de resposta enviado ao cliente da API, devemos alterar o retorno do método do controller de List<> para Page<>;

- Para fazer a ordenação na consulta ao banco de dados, devemos utilizar também a interface Pageable, passando como parâmetro a direção da ordenação, utilizando a classe Direction, e o nome do atributo para ordenar;

- Para receber os parâmetros de ordenação e paginação diretamente nos métodos do controller, devemos habilitar o módulo SpringDataWebSupport, adicionando a anotação @EnableSpringDataWebSupport na classe ForumApplication.

Melhorando desempenho com Spring Cache:

- Para utilizar o módulo de cache do Spring Boot, devemos adicioná-lo como dependência do projeto no arquivo pom.xml;

- Para habilitar o uso de caches na aplicação, devemos adicionar a anotação @EnableCaching na classe ForumApplication;

- Para que o Spring guarde o retorno de um método no cache, devemos anotá-lo com @Cacheable;

- Para o Spring invalidar algum cache após um determinado método ser chamado, devemos anotá-lo com @CacheEvict;

- Devemos utilizar cache apenas para as informações que nunca ou raramente são atualizadas no banco de dados.


