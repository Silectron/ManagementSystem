import React, { Component } from 'react';
import Navigation from './Navigation';
import './App.css';
import {Container, Button, Form, FormGroup, Input, Label, Row, Table} from 'reactstrap';
import {Link} from 'react-router-dom';
import Axios from "axios";

class Employees extends Component {

    constructor(props) {
        super(props)

        this.state = {
            isLoading: true,
            Employees: [],
            name: ''
        }

        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleNameChange = this.handleNameChange.bind(this)
        this.handleLeadSubmit = this.handleLeadSubmit.bind(this)
    }

    async componentDidMount() {
        const response = await fetch('/employees');
        const body = await response.json();
        this.setState({Employees : body, isLoading : false});
    }

    handleSubmit() {
        const params = new URLSearchParams();
        params.append("name", this.state.name)
        Axios.post(`/employees/` +this.state.name, params)
            .then(() => {
                this.componentDidMount();
            }).catch(function (e) {
                console.log(e)
            })
    }

    handleLeadSubmit() {
        const params = new URLSearchParams();
        params.append("name", this.state.name)
        Axios.post(`/leads/` +this.state.name, params)
            .then(() => {
                this.componentDidMount();
            }).catch(function (e) {
                console.log(e)
            })
    }

    handleNameChange(e) {
        this.setState({name : e.target.value})
    }

    render() {
        const{Employees, isLoading} = this.state;
        const title = <h4>Add Employee</h4>;
        
        if(isLoading) {
            return( <div>
                        <h2 style={{display:'flex', justifyContent:'center', alignItems:'center', height: '50vh'}}>
                            Retrieving data...
                        </h2>
                    </div>);
        }
     
        let rows = Employees.map( (employee) =>
            <tr>
                <td>{employee.name}</td>
            </tr>
        )

        return (
            <div>
                <Navigation/>

                <Container>
                        <h2 style={{display:'flex', justifyContent:'center', alignItems:'center', height: '10vh'}}>Employees</h2> 
                    
                        <Table striped className="mt-4">
                            <thead>
                                <tr>
                                    <th width='20%'>Name</th>
                                </tr> 
                            </thead>
                            <tbody>
                                {rows}
                            </tbody>
                        </Table>
                    </Container>

                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <Label for="name">Full Name</Label>
                            <Input type="text" value={this.state.name} onChange={this.handleNameChange}/>
                        </FormGroup>

                        <FormGroup>
                            <Button color="primary" type="submit">Add Employee</Button>
                            <Row></Row>
                            <Button color="danger" size="sm" onClick={this.handleLeadSubmit}>Add Employee as Lead</Button>
                            <Row></Row>
                            <Button color="secondary" size="sm" tag={Link} to="/">Cancel</Button>
                        </FormGroup>

                    </Form>
                </Container>
            </div>
        );
    }
}

export default Employees