import React from 'react';
import ReactDOM from 'react-dom/client';
import './css/index.css';
import reportWebVitals from './reportWebVitals';
import App from './App';
import Catalogue from './Catalogue';
import Checkout from './Checkout';
//import Routes from './Routes';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";


{/*
import { BrowserRouter } from 'react-router-dom';

ReactDOM.render((
  <BrowserRouter>
    <Routes /> 
</BrowserRouter>
  ), document.getElementById('root')
); */}


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  //<React.StrictMode>
  //  <Catalogue />
  //</React.StrictMode>
  <>
    <Router>
      <Routes>
        <Route exact path="/catalogue" element={<Catalogue />} />
        <Route path="/checkout" element={<Checkout />}/>
      </Routes>
    </Router>

  </>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
