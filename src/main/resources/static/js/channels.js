
const channelId = '{{channel.id}}';
const channelName = '{{channel.name}}';

const welcomePage = document.querySelector('#welcome-page');
const channelsPage = document.querySelector('#channels-page');
const chatPage = document.querySelector('#chat-page');
const messages = document.querySelector('#messages');
const messageInput = document.querySelector('#message-input');

function sendMessage() {
    const messageText = messageInput.value;
    if (!messageText) {
        return;
    }

    const message = new Message(UUID.generate(), user.id, channelId, messageText);
    messageRepository.addMessage(message);
    messageInput.value = '';
}

function updateMessages() {
    const messagesList = messageRepository.getMessages(channelId);
    messages.innerHTML = '';
    for (const message of messagesList) {
        const messageElement = document.createElement('li');
        messageElement.textContent = `${message.user.name}: ${message.text}`;
        messages.appendChild(messageElement);
    }
}

function joinChannel() {
    welcomePage.style.display = 'none';
    channelsPage.style.display = 'none';
    chatPage.style.display = 'block';
    updateMessages();
    setInterval(updateMessages, 500);
}

messageInput.addEventListener('keydown', event => {
    if (event.key === 'Enter') {
        sendMessage();
    }
});

channels.addEventListener('click', event => {
    if (event.target.tagName === 'A') {
        const channelId = event.target.getAttribute('href').substring(1);
        const channel = channelRepository.getChannel(channelId);
        if (channel) {
            joinChannel();
        }
    }
});

