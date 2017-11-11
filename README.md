
# Progressive webapp with push notifications

## Setup firebase account

 - Create a project in FireBase (you can import a project from Google)
 - Frontend: Put your gcm_sender_id in manifest.json (is your project ID from FireBase)
 - Backend: Specify the PRIVATE_KEY in ApplicationConfiguration (This is the Server Key of Fire Base cloudmessaging)

## Run

- To run in local:
```bash
./gradlew bootRun
```

- To run with Docker:
```bash
./gradlew build && docker-compose up --build
```

## Endpoints

- SSL certificate is self-signed ! To make service worker to run correctly with self-singled certificates, execute Chrome:

```bash

/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --user-data-dir=/tmp/foo --ignore-certificate-errors --unsafely-treat-insecure-origin-as-secure=https://localhost:443

```

- Website to accept notifications: https://localhost:443
- Admin panel: https://localhost:443/admin


## Known issues
 - Server is accepting all Cross Domain requests
 
 - Javascript client is sending the subscription to hardcoded localhost
 
