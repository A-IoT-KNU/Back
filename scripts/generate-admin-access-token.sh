curl --location --request POST 'http://localhost:9001/realms/master/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'client_id=admin-cli' \
--data-urlencode 'client_secret=SEPc4oCagfpKZeC6oZnccLlQlntia3hg' | json_pp
