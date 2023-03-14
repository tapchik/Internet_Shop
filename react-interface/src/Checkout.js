import React from 'react';
import ReactDOM from 'react-dom/client';
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import './index.css';
import './css/index.css';
import './css/buttons.css';
import './css/listofproducts.css';
import './css/checkout.css';


function CartItem(props) {
    return (
        <div className="cart-item-info">
            <div>
                <img src={props.image_path} style={{width:'200px', height:'200px'}}/>
            </div>
            <div>
                <a>
                    {props.description}
                </a>
            </div>
            <div style={{display: 'flex', flexDirection: 'column'}}>
                <div className="price-tag">
                    {props.price}
                </div>
                <div style={{display: 'flex', justifyContent: 'center'}}>
                    <button type="button" className="btn btn-light" name="decrease-qty" value={props.product_id} onClick={props.onClickDecrease}>➖</button>
                    <p className="qty-label" id={props.product_id}>
                        {props.quantity}
                    </p>
                    <button type="button" className="btn btn-light" name="increase-qty" value={props.product_id} onClick={props.onClickIncrease}>➕</button>
                    <button type="button" className="btn btn-light" name="delete-from-cart" value={props.product_id} onClick={props.onClickDelete}>❌</button>
                </div>
            </div>
        </div>
    )
}

function BtnMakeOrder(props) {
    return (
        <div className='cart-counter'>
            <Button variant="primary" size="lg" id="make-order-btn">Make an order, total: {props.order_price}</Button>{' '}
        </div>
    )
}

class Checkout extends React.Component {
    
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
            <Container>
                <h1>Fistashka</h1>
                <div className="cart-items-at-checkout-container">
                    <CartItem image_path="https://i.ebayimg.com/00/s/MTYwMFgxNjAw/z/RkkAAOSwtspdTBqx/$_57.JPG?set_id=8800005007"
                            description='Apple iPhone 10'
                            price='15 000'
                            product_id='iph010'
                            quantity='3'
                            />
                </div>
                <BtnMakeOrder/>
            </Container>
        )
    }
}

// ========================================
  
export default Checkout;