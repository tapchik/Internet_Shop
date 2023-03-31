import React from 'react';
import Button from 'react-bootstrap/Button';
import InputGroup from 'react-bootstrap/InputGroup';
import { Form } from 'react-bootstrap';

/*
class FilteringForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            filter: 'Apply filter',
            sort: 'none',
        }
    } */

function FilteringForm(props) {
    return (
        /*
        <Form className='filteringform-container' onSubmit={(e) => {this.props.onSubmit(e, this.state.filter, this.state.sort)}}>
            <InputGroup className="mb-3">
                <InputGroup.Text id="basic-addon1">Filter</InputGroup.Text>
                <Form.Control name='filter'
                placeholder="Apply filter" onChange={(e) => {this.state.filter = e.target.value}}
            />
            <Form.Select name='sort' aria-label="Default select example">
                <option value='none'>Don't sort</option>
                <option value='title-asc'>Title asc ➚</option>
                <option value='title-desc'>Title desc ➘</option>
                <option value='price-asc'>Price asc ➚</option>
                <option value='price-desc'>Price desc ➘</option>
            </Form.Select>
        </InputGroup>
            <button type="submit" className="btn btn-light" id="submit-btn">Apply</button>
        </Form>*/
        <div className='filteringform-container'>
        <InputGroup className="mb-3">
                <InputGroup.Text id="basic-addon1">Filter</InputGroup.Text>
                <Form.Control name='filter'
                placeholder="Apply filter"
            />
            <Form.Select name='sort' aria-label="Default select example">
                <option value='none'>Don't sort</option>
                <option value='title-asc'>Title asc ➚</option>
                <option value='title-desc'>Title desc ➘</option>
                <option value='price-asc'>Price asc ➚</option>
                <option value='price-desc'>Price desc ➘</option>
            </Form.Select>
            <Button variant='outline-secondary' onClick={props.onClick}>Apply</Button>
        </InputGroup>
        </div>
    )
}

export default FilteringForm;