  document.getElementById("btn-login").addEventListener("click", function() {
         login(); // Appelle la fonction de connexion lorsque le bouton est cliqué
     });

  function login() {
       var login = document.getElementById("login").value;
       var password = document.getElementById("password").value;

       var xhr = new XMLHttpRequest();
       xhr.open("POST", "/login", true);
       xhr.setRequestHeader('Content-Type', 'application/json');
       xhr.onreadystatechange = function() {
       if (xhr.readyState === 4) {
                 if (xhr.status === 200) {
                     // Connexion réussie
                     window.location.href = "/Dashboard"; // Redirection vers la page home.html
                 } else {
                     // Connexion échouée
                 var errorMessageElement = document.getElementById("errorMessage");
                                 errorMessageElement.textContent = xhr.responseText;
                 }
             }
         };
       var data = JSON.stringify({
         "login": login,
         "password": password
       });
       xhr.send(data);
     }
