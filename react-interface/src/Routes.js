import React from 'react';
import { Routes, Route } from 'react-router-dom';

import Catalogue from './Catalogue';
//import Signup from '../pages/Signup';

export default (
  <Routes> 
      <Route path='/catalogue' element={Catalogue}></Route>
  </Routes>
);