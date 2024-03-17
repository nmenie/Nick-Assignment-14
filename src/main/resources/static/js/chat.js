document.addEventListener('DOMContentLoaded', function() {
    var messageInput = document.getElementById("messageInput");
    var channelId = channel.id; // Get the channel ID from the channel object

    messageInput.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            var message = event.target.value.trim();
            if (message !== "") {
                sendMessage(message);
                event.target.value = "";
            }
        }
    });

    function sendMessage(message) {
        var username = sessionStorage.getItem("username");
        var sanitizedMessage = message; // Sanitize the message if needed
        var sanitizedUsername = username; // Sanitize the username if needed

        fetch("/channels/" + channelId + "/messages", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                content: sanitizedMessage,
                senderUsername: sanitizedUsername
            })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to send message");
            }
        })
        .catch(error => {
            console.error("Error sending message:", error);
        });
    }
});
