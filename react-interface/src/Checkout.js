import React from 'react';
import ReactDOM from 'react-dom/client';
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import Cookies from 'js-cookie';
import './index.css';
import './css/index.css';
import './css/buttons.css';
import './css/listofproducts.css';
import './css/checkout.css';

import CartItem from './elements/CartItem';
import ModalCity from './elements/ModalCity';


function BtnMakeOrder(props) {
    return (
        <div className='cart-counter'>
            <Button 
                variant="primary"
                size="lg"
                id="make-order-btn"
                onClick={props.onClick}>Make an order, total: {props.total_price}
            </Button>
        </div>
    )
}

class Checkout extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            cart_items: [],
            total_order_price_beautiful: '0',
            current_city: 'Not chosen',
            showModalCity: false,
        }
    }

    componentDidMount() {
        this.refreshListOfCartItems()
        this.redrawCurrentCity();
        let city = Cookies.get('current_city')
        if (!city) {
            this.toggleModal()
        }
    }

    toggleModal = () => this.setState({showModalCity: !this.state.showModalCity})

    redrawCurrentCity() {
        this.setState({ current_city: Cookies.get('current_city') })
    }

    refreshListOfCartItems() {
        fetch('http://localhost:8000/api/cart-items', {
                method: 'GET',
                credentials: 'include',
            })
            .then(response => response.json())
            .then(data => this.setState({ 
                cart_items: data.cart_items,
                total_order_price_beautiful: data.total_order_price_beautiful }))
            .catch(error => console.error('Error: ', error))
    }

    btnPlusClicked(product_id) {
        fetch('http://localhost:8000/api/add-to-cart?product_id='+product_id, {
            method: 'POST',
            credentials: 'include',
        })
        .then(response => response.json())
        .then(data => this.setState({ 
            cart_items: data.cart_items,
            total_order_price_beautiful: data.total_order_price_beautiful }))
        .catch(error => console.error('Error: ', error))
    }

    btnMinusClicked(product_id) {
        fetch('http://localhost:8000/api/cart-decrease?product_id='+product_id, {
            method: 'POST',
            credentials: 'include',
        })
        .then(response => response.json())
        .then(data => this.setState({ 
            cart_items: data.cart_items,
            total_order_price_beautiful: data.total_order_price_beautiful }))
        .catch(error => console.error('Error: ', error))
    }

    btnRemoveClicked(product_id) {
        fetch('http://localhost:8000/api/cart-remove?product_id='+product_id, {
            method: 'POST',
            credentials: 'include',
        })
        .then(response => response.json())
        .then(data => this.setState({ 
            cart_items: data.cart_items,
            total_order_price_beautiful: data.total_order_price_beautiful }))
        .catch(error => console.error('Error: ', error))
    }

    btnOrderClicked() {
        let current_city = Cookies.get('current_city')
        alert('Your order is placed and will arive to city '+current_city+' in a week!')
        fetch('http://localhost:8000/api/make-order', {
            method: 'POST',
            body: JSON.stringify({ city: current_city }),
            credentials: 'include',
        })
        window.location.assign("/catalogue")
    }

    render() {
        return (
            <Container>
                <h1>Fistashka</h1>

                <ModalCity toggleModal={this.toggleModal} show={this.state.showModalCity} saveCurrentCity={() => this.redrawCurrentCity()}/>

                <div className='city-picker' style={{marginBottom: '20px'}}>
                    <p id="interface-current-city" style={{margin: 0}}>{this.state.current_city}</p>
                    <Button variant='outline-secondary' style={{marginLeft: '10px'}} onClick={this.toggleModal}>Change city</Button>
                </div>

                <div className="cart-items-at-checkout-container">
                    {this.state.cart_items.map((cart_item) => (
                        <CartItem 
                            title={cart_item.title} 
                            image_path={cart_item.image}
                            product_id={cart_item.product_id}
                            price={cart_item.price_beautiful}
                            quantity={cart_item.quantity}
                            key={cart_item.product_id} 
                            btnPlusClicked={() => this.btnPlusClicked(cart_item.product_id)} 
                            btnMinusClicked={() => this.btnMinusClicked(cart_item.product_id)} 
                            btnRemoveClicked={() => this.btnRemoveClicked(cart_item.product_id)} />
                    ))}
                </div>
                <BtnMakeOrder total_price={this.state.total_order_price_beautiful} onClick={() => this.btnOrderClicked()} />
            </Container>
        )
    }
}

// ========================================
  
export default Checkout;