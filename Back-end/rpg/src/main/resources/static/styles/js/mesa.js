function funcaoJs() {
    var httpRequest;
    var text;
    var codigoMesa = prompt("Digite o codigo da Mesa para entrar:");
    if (codigoMesa != null) {
        makeRequest('http://localhost:8080/', );

    }
}

function makeRequest(url) {
    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
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
    httpRequest.open('GET', url);
    httpRequest.send();
  }

  function alertContents() {
    if (httpRequest.readyState === 4) {
      if (httpRequest.status === 200) {
        alert(httpRequest.responseText);
      } else {
        alert('There was a problem with the request.');
      }
    }
  }

/*
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