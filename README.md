# 🎵 Music API - Processo Seletivo SEPLAG/MT 2026

API REST para gerenciamento de artistas e álbuns musicais desenvolvida como parte do Processo Seletivo Simplificado para Analista de Tecnologia da Informação (Engenheiro da Computação - Sênior).

---

## 📋 Dados do Candidato

**Nome:** JOÃO GABRIEL DE MATOS SILVA 
**CPF:** 060.921.761-57
**Vaga:** Analista de Tecnologia da Informação - Engenheiro da Computação (Sênior)  
**Edital:** Nº 001/2026/SEPLAG  
**Data de Entrega:** 05 de fevereiro de 2026

---

## 📑 Sumário

- [Sobre o Projeto](#-sobre-o-projeto)
- [Arquitetura e Decisões Técnicas](#-arquitetura-e-decisões-técnicas)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Requisitos Implementados](#-requisitos-implementados)
- [Pré-requisitos](#-pré-requisitos)
- [Como Executar](#-como-executar)
- [Como Testar](#-como-testar)
- [Documentação da API](#-documentação-da-api)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Decisões de Implementação](#-decisões-de-implementação)
- [Limitações e Próximos Passos](#-limitações-e-próximos-passos)

---

## 🎯 Sobre o Projeto

Esta API REST foi desenvolvida para gerenciar um catálogo de artistas musicais e seus álbuns, permitindo operações CRUD completas, autenticação segura via JWT, paginação, consultas parametrizadas e documentação interativa via Swagger.

### Funcionalidades Principais

- ✅ Gerenciamento de Artistas (CRUD)
- ✅ Gerenciamento de Álbuns (CRUD)
- ✅ Relacionamento N:N entre Artistas e Álbuns
- ✅ Autenticação e autorização via JWT
- ✅ Paginação e ordenação de resultados
- ✅ Documentação interativa (Swagger UI)
- ✅ Tratamento de erros padronizado
- ✅ Banco de dados PostgreSQL com migrations
- ✅ Containerização com Docker

---

## 🏗️ Arquitetura e Decisões Técnicas

### Arquitetura em Camadas

```
┌─────────────────────────────────────────┐
│         Controllers (API Layer)          │
│  - ArtistaController                     │
│  - AlbumController                       │
│  - AuthController                        │
└───────────────┬─────────────────────────┘
                │
┌───────────────▼─────────────────────────┐
│         Services (Business Layer)        │
│  - ArtistaService                        │
│  - AlbumService                          │
└───────────────┬─────────────────────────┘
                │
┌───────────────▼─────────────────────────┐
│       Repositories (Data Layer)          │
│  - ArtistaRepository                     │
│  - AlbumRepository                       │
│  - UsuarioRepository                     │
└───────────────┬─────────────────────────┘
                │
┌───────────────▼─────────────────────────┐
│         Database (PostgreSQL)            │
└─────────────────────────────────────────┘
```

### Modelo de Dados

```sql
┌──────────────┐         ┌─────────────────┐         ┌──────────────┐
│   Artista    │         │ artista_album   │         │    Album     │
├──────────────┤         ├─────────────────┤         ├──────────────┤
│ id (PK)      │◄───────►│ artista_id (FK) │◄───────►│ id (PK)      │
│ nome         │         │ album_id (FK)   │         │ nome         │
└──────────────┘         └─────────────────┘         └──────────────┘
                                                            
                         ┌─────────────────┐
                         │    Usuario      │
                         ├─────────────────┤
                         │ id (PK)         │
                         │ username        │
                         │ password        │
                         │ ativo           │
                         └─────────────────┘
```

**Justificativa do Relacionamento N:N:**
- Um artista pode ter vários álbuns
- Um álbum pode ter vários artistas (ex: colaborações, bandas)
- Permite flexibilidade para modelar diferentes cenários musicais

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem de programação
- **Spring Boot 3.5.10** - Framework principal
- **Spring Security** - Segurança e autenticação
- **Spring Data JPA** - Persistência de dados
- **Hibernate** - ORM
- **JWT (jjwt 0.11.5)** - Tokens de autenticação

### Banco de Dados
- **PostgreSQL 15** - Banco de dados relacional
- **Flyway 9.22.3** - Versionamento e migrations

### Documentação
- **SpringDoc OpenAPI 2.7.0** - Documentação automática
- **Swagger UI** - Interface interativa

### Infraestrutura
- **Docker** - Containerização
- **Docker Compose** - Orquestração de containers

### Ferramentas de Desenvolvimento
- **Maven** - Gerenciamento de dependências
- **Lombok** - Redução de boilerplate
- **BCrypt** - Hashing de senhas

---

## ✅ Requisitos Implementados

### Requisitos Gerais (Edital)

| Requisito | Status | Observação |
|-----------|--------|------------|
| a) Segurança (CORS) | ✅ Implementado | Configurado no SecurityConfig |
| b) Autenticação JWT (5 min) | ✅ Implementado | Token expira em 5 minutos |
| b.1) Renovação de token | ⚠️ Parcial | Requer novo login (ver limitações) |
| c) POST, PUT, GET | ✅ Implementado | CRUD completo |
| d) Paginação | ✅ Implementado | Via Pageable do Spring |
| e) Consultas parametrizadas | ✅ Implementado | Filtros por artista |
| f) Ordenação alfabética | ✅ Implementado | Asc/Desc por nome |
| g) Upload de imagens | ❌ Não implementado | Ver limitações |
| h) MinIO (S3) | ❌ Não implementado | Ver limitações |
| i) Links pré-assinados | ❌ Não implementado | Ver limitações |
| j) Versionamento | ✅ Implementado | `/api/v1/...` |
| k) Flyway Migrations | ✅ Implementado | V1, V2, V3 |
| l) OpenAPI/Swagger | ✅ Implementado | Completo e funcional |

