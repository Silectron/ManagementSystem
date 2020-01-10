import React, {Component} from 'react';
import {Nav, Navbar, NavItem, NavLink, NavbarBrand} from 'reactstrap';

class Navigation extends Component {
    state = { };
    
    render() {
        return (
            <div>
              <Navbar color="primary" dark expand="md">
                <NavbarBrand href="/">Project Management Application</NavbarBrand>
                  <Nav className="ml-auto" navbar>
                    <NavItem>
                      <NavLink href="/">Home</NavLink>
                    </NavItem>
                    <NavItem>
                      <NavLink href="/projects">Projects</NavLink>
                    </NavItem>
                    <NavItem>
                      <NavLink href="/employees">Employees</NavLink>
                    </NavItem>
                  </Nav>
              </Navbar>
            </div>
          );
    }
}

export default Navigation;