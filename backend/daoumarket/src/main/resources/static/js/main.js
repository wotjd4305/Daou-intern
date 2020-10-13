'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var sender = 1;
var receiver = 2;
var chatroom = 1;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
	event.preventDefault();
    sender = document.querySelector('#sender').value.trim();
	receiver = document.querySelector('#receiver').value.trim();
	chatroom = document.querySelector('#chatroom').value.trim();
	
    if(sender) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}

function onConnected() {
//    stompClient.subscribe('/sub/', onMessageReceived);
     stompClient.subscribe('/sub/' + chatroom, onMessageReceived);
     
    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent.length > 0 && stompClient != null) {
        var message = {
            chatroomId: chatroom,
            senderId: sender,
            receiverId: receiver,
            content: messageInput.value,
            sendTime : new Date(),
            receiveTime : null,
        };
        stompClient.send("/pub/message", {}, JSON.stringify(message));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {

    var message = JSON.parse(payload.body);
    var messageElement = document.createElement('li');

	if(message.chatroomId == chatroom){
        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.senderId);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.senderId);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.senderId);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);

        var textElement = document.createElement('p');
        var messageText = document.createTextNode(message.content);
        textElement.appendChild(messageText);

        messageElement.appendChild(textElement);

        messageArea.appendChild(messageElement);
        messageArea.scrollTop = messageArea.scrollHeight;
    }
}

function getAvatarColor(messageSender) {
    var index = Math.abs((31 * messageSender) % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)