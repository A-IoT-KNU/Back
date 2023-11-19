# Backend

### Requirements

Download and install [Docker Desktop](https://docs.docker.com/get-docker/).

### Getting started

1) Clone repository

2) In root of repository run script `./build-container.sh` and wait for an inscription `Build finished`

3) Go to the Keycloak on `localhost:9001`:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-19-29-30-image.png)

4) Go to the section `Administration Console`:

5) Sign in with username `admin` and password `admin`:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-19-42-08-image.png)

6) Go to the section `Master` and press `Add realm` button:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-19-43-24-image.png)

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-19-43-42-image.png)

7) Name realm `iot`, press `Select file` button, choose from repository folder `keycloak/realmdata` file `realm-iot.json` and press `Create` button:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-19-48-52-image.png)

8) Go to the section `Iot` and choose `Master` section:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-19-50-34-image.png)

9) Go to the section `Clients`:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-19-53-12-image.png)

10) Press on `admin-cli`:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-19-55-23-image.png)

11. Choose `Access Type` and change from `public` to `confidential`, turn on `Service Accounts Enabled` and press `Save` button below:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-20-00-21-image.png)

12) Press on `Service Account Roles`:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-20-12-27-image.png)

13) Choose `Client Roles` and select `iot-realm`:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-20-13-56-image.png)

14) In `Available Roles` choose `manage-users`, `query-users`, `view-users` and press `Add selected >>`:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-20-16-37-image.png)

15) Press on `Credentials` and copy `Secret` value (or press `Regenerate Secret` button and then copy `Secret` value):

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-20-18-01-image.png)

16. Go to the section `Master` and choose `Iot` section:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-20-22-04-image.png)

17. Go to the section `Clients`:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-20-23-55-image.png)

18) Press on `iot-rest-api`:

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-20-24-33-image.png)

19) Press on `Credentials` and copy `Secret` value (or press `Regenerate Secret` button and then copy `Secret` value):

![](/home/pavlokilko/.var/app/com.github.marktext.marktext/config/marktext/images/2023-11-19-20-31-48-image.png)

20. Close Keycloak

21. Stop container in Docker Desktop

22. In repository folder `env.d` open file `backend` and paste saved secret into `KEYCLOAK_ADMIN_CLIENT_SECRET` and `KEYCLOAK_IOT_CLIENT_SECRET`variables:

```properties
# SOME ENVIROMENT VARIABLES 
# ...

KEYCLOAK_ADMIN_CLIENT_SECRET=<YOUR_SAVED_ADMIN_CLIENT_SECRET>
KEYCLOAK_IOT_CLIENT_SECRET=<YOUR_SAVED_IOT_CLIENT_SECRET>
```

23. In root of repository run script `./build-container.sh` and wait for an inscription `Build finished`

24. Now backend is must be ready for REST API REQUEST (for detail information about backend API, read [API-DOC](./API-DOC.md)
