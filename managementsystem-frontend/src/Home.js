import React, {Component} from 'react';
import Navigation from './Navigation';
import logo from './logo.svg';

class Home extends Component {
    state = { 
        isLoading: true,
        Projects: [],
        Employees: [],
    }

    async componentDidMount() {
        const projectsResponse = await fetch('/projects');
        const employeesResponse = await fetch('/employees');
        const projectsBody = await projectsResponse.json();
        const employeesBody = await employeesResponse.json();
        this.setState({
            Projects : projectsBody,
            Employees : employeesBody,
            isLoading : false
        })
    }
    
    render() {
        const{Projects, Employees, isLoading} = this.state;
        const title = <h4>Assign Roles</h4>
        return (
            <div className='Home'>
                <Navigation/>
                <img src={logo} className='Temp-logo' alt='logo'/>
                <h4 style={{display:'flex', justifyContent:'center', alignItems:'center', height: '20vh'}}>
                    Simple Project Management
                </h4>
            </div>
        )
    }
}

export default Home;