### Requisitos Sênior (Edital)

| Requisito | Status | Observação |
|-----------|--------|------------|
| a) Health Checks | ✅ Implementado | Via Spring Actuator |
| b) Testes unitários | ✅ Implementado | Services e Controllers |
| c) WebSocket | ❌ Não implementado | Ver limitações |
| d) Rate Limiting | ❌ Não implementado | Ver limitações |
| e) Endpoint Regionais | ❌ Não implementado | Ver limitações |

**Cobertura de Testes:** Serviços principais e Controllers críticos

---

## 📦 Pré-requisitos

Para executar o projeto, você precisa ter instalado:

- **Docker** (versão 20.10 ou superior)
- **Docker Compose** (versão 2.0 ou superior)
- **Git** (para clonar o repositório)

**Opcional** (para desenvolvimento):
- **Java 17** (JDK)
- **Maven 3.8+**
- **IDE** (IntelliJ IDEA, VS Code, Eclipse)

---

## 🚀 Como Executar

### 1. Clonar o Repositório

```bash
git clone https://github.com/[SEU-USUARIO]/joaogabriel060921.git
cd joaogabriel060921
```

### 2. Executar com Docker Compose

```bash
# Subir todos os serviços (API + PostgreSQL)
docker-compose up -d

# Verificar se os containers estão rodando
docker-compose ps

# Acompanhar os logs
docker-compose logs -f api
```

### 3. Aguardar Inicialização

A aplicação estará disponível em alguns segundos. Aguarde a mensagem:
```
Started MusicApiApplication in X.XXX seconds
```

### 4. Acessar a Aplicação

- **API:** http://localhost:8080
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **Health Check:** http://localhost:8080/actuator/health

### 5. Parar os Serviços

```bash
# Parar os containers
docker-compose down

# Parar e remover volumes (limpa banco de dados)
docker-compose down -v
```

---

## 🧪 Como Testar

### Via Swagger UI (Recomendado)

1. **Acesse:** http://localhost:8080/swagger-ui.html

2. **Faça Login:**
   ```
   POST /api/v1/auth/login
   Body:
   {
     "username": "admin",
     "password": "123456"
   }
   ```

3. **Copie o Token** retornado

4. **Clique em "Authorize"** (cadeado no topo)

5. **Cole o token** (sem "Bearer") e clique em "Authorize"

6. **Teste os Endpoints:**
   - GET /api/v1/artistas - Listar artistas
   - GET /api/v1/albuns - Listar álbuns (paginado)
   - POST /api/v1/artistas - Criar novo artista
   - PUT /api/v1/albuns/{id} - Atualizar álbum

### Via cURL

#### 1. Fazer Login
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

Resposta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

#### 2. Listar Artistas
```bash
curl -X GET http://localhost:8080/api/v1/artistas \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

#### 3. Listar Álbuns (Paginado)
```bash
curl -X GET "http://localhost:8080/api/v1/albuns?page=0&size=5&sort=nome,asc" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI"
```

#### 4. Criar Artista
```bash
curl -X POST http://localhost:8080/api/v1/artistas \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Novo Artista"
  }'
