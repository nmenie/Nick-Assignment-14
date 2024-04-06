const messageInput = document.getElementById('messageInput');





let channelId = null;
let lastMessageId = 0;
let username = null;

async function sendMessage(event) {
    event.preventDefault();
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value.trim();
    if (message !== '') {
        try {
            const response = await fetch(`/channels/${channelId}/messages`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ content: message }),
            });
            if (response.ok) {
                messageInput.value = '';
                await pollMessages();
            } else {
                console.error('Error sending message:', response.status);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }
}

async function pollMessages() {
    try {
        const response = await fetch(`/channels/${channelId}`);
        if (response.ok) {
            const data = await response.json();
            const newMessages = data.messages.filter(message => message.id > lastMessageId);
            const messageContainer = document.getElementById('messageContainer');
            newMessages.forEach(message => {
                const messageElement = document.createElement('div');
                messageElement.innerHTML = `<strong>${username}</strong>: ${message.content}`;
                messageContainer.appendChild(messageElement);
                lastMessageId = Math.max(lastMessageId, message.id);
            });
        } else {
            console.error('Error fetching messages:', response.status);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

function startPolling(channel, currentUsername) {
    channelId = channel.id;
    username = currentUsername;
    lastMessageId = channel.messages.reduce((maxId, message) => Math.max(maxId, message.id), 0);
    setInterval(pollMessages, 5000);
}

window.startPolling = startPolling;
