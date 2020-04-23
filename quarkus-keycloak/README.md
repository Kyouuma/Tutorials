## You can find the tutorial in blog [here](https://kyouuma.blogspot.com/2020/04/securing-apis-from-theory-to-practice.html)
## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```
./mvnw quarkus:dev
```

## Retrieve Tokens

```
KC_CLIENT_ID=quarkus-keycloak
KC_ISSUER=http://localhost:8180/auth/realms/kyoumatrix
```

### username:password | ROLE

> oussama:oussama | ADMIN

> john:john | EDITOR

> jane:jane | SIMPLE USER

```
KC_USERNAME=oussama
KC_PASSWORD=oussama

KC_RESPONSE=$( \
curl \
  -d "client_id=$KC_CLIENT_ID" \
 -d "username=$KC_USERNAME" \
  -d "password=$KC_PASSWORD" \
 -d "grant_type=password" \
 "$KC_ISSUER/protocol/openid-connect/token" \
)
echo $KC_RESPONSE | jq -C .

KC_ACCESS_TOKEN=$(echo $KC_RESPONSE | jq -r .access_token)
```

#### Endpoints - /data/user should work, /data/editor /data/admin

```
curl -v -H "Authorization: Bearer $KC_ACCESS_TOKEN" http://localhost:8082/api/data/user
curl -v -H "Authorization: Bearer $KC_ACCESS_TOKEN" http://localhost:8082/api/data/editor
curl -v -H "Authorization: Bearer \$KC_ACCESS_TOKEN" http://localhost:8082/api/data/admin
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `quarkus-keycloak-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/quarkus-keycloak-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/quarkus-keycloak-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.


## Exporting keycloak Realm with users

Keycloak provides us with a script "standalone.sh" that bootstraps a keycloak instance to export our realm. the below command we'll tell keycloak to run with an offset of 100 otherwise it will find the ports used by the actual container and to export kyoumatrix realm with its users into a single file under /tmp folder.
```$xslt
docker exec -it keycloak /opt/jboss/keycloak/bin/standalone.sh -Djboss.socket.binding.port-offset=100 -Dkeycloak.migration.action=export -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.realmName=kyoumatrix -Dkeycloak.migration.usersExportStrategy=REALM_FILE -Dkeycloak.migration.provider=dir -Dkeycloak.migration.dir=/tmp
docker cp -R keycloak:/tmp/ .
```
## NOTE
there's currently a bug in "mp.jwt.verify.publickey.location" in native mode with urls. [issues 6740](https://github.com/quarkusio/quarkus/issues/6740). You can put the certificate in a certificate-name.pem file instead.
This should be fixed in the next release 1.4.0.Final