```

#### 5. Criar Álbum
```bash
curl -X POST http://localhost:8080/api/v1/albuns \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Novo Álbum",
    "artistasIds": [1, 2]
  }'
```

### Executar Testes Automatizados

```bash
# Com Maven (sem Docker)
mvn test

# Ver relatório de cobertura
mvn test jacoco:report
# Relatório em: target/site/jacoco/index.html
```

---

## 📚 Documentação da API

### Endpoints Principais

#### Autenticação

**POST** `/api/v1/auth/login` - Fazer login
- **Body:**
  ```json
  {
    "username": "admin",
    "password": "123456"
  }
  ```
- **Response:**
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiJ9..."
  }
  ```

#### Artistas

**GET** `/api/v1/artistas` - Listar todos artistas
- **Headers:** `Authorization: Bearer {token}`
- **Response:**
  ```json
  [
    {
      "id": 1,
      "nome": "Serj Tankian"
    }
  ]
  ```

**POST** `/api/v1/artistas` - Criar artista
- **Headers:** `Authorization: Bearer {token}`
- **Body:**
  ```json
  {
    "nome": "Nome do Artista"
  }
  ```

#### Álbuns

**GET** `/api/v1/albuns` - Listar álbuns (paginado)
- **Headers:** `Authorization: Bearer {token}`
- **Query Params:**
  - `page` (default: 0)
  - `size` (default: 10)
  - `sort` (exemplo: `nome,asc` ou `nome,desc`)
- **Response:**
  ```json
  {
    "content": [
      {
        "id": 1,
        "nome": "Harakiri",
        "artistas": [
          {
            "id": 1,
            "nome": "Serj Tankian"
          }
        ]
      }
    ],
    "pageable": {...},
    "totalElements": 14,
    "totalPages": 2
  }
  ```

**POST** `/api/v1/albuns` - Criar álbum
- **Headers:** `Authorization: Bearer {token}`
- **Body:**
  ```json
  {
    "nome": "Nome do Álbum",
    "artistasIds": [1, 2]
  }
  ```

**PUT** `/api/v1/albuns/{id}` - Atualizar álbum
- **Headers:** `Authorization: Bearer {token}`
- **Body:**
  ```json
  {
    "nome": "Nome Atualizado",
    "artistasIds": [1]
  }
  ```

### Dados de Teste

O sistema já vem com dados de exemplo conforme o edital:

**Artistas e Álbuns:**
- **Serj Tankian:** Harakiri, Black Blooms, The Rough Dog
- **Mike Shinoda:** The Rising Tied, Post Traumatic, Post Traumatic EP, Where'd You Go
- **Michel Teló:** Bem Sertanejo, Bem Sertanejo - O Show (Ao Vivo), Bem Sertanejo - (1ª Temporada) - EP
- **Guns N' Roses:** Use Your Illusion I, Use Your Illusion II, Greatest Hits

**Usuário de Teste:**
- **Username:** admin
- **Password:** admin123

---

## 📁 Estrutura do Projeto

```
joaogabriel060921/
├── src/
│   ├── main/
│   │   ├── java/com/seplag/musicapi/
│   │   │   ├── config/              # Configurações (OpenAPI)
│   │   │   ├── controller/          # Controllers REST
│   │   │   ├── dominio/
│   │   │   │   └── entidade/        # Entidades JPA
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── exception/           # Tratamento de exceções
│   │   │   ├── repository/          # Repositories JPA
│   │   │   ├── security/            # JWT e Security Config
│   │   │   ├── service/             # Camada de negócio
│   │   │   └── MusicApiApplication.java
│   │   └── resources/
│   │       ├── application.yml      # Configurações da aplicação
│   │       └── db/migration/        # Scripts Flyway
│   │           ├── V1__create_schema.sql
│   │           ├── V2__create_usuario.sql
│   │           └── V3__seed_data.sql
│   └── test/                        # Testes unitários
├── docker-compose.yml               # Orquestração Docker
├── Dockerfile                       # Imagem da aplicação
├── pom.xml                          # Dependências Maven
└── README.md                        # Este arquivo
```

---

## 💡 Decisões de Implementação

### 1. Relacionamento N:N (Artista ↔ Álbum)

