> <h1>To do web app</h1>
 > Status: desenvolvendo.</br>
![Header](https://user-images.githubusercontent.com/44982114/218023185-ff4ba696-f054-48d4-8891-ef978ea5057b.png)

</br>

 > <h2>Introdução:</h2>

 Aplicativo de tarefas onde o usuario pode realizar o CRUD de tarefas e Cadastro de usuarios</br>
 
 O modelo de tarefa possui os seguintes atributos:</br>
   <ul>
    <li>id</li>
    <li>titulo</li>
    <li>descrição</li>
    <li>data para finalizar</li>
    <li>finalizado</li>
    <li>user</li>
   </ul></br>
 O modelo de Usuario possui os seguintes atributos:</br>
 <ul>
    <li>id</li>
    <li>userName</li>
    <li>password</li>
    <li>email</li>
    <li>todos</li>
   </ul></br>
 
 ![image](https://user-images.githubusercontent.com/44982114/219597688-85d59260-10dd-454e-a64c-a14ff9a58f0b.png)


 </br>
 
 > <h2>Instalação:</h2>
  
 <ul>
    <li>
      <h3>Back-end:</h3>
        * Abra a pasta backend em sua ide.</br>
        * Configure a JDK (11 ou superior).</br>
        * Baixe as dependencias contidas no arquivo pom.xml.</br>
        * Execute a Aplicação. (Será executada na porta 8080)</br>
    </li>
    <li>
      <h3>Front-end:</h3>
        * Abra a pasta frontend em sua ide.</br>
        * Baixe as dependencias contidas no arquivo package.json. (execute o comando "npm install")</br>
        * Execute a Aplicação. (Será executada na porta 4200)</br>
   </li>
 </ul>
 
 </br>
 
 > <h2>Tecnologias Utilizadas:</h2>
   Aplicação web construida com base no padrao de arquitetura MVC com tecnicas como design patterns, SOLID e clean code.</br>
   
  <ul>
    <li><h3>No back-end(APIRest):</h3>
        Utilizando spring boot 2.7.8 com JAVA 11, lombok, jpa, banco de dados H2 e swagger 3.0.
    </li>
    <li><h3>No Front-end(SPA):</h3>
       Utilizando Angular 14 e a biblioteca do Angular Material.
    </li>
  </ul>
