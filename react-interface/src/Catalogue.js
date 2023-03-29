import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Cookies from 'universal-cookie';
import ReactDOM from 'react-dom/client';
import './css/App.css';
import './css/buttons.css';
import './css/checkout.css';
import './css/index.css';
import './css/listofproducts.css';
import $ from 'jquery';

// adds product to cart
/*
$('body').on('click', '.add-to-cart', function() {
    var product_id = $(this).val().toString();
    var parameters = `?product_id=${product_id}`;
    $.ajax({type: "POST",
        url: '/add_to_cart' + parameters,
        dataType: "text",
        success:function(result) {
            $("#cart-btn").text(result);
    }});
});*/

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

function Product(props) {
    return (
        <div className='product-info'>
            <div>
                <img src={props.image} style={{width:'200px', height:'200px'}}/>
            </div>
            <div>
                <a>{props.title}</a>
            </div>
            <div style={{display: 'flex', flexDirection: 'column'}}>
                <p className='price-tag'>{props.price}</p>
                <button type='button' className='add-to-cart btn btn-success' onClick={props.onClick}>Add to cart</button>
            </div>
        </div>
    )
}

function ButtonGoToCheckout(props) {
    return (
        <div style={{position: 'absolute', right: '0px', bottom: '0px'}}>
            <Button variant='primary' size='lg' className="btn" id='cart-btn' onClick={props.onClick}>
                Items in cart: {props.total_items_in_cart}
            </Button>
        </div>
    )
}

class ModalCity extends React.Component {

    dismissModal = () => { this.props.toggle() }

    render() {
        return (
        <div className={`modal fade WelcomeModal ${this.props.showModal ? 'show' : ''}`}//className="modal fade bd-example-modal-sm" 
            style={{display: `${this.props.showCityPickerModal ? 'block' : 'none'}`,}} 
            id="pick-a-city" tabIndex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div className="modal-dialog modal-sm" role="dialogue">
            <div className="modal-content">
            <div className="modal-header">
                <h5 className="modal-title" id="exampleModalLabel">Pick your city</h5>
                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div className="modal-body">
                <select className="form-select" name="city-selected" aria-label="Default select example">
                <option value="none"defaultValue>Choose your city</option>
                <option value="Berezniki">Berezniki</option>
                <option value="Perm">Perm</option>
                <option value="Moscow">Moscow</option>
                <option value="Chaykovsk">Chaykovsk</option>
                <option value="Polazna">Polazna</option>
                <option value="Dobryanka">Dobryanka</option>
                </select>
            </div>
            <div className="modal-footer">
                <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" className="btn btn-primary" id="save-current-city" data-dismiss="modal" onClick={this.dismissModal}>Save changes</button>
            </div>
            </div>
        </div>
        </div>
        )
    }
}

class Catalogue extends React.Component {
    
    componentDidMount() {
        fetch('http://127.0.0.1:8000/api/products')
            .then(response => response.json())
            .then(data => this.setState({ products: data.products }))
            .catch(error => console.error('Error: ', error))
        fetch('http://127.0.0.1:8000/api/cart-items')
            .then(response => response.json())
            .then(data => this.setState({ total_items_in_cart: data.total_items_in_cart }))
            .catch(error => console.error('Error: ', error))
    }

    constructor(props) {
        super(props);
        this.state = {
            history: [{squares: Array(9).fill(null)}],
            products: [],
            total_items_in_cart: 0,
            stepNumber: 0,
            current_city: 'Not chosen',
            xIsNext: true,
            showCityPickerModal: false,
        }
    }

    btnAddToCartClicked(product_id) {
        fetch('http://127.0.0.1:8000/api/add-to-cart?product_id='+product_id, {
            method: 'POST'})
            .then(response => response.json())
            .then(data => this.setState({ total_items_in_cart: data.total_items_in_cart }))
    }

    toggleModal = () => this.setState({
        showCityPickerModal: !this.state.showCityPickerModal
    })

    render() {
        return (
            <Container>
            <h1>Fistashka</h1>

            <ModalCity toggle={this.toggleModal} showCityPickerModal={this.state.showCityPickerModal}/>
            
            <div className='city-picker'>
                <p id="interface-current-city" style={{margin: 0}}>{this.state.current_city}</p>
                <Button variant='primary' style={{marginLeft: '10px'}} onClick={this.toggleModal}>Change city</Button>
            </div>

            <div className='list-of-products-container'>
            {this.state.products.map((product) => (
                <Product image={product.image} title={product.title} id={product.id} price={product.price_beautiful} key={product.id} onClick={() => this.btnAddToCartClicked(product.id)} />
            ))}
            </div>
            <ButtonGoToCheckout total_items_in_cart={this.state.total_items_in_cart} onClick={() => window.location.assign("/checkout")}/>
            </Container>
        )
    }
}

// ========================================
  
export default Catalogue;