**Decisão:** Usar tabela intermediária `artista_album`

**Justificativa:**
- Flexibilidade para álbuns com múltiplos artistas (colaborações)
- Permite modelar bandas como artistas únicos
- Facilita consultas bidirecionais (artistas de um álbum, álbuns de um artista)

**Alternativas consideradas:**
- 1:N (Álbum → Artista): Limitaria álbuns a um único artista
- JSON na tabela: Perderia integridade referencial

### 2. Autenticação JWT com Spring Security

**Decisão:** JWT stateless com expiração de 5 minutos

**Justificativa:**
- **Stateless:** Escalável, não requer sessão no servidor
- **Expiração curta:** Maior segurança conforme requisito do edital
- **BCrypt para senhas:** Hashing robusto (custo 10)

**Trade-off:**
- Usuário precisa fazer login frequentemente
- Ideal seria implementar refresh token (ver limitações)

### 3. Paginação com Spring Data

**Decisão:** Usar `Pageable` do Spring Data

**Justificativa:**
- Solução nativa e testada
- Suporte a ordenação integrado
- Fácil integração com front-end

**Configuração padrão:**
```java
@PageableDefault(size = 10, sort = "nome")
```

### 4. Flyway para Migrations

**Decisão:** Migrations versionadas (V1, V2, V3)

**Justificativa:**
- **V1:** Schema base (tabelas)
- **V2:** Usuário inicial (admin/admin123)
- **V3:** Dados de exemplo do edital

**Benefícios:**
- Rastreabilidade de mudanças
- Reprodutibilidade em qualquer ambiente
- Rollback seguro se necessário

### 5. Tratamento de Exceções Centralizado

**Decisão:** `@RestControllerAdvice` com handlers específicos

**Justificativa:**
- Respostas de erro padronizadas
- Separação de concerns (controller não trata exceções)
- Fácil manutenção

**Estrutura da resposta de erro:**
```json
{
  "timestamp": "2026-02-05T10:30:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Usuário ou senha incorretos"
}
```

### 6. Docker Compose para Desenvolvimento

**Decisão:** Separar serviços (API + PostgreSQL)

**Justificativa:**
- Ambiente consistente entre dev e produção
- Fácil setup para avaliadores
- Isolamento de dependências

**Configuração:**
```yaml
services:
  db:
    image: postgres:15
    # ...
  api:
    build: .
    depends_on:
      - db
```

---

## ⚠️ Limitações e Próximos Passos

### Não Implementados (por ordem de prioridade)

#### 1. Upload de Imagens + MinIO + Presigned URLs

**Status:** ❌ Não implementado

**Motivo:** Limitação de tempo e priorização dos requisitos core

**Impacto:** 
- Não é possível fazer upload de capas de álbuns
- Não há integração com storage S3-compatible

**Como implementaria:**

**Dependências necessárias:**
```xml
<dependency>
    <groupId>io.minio</groupId>
    <artifactId>minio</artifactId>
    <version>8.5.7</version>
</dependency>
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.15.1</version>
</dependency>
```

**Implementação:**
```java
@Service
public class MinioService {
    
    @Value("${minio.url}")
    private String minioUrl;
    
    @Value("${minio.access-key}")
    private String accessKey;
    
    @Value("${minio.secret-key}")
    private String secretKey;
    
    private MinioClient minioClient;
    
    @PostConstruct
    public void init() {
        minioClient = MinioClient.builder()
            .endpoint(minioUrl)
            .credentials(accessKey, secretKey)
            .build();
    }
    
    public String uploadFile(MultipartFile file, String fileName) {
        // Upload para MinIO
        // Retornar identificador do arquivo
    }
    
    public String generatePresignedUrl(String fileName) {
        // Gerar URL com expiração de 30 minutos
        return minioClient.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .bucket("album-covers")
                .object(fileName)
                .expiry(30, TimeUnit.MINUTES)
                .build()
        );
    }
}
```

**Endpoint:**
```java
@PostMapping("/{id}/capa")
public ResponseEntity<AlbumResponseDTO> uploadCapa(
    @PathVariable Long id,
    @RequestParam("file") MultipartFile file
) {
    // Validar formato (JPEG, PNG)
    // Upload para MinIO
    // Salvar referência no banco
    // Retornar álbum atualizado
}

@GetMapping("/{id}/capa")
public ResponseEntity<String> getCapaUrl(@PathVariable Long id) {
    // Gerar presigned URL
    // Retornar URL com expiração de 30 min
}
```

