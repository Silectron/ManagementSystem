import React, { Component } from 'react';
import Navigation from './Navigation';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import {Container, Button, Form, FormGroup, Label, Input, Row, Col, Table} from 'reactstrap';
import {Link} from 'react-router-dom';
import Axios from 'axios';

class Project extends Component {

    constructor(props) {
        super(props) 

        this.state = {
            isLoading: true,
            Projects: [],
            startDate: new Date(),
            endDate: new Date(Date.now() + 12096e5),
            name: '',
            status: '',
            client: ''
        }

        this.handleNameChange = this.handleNameChange.bind(this)
        this.handleStatusChange = this.handleStatusChange.bind(this)
        this.handleClientChange = this.handleClientChange.bind(this)
        this.handleStartDateChange = this.handleStartDateChange.bind(this)
        this.handleEndDateChange = this.handleEndDateChange.bind(this)
        this.handleSubmit=this.handleSubmit.bind(this)
        
    }

    async componentDidMount() {
        const response = await fetch('/projects');
        const body = await response.json();
        this.setState({Projects : body, isLoading : false,});
    
    }

    handleNameChange(e) {
        this.setState({name: e.target.value})
    }

    handleStatusChange(e) {
        this.setState({status: e.target.value})
    }

    handleClientChange(e) {
        this.setState({client: e.target.value})
    }

    handleStartDateChange = date => {
        this.setState({startDate: date})
    }

    handleEndDateChange = date => {
        this.setState({endDate: date})
    }

    handleSubmit = event => {
        event.preventDefault();

        let year = this.state.startDate.getFullYear();
        let month = this.state.startDate.getMonth() + 1;
        let day = this.state.startDate.getDate();
        let newStart = year + '-' + month + '-' + day;
        year = this.state.endDate.getFullYear();
        month = this.state.endDate.getMonth() + 1;
        day = this.state.endDate.getDate();
        let newEnd = year + '-' + month + '-' + day

        const params = new URLSearchParams();
        params.append("name", this.state.name)
        params.append("status", this.state.status)
        params.append("startDate", newStart)
        params.append("endDate", newEnd)
        params.append("client", this.state.client)
        Axios.post(`/projects/` +this.state.name, params)
            .then(() => {
                this.componentDidMount();
            }).catch(function (e) {
                console.log(e)
            })
        
    }

    render() {
        const{isLoading, Projects} = this.state;
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

        return (
            <div>
                <Navigation/>                        
                
                <Container>
                    <h2 style={{display:'flex', justifyContent:'center', alignItems:'center', height: '10vh'}}>Projects</h2> 
                    
                    <Table striped className="mt-4">
                        <thead>
                            <tr>
                                <th width='20%'>Project Name</th>
                                <th width='15%'>Client</th>
                                <th width='25%'>Start Date</th>
                                <th width='25%'>Deadline</th>                                    <th width='10%'>Status</th>
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
                                <Col md={6}>
                                    <FormGroup>
                                        <Label for="name">Project Name</Label>
                                        <Input type="text" value={this.state.name} onChange={this.handleNameChange}></Input>
                                    </FormGroup>
                                </Col>
                                <Col md={4}>
                                    <FormGroup>
                                        <Label for="status">Status</Label>
                                        <Input type="text" value={this.state.status} onChange={this.handleStatusChange}></Input>
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

                            <Row form>
                                <Col md={5}>
                                <FormGroup>
                                    <Label for="client">Client</Label>
                                    <Input type="text" value={this.state.client} onChange={this.handleClientChange}></Input>
                                </FormGroup>
                                </Col>
                            </Row>

                            <FormGroup>  
                                <Button color="primary" type="submit">Add Project</Button>
                                <Row></Row>
                                <Button color="secondary" size = "sm" tag={Link} to="/">Cancel</Button>
                            </FormGroup>
                        </Form>
                    </Container>
            </div>
        )
    }
}

export default Project