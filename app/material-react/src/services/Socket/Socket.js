import React, { Component } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stomp-websocket';

class Socket {

    state = {
        connected: false,
    }

    connect = (target, responseHandler) => {
        var socket = new SockJS('http://localhost:3003/iflower');
        var stomp = Stomp.over(socket);
        console.log(stomp);
        this.stompClient = stomp;
        this.stompClient.connect({}, (frame) => {
            this.state.connected = true;
            console.log('Connected: ' + frame);
            this.stompClient.subscribe('/topic/update/' + target, responseHandler);
        });
    }

    disconnect = () => {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        this.state.connected = false;
        console.log("Disconnected");
    }

    send = () => {
        this.stompClient.send("/app/settings/some", {}, JSON.stringify({ 'value': "request" }));
    }
}

export default Socket; 