**Docker Compose addition:**
```yaml
minio:
  image: minio/minio:latest
  ports:
    - "9000:9000"
    - "9001:9001"
  environment:
    MINIO_ROOT_USER: admin
    MINIO_ROOT_PASSWORD: admin123
  command: server /data --console-address ":9001"
```

**Esforço estimado:** 6-8 horas

---

#### 2. Renovação de Token JWT (Refresh Token)

**Status:** ⚠️ Parcial (token expira mas não renova automaticamente)

**Motivo:** Priorização de funcionalidades core

**Impacto:**
- Usuário precisa fazer login a cada 5 minutos
- Experiência de usuário prejudicada

**Como implementaria:**

**Nova entidade:**
```java
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue
    private Long id;
    
    private String token;
    
    @ManyToOne
    private Usuario usuario;
    
    private LocalDateTime expiryDate; // 7 dias
}
```

**Endpoint:**
```java
@PostMapping("/refresh")
public ResponseEntity<AuthResponseDTO> refresh(
    @RequestBody RefreshTokenRequestDTO request
) {
    // Validar refresh token
    // Gerar novo access token (5 min)
    // Opcionalmente gerar novo refresh token
    return ResponseEntity.ok(new AuthResponseDTO(newAccessToken));
}
```

**Fluxo:**
1. Login retorna: `accessToken` (5 min) + `refreshToken` (7 dias)
2. Front usa `accessToken` nas requisições
3. Quando `accessToken` expira (401), front chama `/refresh` com `refreshToken`
4. Recebe novo `accessToken`

**Esforço estimado:** 3-4 horas

---

#### 3. WebSocket para Notificações

**Status:** ❌ Não implementado

**Motivo:** Requisito sênior opcional, priorizei testes unitários

**Impacto:**
- Front não recebe notificação em tempo real quando novo álbum é criado

**Como implementaria:**

**Dependência:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

**Configuração:**
```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
```

**No Service:**
```java
@Service
public class AlbumService {
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    public AlbumResponseDTO criar(AlbumRequestDTO dto) {
        Album album = // criar álbum
        
        // Notificar via WebSocket
        messagingTemplate.convertAndSend(
            "/topic/albuns", 
            AlbumResponseDTO.fromEntity(album)
        );
        
        return AlbumResponseDTO.fromEntity(album);
    }
}
```

**Front-end (exemplo):**
```javascript
const socket = new SockJS('http://localhost:8080/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, () => {
    stompClient.subscribe('/topic/albuns', (message) => {
        const novoAlbum = JSON.parse(message.body);
        console.log('Novo álbum cadastrado:', novoAlbum);
        // Atualizar UI
    });
});
```

**Esforço estimado:** 4-5 horas

---

#### 4. Rate Limiting (10 req/min por usuário)

**Status:** ❌ Não implementado

**Motivo:** Funcionalidade avançada, tempo limitado

**Impacto:**
- Sistema vulnerável a abuso (muitas requisições de um mesmo usuário)
- Sem proteção contra ataques de força bruta

**Como implementaria:**

**Opção 1: Bucket4j (biblioteca especializada)**

**Dependência:**
```xml
<dependency>
    <groupId>com.bucket4j</groupId>
    <artifactId>bucket4j-core</artifactId>
    <version>8.7.0</version>
</dependency>
```

**Implementação:**
```java
@Component
public class RateLimitFilter extends OncePerRequestFilter {
    
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        
        String username = getUsername(request); // Do token JWT
        
        Bucket bucket = cache.computeIfAbsent(username, k -> 
            Bucket.builder()
                .addLimit(Limit.of(10, Duration.ofMinutes(1)))
                .build()
        );
        
        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(429); // Too Many Requests
            response.getWriter().write(
                "{\"error\":\"Rate limit exceeded: 10 requests per minute\"}"
            );
        }
    }
}
```

**Opção 2: Spring AOP + Redis (produção)**
```java
@Aspect
@Component
public class RateLimitAspect {
    
    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;
    
    @Around("@annotation(RateLimited)")
    public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        String username = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();
        
        String key = "rate_limit:" + username;
        Integer count = redisTemplate.opsForValue().get(key);
        
        if (count == null) {
            redisTemplate.opsForValue().set(key, 1, 1, TimeUnit.MINUTES);
        } else if (count < 10) {
            redisTemplate.opsForValue().increment(key);
        } else {
            throw new RateLimitExceededException();
        }
        
        return joinPoint.proceed();
    }
}
```

