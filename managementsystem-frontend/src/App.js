import React, {Component} from 'react';
import Home from './Home';
import Project from './Project';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

class App extends Component {
    state = { };

    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/projects' exact={true} component={Project}/>
                </Switch>
            </Router>
        );
    }
}

export default App;