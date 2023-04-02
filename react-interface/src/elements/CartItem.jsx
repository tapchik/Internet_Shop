import React from 'react';
import Button from 'react-bootstrap/Button';

function CartItem(props) {
    return (
        <div className="cart-item-info">
            <div>
                <img src={props.image_path} style={{width:'200px', height:'200px'}}/>
            </div>
            <div>
                <a>
                    {props.title}
                </a>
            </div>
            <div style={{display: 'flex', flexDirection: 'column'}}>
                <div className="price-tag">
                    {props.price}
                </div>
                <div style={{display: 'flex', justifyContent: 'center'}}>
                    <Button variant='light' onClick={props.btnMinusClicked}>➖</Button>
                    <p className="qty-label"> {props.quantity} </p>
                    <Button variant='light' onClick={props.btnPlusClicked}>➕</Button>
                    <Button variant='light' onClick={props.btnRemoveClicked}>❌</Button>
                </div>
            </div>
        </div>
    )
}

export default CartItem;