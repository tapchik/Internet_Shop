import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Cookies from 'js-cookie';
//import Cookies from 'universal-cookie';
import ReactDOM from 'react-dom/client';
import './css/App.css';
import './css/buttons.css';
import './css/checkout.css';
import './css/index.css';
import './css/listofproducts.css';
import $ from 'jquery';

import Product from './elements/Product';
import FilteringForm from './elements/FilteringForm';
import ModalCity from './elements/ModalCity';

function ButtonGoToCheckout(props) {
    return (
        <div style={{position: 'absolute', right: '0px', bottom: '0px'}}>
            <Button variant='primary' size='lg' className="btn" id='cart-btn' onClick={props.onClick}>
                Items in cart: {props.total_items_in_cart}
            </Button>
        </div>
    )
}

class Catalogue extends React.Component {
    
    componentDidMount() {
        this.redrawCurrentCity()
        Cookies.set('sessionid', 'k435hk345kxkh42l1');
        fetch('http://localhost:8000/api/products')
            .then(response => response.json())
            .then(data => this.setState({ products: data.products }))
            .catch(error => console.error('Error: ', error));
        fetch('http://localhost:8000/api/cart-items', {
                method: 'GET',
                credentials: 'include',
            })
            .then(response => response.json())
            .then(data => this.setState({ 
                total_items_in_cart: data.total_items_in_cart}))
            .catch(error => console.error('Error: ', error))
    }

    constructor(props) {
        super(props);
        this.state = {
            products: [],
            total_items_in_cart: 0,
            current_city: 'Not chosen',
            showModalCity: false,
        }
    }

    refreshListOfProducts() {
        //e.preventDefault();
        let filter = document.getElementsByName('filter')[0].value;
        let sort = document.getElementsByName('sort')[0].value;
        fetch('http://localhost:8000/api/products?filter='+filter+'&sort='+sort, {
                method: 'GET',
                credentials: 'include',
            })
            .then(response => response.json())
            .then(data => this.setState({ products: data.products }))
            .catch(error => console.error('Error: ', error));
    }

    btnAddToCartClicked(product_id) {
        fetch('http://localhost:8000/api/add-to-cart?product_id='+product_id, {
                method: 'POST',
                //mode: "cors",
                credentials: 'include', //same-origin
                //body: {'sessionid': Cookies.get('sessionid')}
            })
            .then(response => response.json())
            .then(data => this.setState({ 
                total_items_in_cart: data.total_items_in_cart,
            }))
    }

    toggleModal = () => this.setState({showModalCity: !this.state.showModalCity})

    redrawCurrentCity() {
        this.setState({ current_city: Cookies.get('current_city') })
    }

    render() {
        return (
            <Container>
            <h1>Fistashka</h1>
            <p>{Cookies.get('sessionid')}</p>

            <ModalCity toggleModal={this.toggleModal} show={this.state.showModalCity} saveCurrentCity={() => this.redrawCurrentCity()}/>

            <div className='city-picker'>
                <p id="interface-current-city" style={{margin: 0}}>{this.state.current_city}</p>
                <Button variant='outline-secondary' style={{marginLeft: '10px'}} onClick={this.toggleModal}>Change city</Button>
            </div>

            <FilteringForm onClick={() => this.refreshListOfProducts()}/>

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