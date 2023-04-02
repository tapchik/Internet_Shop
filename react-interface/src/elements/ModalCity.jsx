import React from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { Form } from 'react-bootstrap';
import InputGroup from 'react-bootstrap/InputGroup';
import Cookies from 'js-cookie';

function ModalCity(props) {
    return (
        <Modal show={props.show} onHide={props.toggleModal}>
            <Modal.Header closeButton>
                <Modal.Title>Pick your city</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <label>Select your city: </label>
                <select class="form-select" name="city-selected" aria-label="Default select example">
                <option value="Not chosen" selected>Not chosen</option>
                <option value="Berezniki">Berezniki</option>
                <option value="Perm">Perm</option>
                <option value="Moscow">Moscow</option>
                <option value="Chaykovsk">Chaykovsk</option>
                <option value="Polazna">Polazna</option>
                <option value="Dobryanka">Dobryanka</option>
                </select>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={props.toggleModal}>
                Close
                </Button>
                <Button variant="primary" onClick={function(e) {Cookies.set('current_city', document.getElementsByName('city-selected')[0].value); props.saveCurrentCity(); props.toggleModal()}}>
                Save Changes
                </Button>
            </Modal.Footer>
        </Modal>
    )
}

export default ModalCity;