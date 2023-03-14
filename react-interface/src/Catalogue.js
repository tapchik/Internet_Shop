import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';


class FilteringForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            filter: '',
            order_by: ''
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        alert('A name was submitted: ' + this.state.value);
        event.preventDefault();
    }
}


class Catalogue extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            history: [{squares: Array(9).fill(null)}],
            stepNumber: 0,
            xIsNext: true,
        }
    }

    render() {
        return (
            <h1>Fistashka</h1>
        )
    }
}

// ========================================
  
export default Catalogue;