import React, {Component} from 'react';
import Navigation from './Navigation';
import logo from './logo.svg';
import {Container, Button, Table, Row} from 'reactstrap';
import Axios from 'axios';

class Home extends Component {
    constructor(props){
        super(props)
        this.state = {
            isLoading : true,
            Projects : [],
            Employees : [],
            Leads : [],
            selectedProject : [],
            selectedEmployee : [],
            selectedLead : []
        }

        this.handleAssignSubmit = this.handleAssignSubmit.bind(this)
        this.handleLeadSubmit = this.handleLeadSubmit.bind(this)
    }

    async componentDidMount() {
        const projectsResponse = await fetch('/projects');
        const employeesResponse = await fetch('/employees');
        const leadsResponse = await fetch('/leads')
        const projectsBody = await projectsResponse.json();
        const employeesBody = await employeesResponse.json();
        const leadsBody = await leadsResponse.json();
        this.setState({
            Projects : projectsBody,
            Employees : employeesBody,
            Leads : leadsBody,
            isLoading : false
        })
    }

    handleAssignSubmit() {
        const params = new URLSearchParams();
        params.append("employee", this.state.selectedEmployee)
        params.append("project", this.state.selectedProject)
        Axios.post(`/assign/` +this.state.selectedEmployee, params)
            .then(() => {
                this.componentDidMount();
            }).catch(function (e) {
                console.log(e)
            })
    }

    handleLeadSubmit() {
        const params = new URLSearchParams();
        params.append("lead", this.state.selectedLead)
        params.append("project", this.state.selectedProject)
        Axios.post(`/leadproject/` +this.state.selectedLead, params)
            .then(() => {
                this.componentDidMount();
            }).catch(function (e) {
                console.log(e)
            })
    }

    render() {
        const{Projects, Employees, Leads, isLoading} = this.state;
        const title = <h3 style={{display:'flex', justifyContent:'center', alignItems:'center', height: '10vh'}}>Assign Roles</h3>
        if(isLoading) {
            return( <div>
                        <h2 style={{display:'flex', justifyContent:'center', alignItems:'center', height: '50vh'}}>
                            Retrieving data...
                        </h2>
                    </div>);
        }
        let projectRows = Projects.map( (project, index) => {
            if(project.status.localeCompare("Completed") !== 0) {
                return( <tr key={index}>
                            <td>{project.name}</td>
                            <td>{project.client}</td>
                            <td>{project.startDate}</td>
                            <td>{project.endDate}</td>
                            <td>{project.status}</td>
                        </tr>)
            }
            else{
                return null
            }
        })

        let employeeRows = Employees.map( (employee, index) => 
            <tr key={index}>
                <td>{employee.name}</td>
            </tr>
        )

        let leadRows = Leads.map( (lead, index) => 
            <tr key={index}>
                <td>{lead.name}</td>
            </tr>
        )

        return (
            <div className='Home'>
                <Navigation/>
                <img src={logo} className='Temp-logo' alt='logo'/>
                
                <Container>
                    <h2 style={{display:'flex', justifyContent:'center', alignItems:'center', height: '10vh'}}>Projects</h2> 
                    
                    <Table hover className="mt-4">
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
                            {projectRows}
                        </tbody>
                    </Table>
                </Container>

                <Container>
                    {title}
                    <div>
                        <div style={{float: 'left', width: '50%', height: '100%'}}>
                            <Table hover borderless>
                                    <thead align='center'>
                                    <tr>
                                        <th style={{fontSize: '20px', border: '1px ridge grey'}}>Employees</th>
                                    </tr>
                                    </thead>
                                    <tbody align='center'>
                                        {employeeRows}
                                    </tbody>
                            </Table>
                        </div>
                        <div style={{float: 'right', width: '50%', height: '100%'}}>
                            <Table hover borderless>
                                    <thead align='center'>
                                    <tr>
                                        <th style={{fontSize: '20px', border: '1px ridge grey'}}>Leads</th>
                                    </tr>
                                    </thead>
                                    <tbody align='center'>
                                        {leadRows}
                                    </tbody>
                        </Table>
                        </div>
                    </div>
                </Container>
                
                <Container style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
                <Row>
                    <div style={{width: '35%', height: '100%'}}>
                        <Button color="primary" onClick={this.handleAssignSubmit}>Assign to Project</Button>
                    </div> 

                    <div style={{height: '100%', width: '30%'}}/>

                    <div style={{width: '35%', height: '100%'}}>
                        <Button color="primary" onClick={this.handleLeadSubmit}>Assign as Lead</Button>      
                    </div>  
                </Row>
                </Container>
                 
                <h4 style={{display:'flex', justifyContent:'center', alignItems:'center', height: '20vh'}}>
                    Simple Project Management
                </h4>
            </div>
        )
    }
}

export default Home;