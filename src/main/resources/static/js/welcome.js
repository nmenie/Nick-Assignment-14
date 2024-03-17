document.addEventListener('DOMContentLoaded', function() {
  function getName() {
    const storedUsername = sessionStorage.getItem("username");
    if (!storedUsername) {
      const username = prompt("What is your name?");
      if (username) {
        sessionStorage.setItem("username", username);
        submitUsername(username);
      }
    } else {
      redirectToChannels();
    }
  }

  function submitUsername(username) {
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/welcome', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE) {
        if (xhr.status === 200) {
          window.location.replace('/channels');
        } else {
          console.error('Error submitting username:', xhr.status);
        }
      }
    };
    xhr.send('username=' + encodeURIComponent(username));
  }

  function redirectToChannels() {
    window.location.replace('/channels');
  }

  getName();
});
  
  

   
  