```bash
docker run -d --name keycloak -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev
```
### After creating the keycloak container, we need to create a 
        - visit localhost:8080/admin

        - create a new realm "event-management"
        - create client("event-manager-backend", "event-manager-frontend"), and 
        - create roles("ADMIN", "USER")
        - also create few sample user for testing purpose for both "USER" and "ADMIN".
        - Enable OpenID Connect for  both clients
        - Add the roles to the user's
        - provide root URL for "event-manager-backend" : http://localhost:8081
        - enable client authentication for "event-manager-backend"
        - enable "Direct Access Grants" for "event-manager-backend"
        - enable "Service Accounts Enabled" for "event-manager-backend"

        - enable Valid Redirect URIs for "event-manager-frontend" : http://localhost:3000/
        - enable Web Origins for "event-manager-frontend" : http://localhost:3000/
        - enable "direct access grants" for "event-manager-frontend"

```bash
docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres
```

```bash
docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres
```

```bash
docker exec -it postgres psql -U admin -c "CREATE DATABASE eventdb;"
```
```bash
docker exec -it postgres psql -U admin -l
```
```bash
docker exec -it postgres psql -U admin -d eventdb

```


## Generate Token for user's
### change the "client_secret", "username", "password" as per your configuration
```bash
curl -X POST 'http://localhost:8080/realms/event-management/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=event-manager-backend' \
--data-urlencode 'client_secret=hZdjg84U38snYfObrwELG2pIQp0XIcwt' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=priuansusahoo' \
--data-urlencode 'password=Something@12345'
```

## Kafka SetUp
```bash
docker network create kafka-network
```
```bash
docker run -d --network kafka-network --name zookeeper -e ZOOKEEPER_CLIENT_PORT=2181 -e ALLOW_ANONYMOUS_LOGIN=yes bitnami/zookeeper
```
```bash
docker run -d --network kafka-network --name kafka -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181 -e ALLOW_PLAINTEXT_LISTENER=yes -p 9092:9092 bitnami/kafka
```
