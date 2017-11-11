
# Webpush with payload encryption

## Run

```bash
./gradlew bootRun
```

## Endpoints

- Website: https://localhost:8443
- Admin: https://localhost:8443/admin

## Notes

- SSL certificate is self-signed ! To make service worker to run correctly with self-singled certificates, execute Chrome:

```bash

/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --user-data-dir=/tmp/foo --ignore-certificate-errors --unsafely-treat-insecure-origin-as-secure=https://localhost:8443

```
  change localhost:8443 according to your needs.

 - Server is accepting all Cross Domain requests
 
 - Javascript client is sending the subscription to hardcoded localhost

 - You must install the BouncyCastleProvider, installed dynamically in this project
 
## Setup push payload encryption

 - Create a project in FireBase (you can import a project from Google)
 - Frontend: Put your gcm_sender_id in manifest.json (is your project ID from FireBase)
 - Backend: Specify the PRIVATE_KEY (This is the Server Key of Fire Base cloudmessaging)
 