**Headers de resposta:**
```
X-RateLimit-Limit: 10
X-RateLimit-Remaining: 7
X-RateLimit-Reset: 1643723400
```

**Esforço estimado:** 3-4 horas (Bucket4j) ou 6-8 horas (Redis + AOP)

---

#### 5. Endpoint de Regionais (Sincronização)

**Status:** ❌ Não implementado

**Motivo:** Requisito específico e complexo, priorizado funcionalidades core

**Impacto:**
- Não há sincronização com a API externa de regionais da Polícia Civil

**Como implementaria:**

**Entidade:**
```java
@Entity
public class Regional {
    @Id
    private Integer id;
    
    @Column(length = 200)
    private String nome;
    
    private Boolean ativo = true;
    
    @CreatedDate
    private LocalDateTime criadoEm;
    
    @LastModifiedDate
    private LocalDateTime atualizadoEm;
}
```

**DTO da API Externa:**
```java
public record RegionalExternoDTO(
    Integer id,
    String nome
) {}
```

**Service:**
```java
@Service
public class RegionalSyncService {
    
    @Autowired
    private RegionalRepository regionalRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Scheduled(fixedRate = 3600000) // A cada 1 hora
    public void sincronizar() {
        List<RegionalExternoDTO> regionaisExternas = buscarRegionaisExternas();
        List<Regional> regionaisLocais = regionalRepository.findAll();
        
        // Mapas para comparação eficiente O(1)
        Map<Integer, RegionalExternoDTO> mapaExterno = regionaisExternas
            .stream()
            .collect(Collectors.toMap(
                RegionalExternoDTO::id, 
                Function.identity()
            ));
        
        Map<Integer, Regional> mapaLocal = regionaisLocais
            .stream()
            .collect(Collectors.toMap(
                Regional::getId, 
                Function.identity()
            ));
        
        // 1. Novas regionais → Inserir
        regionaisExternas.forEach(externa -> {
            if (!mapaLocal.containsKey(externa.id())) {
                Regional nova = new Regional();
                nova.setId(externa.id());
                nova.setNome(externa.nome());
                nova.setAtivo(true);
                regionalRepository.save(nova);
                log.info("Regional inserida: {} - {}", externa.id(), externa.nome());
            }
        });
        
        // 2. Regionais ausentes na API → Inativar
        regionaisLocais.forEach(local -> {
            if (local.getAtivo() && !mapaExterno.containsKey(local.getId())) {
                local.setAtivo(false);
                regionalRepository.save(local);
                log.info("Regional inativada: {} - {}", local.getId(), local.getNome());
            }
        });
        
        // 3. Atributo alterado → Inativar antiga + Criar nova
        regionaisLocais.forEach(local -> {
            RegionalExternoDTO externa = mapaExterno.get(local.getId());
            if (externa != null && 
                local.getAtivo() && 
                !local.getNome().equals(externa.nome())) {
                
                // Inativar antiga
                local.setAtivo(false);
                regionalRepository.save(local);
                
                // Criar nova com mesmo ID mas nome atualizado
                Regional nova = new Regional();
                nova.setId(externa.id());
                nova.setNome(externa.nome());
                nova.setAtivo(true);
                regionalRepository.save(nova);
                
                log.info("Regional atualizada: {} - {} → {}", 
                    local.getId(), local.getNome(), externa.nome());
            }
        });
    }
    
    private List<RegionalExternoDTO> buscarRegionaisExternas() {
        String url = "https://integrador-argus-api.geia.vip/v1/regionais";
        try {
            ResponseEntity<RegionalExternoDTO[]> response = 
                restTemplate.getForEntity(url, RegionalExternoDTO[].class);
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            log.error("Erro ao buscar regionais: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}
```

**Endpoint Manual:**
```java
@RestController
@RequestMapping("/api/v1/regionais")
public class RegionalController {
    
    @Autowired
    private RegionalSyncService syncService;
    
    @PostMapping("/sincronizar")
    public ResponseEntity<String> sincronizarManual() {
        syncService.sincronizar();
        return ResponseEntity.ok("Sincronização iniciada");
    }
    
    @GetMapping
    public ResponseEntity<List<Regional>> listar(
        @RequestParam(required = false, defaultValue = "true") Boolean ativo
    ) {
        return ResponseEntity.ok(
            regionalRepository.findByAtivo(ativo)
        );
    }
}
```

