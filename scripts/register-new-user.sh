curl --location --request POST 'http://localhost:9001/admin/realms/iot/users' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJRM3JtU2lGdG5MV0FvYkFIUFBoWHdlNlBySzRCYVZzLUFsaFZ6Y0Z2ZG93In0.eyJleHAiOjE2OTg3MjMzODMsImlhdCI6MTY5ODcyMzMyMywianRpIjoiNjM5MTUyNDctMWQ4YS00YWIxLTljZWEtMTI1OWFmMmFkZTViIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAxL3JlYWxtcy9tYXN0ZXIiLCJzdWIiOiJlMDhmNTA3Zi04YzgzLTRiOGQtYWY4Yi1kY2YwNTUzZDhjNDgiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhZG1pbi1jbGkiLCJhY3IiOiIxIiwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiY2xpZW50SG9zdCI6IjE3Mi4yMy4wLjEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImNsaWVudElkIjoiYWRtaW4tY2xpIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic2VydmljZS1hY2NvdW50LWFkbWluLWNsaSIsImNsaWVudEFkZHJlc3MiOiIxNzIuMjMuMC4xIn0.rxmrJPVzp7G8vki-zxI5U5FuOAwcsZOlbXTkggBccgvD8_o2QaJxq-jRHhELfbaSN4EqOOtmbGNgwOS9ZGQCcFJEBGFnA0bpBxOJkslLYg4oyhG1tFDQT_s35YXyLsZMmCuLM3lPVuhUgOSW6Vk3BJ13eps9SGq3xZt57_I5kaS0QLgQgBLgTOeEBYZpjfvesvi4nNou5VptdXGCH3sC8eOAYmN_1Uuz__-e8bodEOOHuzsZXJp6sLBBgrCe3BXafJbKO9OeG_zzDX8afhd2R23SVlrdoSwlPK0oTCoiUYjDA0jOngmuIh7ZafM-rT4djskARM4-_NFXcRVJQmrrqg' \
--data-raw '{"enabled": true, "username": "Ada", "email": "ada@mail.com", "firstName": "Ada", "lastName": "Lovelace", "credentials": [{"type": "password", "value": "password", "temporary": false}]}'
