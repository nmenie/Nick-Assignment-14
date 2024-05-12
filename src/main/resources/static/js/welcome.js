function getName() {
    const storedUsername = sessionStorage.getItem("username");
    if (!storedUsername) {
      const username = prompt("What is your name?");
      if (username) {
        sessionStorage.setItem("username", username);
        console.log("Username has been saved to sessionStorage");
        document.getElementById("usernameInput").value = username;
        document.getElementById("usernameForm").submit();
      }
    } else {
      document.getElementById("usernameInput").value = storedUsername;
      document.getElementById("usernameForm").submit();
    }
  }
  
  getName();




  

   
  