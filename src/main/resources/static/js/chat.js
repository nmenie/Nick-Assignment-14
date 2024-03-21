var messageInput = document.getElementById("messageInput");
var messageContainer = document.getElementById("messageContainer");
const submitBtn = document.getElementById('submit-btn')

function sendMessage() {
    console.log(messageInput)
}
if(submitBtn) {

    submitBtn.addEventListener('click', sendMessage);
}

// function sendMessage(message) {
//     console.log('sendMessage is invoked')
//     var username = sessionStorage.getItem("username");
//     var sanitizedMessage = DOMPurify.sanitize(message);
//     var sanitizedUsername = DOMPurify.sanitize(username);

//     fetch("/channels/" + channelId + "/messages", {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json"
//         },
//         body: JSON.stringify({
//             content: sanitizedMessage,
//             senderUsername: sanitizedUsername
//         })
//     })
//     .then(response => {
//         if (!response.ok) {
//             throw new Error("Failed to send message");
//         }
//         // Fetch the updated messages after sending a message
//         getMessages();
//     })
//     .catch(error => {
//         console.error("Error sending message:", error);
//     });
// }

// if (messageInput) {

//     messageInput.addEventListener("keypress", function(event) {
//         if (event.key === "Enter") {
//             var message = event.target.value.trim();
//             sendMessage(message)
//             if (message !== "") {
//                 sendMessage(message);
//                 event.target.value = "";
//             }
//         }
//     });
// }
// document.addEventListener('DOMContentLoaded', function() {
//     var channelId = "{{channelId}}"




//     function displayMessage(message) {
//         console.log(message); // Log the message object to the console
//         var messageElement = document.createElement('div');
//         var sanitizedUsername = DOMPurify.sanitize(message.senderUsername);
//         var sanitizedContent = DOMPurify.sanitize(message.content);
//         messageElement.innerHTML = '<strong>' + sanitizedUsername + ':</strong> ' + sanitizedContent;
//         messageContainer.appendChild(messageElement);
//     }

//     function getMessages() {
//         fetch('/channels/' + channelId + '/messages')
//             .then(response => response.json())
//             .then(messages => {
//                 console.log(messages); // Log the received messages array
//                 // Clear the message container before appending new messages
//                 messageContainer.innerHTML = '';
//                 messages.forEach(message => {
//                     displayMessage(message);
//                 });
//             })
//             .catch(error => {
//                 console.error('Error retrieving messages:', error);
//             });
//     }

//     // Call the getMessages function initially and periodically
//     getMessages();
//     setInterval(getMessages, 5000);
// });
