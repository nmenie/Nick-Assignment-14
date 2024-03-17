document.addEventListener('DOMContentLoaded', function() {
    var channelId = null;
    var lastMessageId = 0;

    function pollMessages() {
        fetch('/channels/' + channelId + '/messages?lastMessageId=' + lastMessageId)
            .then(response => response.json())
            .then(data => {
                data.forEach(message => {
                    var chatWindow = document.getElementById('chat-window');
                    var messageElement = document.createElement('div');
                    messageElement.textContent = message.senderUsername + ': ' + message.content;
                    chatWindow.appendChild(messageElement);
                    lastMessageId = Math.max(lastMessageId, message.id);
                });
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    function startPolling(channel) {
        channelId = channel.id;
        // Start polling for new messages every 500 milliseconds
        setInterval(pollMessages, 500);
    }

    // Export the startPolling function
    window.startPolling = startPolling;
});