import React from 'react';

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

export default Product;