# Backend

---

### Requirements

Download and install [Docker Desktop](https://docs.docker.com/get-docker/).

---

### Getting started

1) Clone repository

2) In root of repository run script `./build-container.sh` and wait for an inscription `Build finished`

### Keycloak first setup

1) Go to the Keycloak on `localhost:9001`:

![](./back/images/step-1.png)

2) Go to the section `Administration Console`:

![](./back/images/step-2.png)

3) Sign in with username `admin` and password `admin`:

![](./back/images/step-3.png)

4) Go to the section `Master` and press `Add realm` button:

![](./back/images/step-4.png)

![](./back/images/step-5.png)

5) Name realm `iot`, press `Select file` button, choose from repository folder `keycloak/realmdata` file `realm-iot.json` and press `Create` button:

![](./back/images/step-6.png)

6) Go to the section `Iot` and choose `Master` section:

![](./back/images/step-7.png)

7) Go to the section `Clients`:

![](./back/images/step-8.png)

8) Press on `admin-cli`:

![](./back/images/step-9.png)

9) Choose `Access Type` and change from `public` to `confidential`, turn on `Service Accounts Enabled` and press `Save` button below:

![](./back/images/step-10.png)

10) Press on `Service Account Roles`:

![](./back/images/step-11.png)

11) Choose `Client Roles` and select `iot-realm`:

![](./back/images/step-12.png)

12) In `Available Roles` choose `manage-users`, `query-users`, `view-users` and press `Add selected >>`:

![](./back/images/step-13.png)

13) Press on `Credentials` and copy `Secret` value (or press `Regenerate Secret` button and then copy `Secret` value):

![](./back/images/step-14.png)

14) Go to the section `Master` and choose `Iot` section:

![](./back/images/step-15.png)

15) Go to the section `Clients`:

![](./back/images/step-16.png)

16) Press on `iot-rest-api`:

![](./back/images/step-17.png)

17) Press on `Credentials` and copy `Secret` value (or press `Regenerate Secret` button and then copy `Secret` value):

![](./back/images/step-18.png)

18) Close Keycloak

19) Stop and delete container in Docker Desktop and delete image `backend` (IMPORTANT!!!)

![](./back/images/step-19.png)

![](./back/images/step-20.png)

20) In repository folder `env.d` open file `backend` and paste saved secret into `KEYCLOAK_ADMIN_CLIENT_SECRET` and `KEYCLOAK_IOT_CLIENT_SECRET` variables:

```properties
# SOME ENVIROMENT VARIABLES 
# ...

KEYCLOAK_ADMIN_CLIENT_SECRET=<YOUR_SAVED_ADMIN_CLIENT_SECRET>
KEYCLOAK_IOT_CLIENT_SECRET=<YOUR_SAVED_IOT_CLIENT_SECRET>
```

21) In root of repository run script `./build-container.sh` and wait for an inscription `Build finished`

---

Now backend is must be ready for REST API requests (for detail information about backend API, read [API-DOC](./API-DOC.md)
