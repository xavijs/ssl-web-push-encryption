const backendUrlSubscriptionStorage = 'https://localhost:443/subscription';

if ('serviceWorker' in navigator) {

  console.log("service worker present!");
  navigator.serviceWorker.register("./service-worker.js", {scope: './'});

  navigator.serviceWorker.ready.then(
      function (serviceWorkerRegistration) {

        console.log("service worker is readyyyyy");
        serviceWorkerRegistration.pushManager.subscribe({userVisibleOnly: true})
            .then(
                function (pushSubscription) {
                  console.log(JSON.stringify(pushSubscription));
                  sendSubscriptionToBackend(pushSubscription);
                }
            )
      }
  )
}

function sendSubscriptionToBackend (subscription) {

  var xhr = new XMLHttpRequest();
  xhr.open("PUT", backendUrlSubscriptionStorage + "/" + prompt("Insert your name or ID (can be random string)", "XaviJS"), true);

//Send the proper header information along with the request
  xhr.setRequestHeader("Content-type", "application/json;charset=UTF-8");
  xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
  xhr.send(JSON.stringify(subscription));
}
