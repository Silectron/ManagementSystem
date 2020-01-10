import React, {Component} from 'react';
import Navigation from './Navigation';
import logo from './logo.svg';

class Home extends Component {
    state = { }
    
    render() {
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