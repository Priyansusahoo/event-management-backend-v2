```bash
docker run -d --name keycloak -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev
```

```bash
docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres
```

```bash
docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres
```

```bash
  689  exit
  690  docker run -d --name keycloak -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev
  691  docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres
  692  sudo systemctl status postgresql
  693  sudo systemctl disbale postgresql
  694  sudo systemctl disable postgresql
  695  sudo systemctl status postgresql
  696  sudo systemctl stop postgresql
  697  sudo systemctl status postgresql
  698  docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres
  699  docker exec -it postgres psql -U admin -c "CREATE DATABASE eventdb;"
  700  docker exec -it postgres psql -U admin -l
  701  docker exec -it postgres psql -U admin -d eventdb
  702  history
```


### Generate Token for user's
```bash
curl -X POST 'http://localhost:8080/realms/event-management/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=event-manager-backend' \
--data-urlencode 'client_secret=hZdjg84U38snYfObrwELG2pIQp0XIcwt' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=priuansusahoo' \
--data-urlencode 'password=Something@12345'
```