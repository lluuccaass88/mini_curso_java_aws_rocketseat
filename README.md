<h1>Encurtador de URLs Serverless com AWS</h1>

[pt-br] Este projeto, desenvolvido durante o mini-curso de Java e AWS da Rocketseat demonstra a implementação de um encurtador de URLs utilizando uma arquitetura serverless robusta na AWS. A solução combina a flexibilidade de Lambdas com a 
escalabilidade do API Gateway e a durabilidade do S3.

Arquitetura:

- API Gateway: Expor a API RESTful para criação e redirecionamento de URLs.
- Lambda: GenerateShortnerUrl: Gera URLs curtas, utilizando um algoritmo de hashing ou base62, e armazena as associações no S3.
- Lambda: RedirectUrlShortener: Recupera a URL original do S3 com base no identificador curto e realiza o redirecionamento HTTP.
- Amazon S3: Armazena as associações entre URLs curtas e originais, garantindo alta disponibilidade e durabilidade.

  ![image](https://github.com/user-attachments/assets/a7b38d77-bac2-4940-8af8-c45979649be6)

[en-us] This project showcases the implementation of a URL shortener using a robust serverless architecture on AWS. The solution combines the flexibility of Lambdas with the scalability of API Gateway and the durability of S3.

Architecture:

- API Gateway: Exposes the RESTful API for creating and redirecting URLs.
- Lambda: GenerateShortnerUrl: Generates short URLs using a hashing or base62 algorithm and stores the associations in S3.
- Lambda: RedirectUrlShortener: Retrieves the original URL from S3 based on the short identifier and performs the HTTP redirect.
- Amazon S3: Stores the associations between short and original URLs, ensuring high availability and durability.
