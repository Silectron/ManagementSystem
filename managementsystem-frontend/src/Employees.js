import React, { Component } from 'react';
import Navigation from './Navigation';
import './App.css';
import {Container, Button, Form, FormGroup, Input, Label, Row} from 'reactstrap';
import {Link} from 'react-router-dom';

class Employees extends Component {
    state = {
        isLoading: true,
        Employees: []

    }

    async componentDidMount() {
        const response = await fetch('/employees');
        const body= await response.json();
        this.setState({Employees : body, isLoading : false});
    }

    async handleSubmit() {
        
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

        return (
            <div>
                <Navigation/>
                <h2 style={{display:'flex', justifyContent:'center', alignItems:'center', height: '10vh'}}>Employees</h2>

                {   
                    Employees.map( employee =>
                        <div id={employee.name}>{employee.name}</div>
                    )
                }
                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <Label for="name">Full Name</Label>
                            <Input type="text" name="title" id="name" onChange={this.handleChange}/>
                        </FormGroup>

                        <FormGroup>
                            <Button color="primary" type="submit">Add Employee</Button>
                            <Row></Row>
                            <Button color="secondary" size="sm" tag={Link} to="/projects">Cancel</Button>
                        </FormGroup>

                    </Form>
                </Container>
            </div>
        );
    }
}

export default Employees