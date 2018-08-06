import React, { Component } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stomp-websocket';
import Button from '@material-ui/core/Button'

import Hoc from '../../containers/Hoc/Hoc'



class Socket extends Component {

    constructor(props) {
        super(props)
    }

    state = {
        connected: false,
        responses: [],
        requests: []
    }

    componentDidMount() {
        // this.connect()
    }

    componentWillUnmount() {
        // this.disconnect()
    }

    render() {
        return (
            <div>
                <p>Socket status: {this.state.connected ? 'Connected' : 'Not Connected'}</p>
                <button onClick={this.connect}>Connect WebSocket</button>
                <button onClick={this.disconnect}>Disconnect WebSocket</button>
                <hr />
                {
                    this.state.responses.reverse().map((it, index) => (
                        <Hoc key={index}>
                            {/* <p key={"req" + index}>Request: {it}</p> */}
                            <p key={"res" + index}>Response: {this.state.responses.reverse()[index]}</p>
                        </Hoc>
                    ))
                }
                <input
                    value={this.state.request}
                    ref={(inp) => this.messageInput = inp}
                    disabled={this.state.connected === false}
                ></input>
                <Button 
                color="primary"
                variant="contained"
                    onClick={this.send}
                    disabled={this.state.connected === false}
                >Set "some" setting</Button>
                <hr/>
                <input key="2"
                    value={this.state.request}
                    ref={(inp) => this.messageInputFlex = inp}
                    disabled={this.state.connected === false}
                ></input>
                <Button key="2"
                color="primary"
                variant="contained"
                    onClick={this.sendFlex}
                    disabled={this.state.connected === false}
                >Set flex setting</Button>

            </div>
        );
    }

    connect = () => {
        var socket = new SockJS('http://localhost:3003/iflower');
        var stomp = Stomp.over(socket);
        console.log(stomp);
        this.stompClient = stomp;
        this.stompClient.connect({}, (frame) => {
            this.setState({ connected: true });
            console.log('Connected: ' + frame);
            this.stompClient.subscribe('/topic/update', (status) => {
                const responses = [...this.state.responses]
                const response = JSON.parse(status.body).message
                console.log(response)
                responses.push(response)
                this.setState({ responses: responses });
            });
        });
    }

    disconnect = () => {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        this.setState({ connected: false });
        console.log("Disconnected");
    }

    send = () => {
        const requests = [...this.state.requests]
        const request = this.messageInput.value
        requests.push(request)

        this.stompClient.send("/app/settings/some", {}, JSON.stringify({ 'value': request }));
        this.setState({ requests: requests })
    }

    sendFlex = () => {
        const requests = [...this.state.requests]
        const request = this.messageInputFlex.value
        requests.push(request)

        this.stompClient.send("/app/settings/flex/some", {}, JSON.stringify({ 'value': request }));
        this.setState({ requests: requests })
    }

}

export default Socket; 