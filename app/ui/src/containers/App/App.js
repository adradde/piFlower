import React, { Component } from 'react';
import Hoc from '../Hoc/Hoc'
import Layout from '../../components/Layout/Layout'


import './App.css';

class App extends Component {

    state = {
        flowers: [],
        isLoading: false
    }


    componentDidMount() {

        this.setState({ isLoading: true });

        fetch('http://localhost:3003/flowers')
            .then(response => response.json())
            .then(data => this.setState({ flowers: data, isLoading: false }));
    }

    render() {

        if (this.state.isLoading) {
            return <p> App is loading </p>
        }

        return (
            <Hoc>
                <Layout />
            </Hoc>
        );
    }
}

export default App;
