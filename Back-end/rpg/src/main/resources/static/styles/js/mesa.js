function funcaoJs() { // funcaoJs(userId)
    var httpRequest;
    var text;
    var codigoMesa = prompt("Digite o codigo da Mesa para entrar:");
    var userId = document.getElementById("userId").value;
    if (codigoMesa != null) {
            makeRequest('http://localhost:8080/entrando_na_mesa', userId, codigoMesa);

        }
    }

    function makeRequest(url, id, codigo) {
        if (window.XMLHttpRequest) {
          httpRequest = new XMLHttpRequest();
        } else if (window.ActiveXObject) { // IE
          try {
            httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
          }
          catch (e) {
            try {
              httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch (e) {}
          }
        }

        if (!httpRequest) {
          alert('Giving up :( Cannot create an XMLHTTP instance');
          return false;
        }
        var param = {
            "codigoMesa" : codigo,
            "userId" : id
        }
        httpRequest.onreadystatechange = alertContents;
        httpRequest.open('POST', url);
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-unlencoded')
        httpRequest.setRequestHeader('codigoMesa', codigo)
        httpRequest.setRequestHeader('userId', id)
        httpRequest.send();
      }

      function alertContents() {
        if (httpRequest.readyState === 4) {
          if (httpRequest.status === 200) {
            var response = JSON.parse(httpRequest.responseText);
            aler("Requisicao concluida, recarregue a pagina")
          } else {
            alert('There was a problem with the request.');
          }
        }
      }



/*

if (codigoMesa != null) {
        makeRequest('http://localhost:8080/entrando_na_mesa', userId, codigoMesa);

    }
}

function makeRequest(url, id, codigo) {
    if (window.XMLHttpRequest) {
      httpRequest = new XMLHttpRequest();
    } else if (window.ActiveXObject) { // IE
      try {
        httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
      }
      catch (e) {
        try {
          httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (e) {}
      }
    }

    if (!httpRequest) {
      alert('Giving up :( Cannot create an XMLHTTP instance');
      return false;
    }
    httpRequest.onreadystatechange = alertContents;
    httpRequest.open('POST', url);
    httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-unlencoded')
    httpRequest.send('userId=' + encodeURIComponent(id), 'codigoMesa=' + encodeURIComponent(codigo));
  }

  function alertContents() {
    if (httpRequest.readyState === 4) {
      if (httpRequest.status === 200) {
        var response = JSON.parse(httpRequest.responseText);
        aler(response.computedString)
      } else {
        alert('There was a problem with the request.');
      }
    }
  }

var httpRequest;
  document.getElementById("ajaxButton").onclick = function() {
  makeRequest('http://localhost:8080/');
  };





case "Martini":
      text = "Excellent choice! Martini is good for your soul.";
      break;
    case "Daiquiri":
      text = "Daiquiri is my favorite too!";
      break;
    case "Cosmopolitan":
      text = "Really? Are you sure the Cosmopolitan is your favorite?";
      break;
    default:
      text = "I have never heard of that one..";
      break;
*/