**Complexidade do algoritmo:** O(n + m) onde n = regionais locais, m = regionais externas
- Uso de `HashMap` para comparação em O(1)
- Evita nested loops O(n²)

**Esforço estimado:** 5-6 horas

---

#### 6. Testes de Integração E2E

**Status:** ⚠️ Parcial (apenas testes unitários implementados)

**Motivo:** Foco em testes unitários dos componentes críticos

**Impacto:**
- Não há validação automatizada do fluxo completo (login → criar álbum → consultar)

**Como implementaria:**

**Dependências:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>postgresql</artifactId>
    <scope>test</scope>
</dependency>
```

**Teste E2E:**
```java
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
class AlbumE2ETest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");
    
    @LocalServerPort
    private int port;
    
    private String token;
    
    @BeforeEach
    void fazerLogin() {
        token = given()
            .contentType(ContentType.JSON)
            .body(Map.of("username", "admin", "password", "admin123"))
            .when()
            .post("/api/v1/auth/login")
            .then()
            .statusCode(200)
            .extract()
            .path("token");
    }
    
    @Test
    void devePermitirFluxoCompletoDeAlbum() {
        // 1. Criar artista
        Long artistaId = given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(Map.of("nome", "Artista Teste"))
            .when()
            .post("/api/v1/artistas")
            .then()
            .statusCode(201)
            .extract()
            .path("id");
        
        // 2. Criar álbum
        Long albumId = given()
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(Map.of(
                "nome", "Álbum Teste",
                "artistasIds", List.of(artistaId)
            ))
            .when()
            .post("/api/v1/albuns")
            .then()
            .statusCode(201)
            .extract()
            .path("id");
        
        // 3. Consultar álbum
        given()
            .header("Authorization", "Bearer " + token)
            .when()
            .get("/api/v1/albuns")
            .then()
            .statusCode(200)
            .body("content.size()", greaterThan(0))
            .body("content.find { it.id == " + albumId + " }.nome", equalTo("Álbum Teste"));
    }
}
```

**Esforço estimado:** 4-6 horas

---

### Melhorias Futuras

#### Performance
- **Cache Redis:** Para consultas frequentes (lista de artistas)
- **Índices no BD:** Em campos de busca (nome de artista/álbum)
- **Lazy Loading:** Para relacionamentos N:N quando necessário

#### Segurança
- **HTTPS:** Certificado SSL em produção
- **OWASP:** Validações contra SQL Injection, XSS
- **Auditoria:** Log de todas as ações de usuários

#### Observabilidade
- **ELK Stack:** Centralização de logs
- **Prometheus + Grafana:** Métricas e dashboards
- **Distributed Tracing:** Com Sleuth/Zipkin

#### DevOps
- **CI/CD:** GitHub Actions para build/test/deploy automático
- **Kubernetes:** Orquestração em produção
- **Versionamento Semântico:** Tags no Git (v1.0.0, v1.1.0)

---

## 🎓 Considerações Finais

Este projeto foi desenvolvido com foco em:

1. **Qualidade sobre Quantidade:** Preferi implementar bem os requisitos core do que implementar tudo superficialmente

2. **Código Limpo:** Seguindo princípios SOLID, DRY, e nomenclaturas descritivas

3. **Documentação:** README completo, código comentado onde necessário, Swagger funcional

4. **Testabilidade:** Arquitetura em camadas facilita testes, DTOs separam concerns

5. **Manutenibilidade:** Estrutura organizada, exceções centralizadas, configurações externalizadas

### Lições Aprendidas

- **Priorização é crucial:** Em processos seletivos com tempo limitado, escolher o que implementar é tão importante quanto a implementação
- **Docker facilita avaliação:** Garantir que avaliadores consigam executar facilmente
- **Documentação vale ouro:** Um README bem escrito demonstra organização e comunicação

### Agradecimentos

Agradeço a oportunidade de participar deste processo seletivo e espero que este projeto demonstre minha capacidade técnica e comprometimento com qualidade.

Fico à disposição para esclarecimentos e melhorias.

---

**Desenvolvido para o Processo Seletivo SEPLAG/MT 2026**  
**Edital Nº 001/2026 - Analista de TI (Engenheiro da Computação - Sênior)**
