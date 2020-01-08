import React, { Component } from "react";

class Project extends Component {
    state = {
        isLoading: true,
        Projects : []
    }

    async componentDidMount() {
        const response = await fetch('/projects');
        const body = await response.json();
        this.setState({Projects : body, isLoading : false});
    }

    render() {
        const{Projects, isLoading} = this.state;
        if(isLoading) 
            return(<div>Retrieving data...</div>);
        
            return(
                <div>
                    <h1>Projects</h1>
                    {   
                        Projects.map( project =>
                            <div id={project.name}>
                                {project.name}
                            </div>
                        )
                    }
                </div>
            );
        
    }
}

export default Project;