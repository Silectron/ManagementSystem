import React, { Component } from "react";
import Navigation from './Navigation';
import DatePicker from 'react-datepicker';
import './App.css';
import 'react-datepicker/dist/react-datepicker.css';
import { Container, Button, Form, FormGroup, Label, Input, Row, Col, Table} from "reactstrap";
import { Link } from 'react-router-dom';
import Axios from "axios";

class Project extends Component {

    constructor(props) {
        super(props) 

        this.state = {
            isLoading: true,
            Projects: [],
            startDate: new Date(),
            endDate: new Date(Date.now() + 12096e5),
            packet: this.createProject
        }
    }
    

    createProject = {
        name: '',
        status: '',
        startDate: '',
        endDate:'',
        client:''
    }

    async componentDidMount() {
        const response = await fetch('/projects');
        const body = await response.json();
        this.setState({Projects : body, isLoading : false});
    
    
    }

    async handleSubmit() {
    }

    handleStartDateChange = date => {
        this.setState({
            startDate: date
        })
    }

    handleEndDateChange = date => {
        this.setState({
            endDate: date
        })
    }

    render() {
        const{Projects, isLoading} = this.state;
        const title = <h4>Create New Project</h4>;
        if(isLoading) {
            return( <div>
                        <h2 style={{display:'flex', justifyContent:'center', alignItems:'center', height: '50vh'}}>
                            Retrieving data...
                        </h2>
                    </div>);
        }

        let rows = Projects.map( (project) =>
                <tr>
                    <td>{project.name}</td>
                    <td>{project.client}</td>
                    <td>{project.startDate}</td>
                    <td>{project.endDate}</td>
                    <td>{project.status}</td>
                </tr>
            )
            return(
                <div>
                    <Navigation/>
                    
                    
                    <Container>
                        <h2 style={{display:'flex', justifyContent:'center', alignItems:'center', height: '10vh'}}>Projects</h2> 
                    
                        <Table className="mt-4">
                            <thead>
                                <tr>
                                    <th width='20%'>Project Name</th>
                                    <th width='15%'>Client</th>
                                    <th width='25%'>Start Date</th>
                                    <th width='25%'>Deadline</th>
                                    <th width='10%'>Status</th>
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
                            <Row form>
                                <Col md={8}>
                                    <FormGroup>
                                        <Label for="name">Project Name</Label>
                                        <Input type="text" name="name" id="name" onChange={this.handleChange}></Input>
                                    </FormGroup>
                                </Col>
                                <Col md={3}>
                                    <FormGroup>
                                        <Label for="status">Status</Label>
                                        <Input type="text" name="status" id="status" onChange={this.handleChange} autoComplete="status"></Input>
                                    </FormGroup>
                                </Col>
                            </Row>
                            <Row form>
                                <Col md={3}>
                                    <FormGroup>
                                        <Label for="startDate">Start Date</Label>
                                        <DatePicker selected={this.state.startDate} onChange={this.handleStartDateChange}/>
                                    </FormGroup>
                                </Col>
                                <Col md={3}>
                                    <FormGroup>
                                        <Label for="endDate">End Date</Label>
                                        <DatePicker selected={this.state.endDate} onChange={this.handleEndDateChange}/>
                                    </FormGroup>
                                </Col>
                            </Row>

                            <div className="row">
                                <FormGroup className="col-md-6 mb-3">
                                    <Label for="client">Client</Label>
                                    <Input type="text" name="client" id="client" onChange={this.handleChange} autoComplete="client"></Input>
                                </FormGroup>
                            </div>

                            <FormGroup>  
                                <Button color="primary" tag={Link} to="/" type="submit">Add Project</Button>
                                <Row></Row>
                                <Button color="secondary" size = "sm" tag={Link} to="/projects">Cancel</Button>
                            </FormGroup>


                        </Form>
                    </Container>
                </div>
            );
        
    }
}

export default Project;