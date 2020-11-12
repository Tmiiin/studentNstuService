# Hello-service
MCX hello example service
> Default server port - 8888

#### Build docker image:
    docker build -t example/hello-service:latest .
#### Docker run environment variables
    - DB_HOST - database host (default = localhost)
    - DB_PORT - database post (default = 5432)
    - DB_NAME - database name (default = postgres)
    - DB_SSL_MODE - postgresql sslmode parameter value (default = require) 
    - DB_USERNAME - database user name (default = postgres)
    - DB_USERPASS - detabase user password (default = mysecretpassword)
    
#### Swagger UI: 
    http://localhost:8888/swagger-ui.html
#### OpenApi v3 docs file:
    http://localhost:8888/v3/api-docs
    http://localhost:8888/v3/api-docs.yaml     