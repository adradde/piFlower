import React, { Component } from 'react';
import './App.css';

class App extends Component {

	state = {
		flowers: [],
		isLoading: false
	}

	registerWebSocket(){

		var WebSocketClient = require('websocket').client;
		var client = WebSocketClient({});

		client.on('connect', function(connection) {
            console.log('WebSocket Client Connected');
            connection.on('error', function(error) {
                console.log("Connection Error: " + error.toString());
            });
            connection.on('close', function() {
                console.log('echo-protocol Connection Closed');
            });
            connection.on('message', function(message) {
                if (message.type === 'utf8') {
                    console.log("Received: '" + message.utf8Data + "'");
                }
            });

            function sendNumber() {
                if (connection.connected) {
                    var number = Math.round(Math.random() * 0xFFFFFF);
                    connection.sendUTF(number.toString());
                    setTimeout(sendNumber, 1000);
                }
            }
            sendNumber();
        });
	}

	componentDidMount(){
//		this.registerWebSocket()

		this.setState({isLoading: true});

          fetch('http://localhost:3003/flowers')
            .then(response => response.json())
            .then(data => this.setState({flowers: data, isLoading: false}));
	}

  render() {

  	if(this.state.isLoading){
  		return <p> App is loading </p>
  	}

    return(
    	<ul>{
			this.state.flowers.map( it => {
				return (<li>
					<h3>{it.name}</h3>
					<p>Color: {it.color}</p>
					<p>{it.lastUpdate}</p>
				</li>)
			})
    		}
    	</ul>
    );
  }
}

export default App;
