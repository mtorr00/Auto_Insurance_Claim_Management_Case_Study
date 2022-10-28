import React, { Component } from 'react'
import { withKeycloak } from '@react-keycloak/web'
import { moviesApi } from '../misc/MoviesApi'
import { handleLogError } from '../misc/Helpers'
import { Container, Form, Segment, Button, Divider, Grid } from 'semantic-ui-react'
import { withRouter } from 'react-router-dom'

class UserSettings extends Component {
  state = {
    username: '',

  }

  async componentDidMount() {
    const { keycloak } = this.props

    try {
      const response = await moviesApi.getUserExtrasMe(keycloak.token)
      const { username } = response.data
      this.setState({ username })
    } catch (error) {
      handleLogError(error)
    }
  }

  render() {
    
    return (
      <Container>
        <Grid centered>
          <Grid.Row>
            <Segment style={{ width: '330px' }}>
              <Form>
                <Divider />
                <Button fluid onClick={x} color='blue' disabled={x}>Shuffle</Button>
                <Divider />
                <Button.Group fluid>
                  <Button onClick={x}>Cancel</Button>
                  <Button.Or />
                  <Button disabled={x} onClick={x} positive>Save</Button>
                </Button.Group>
              </Form>
            </Segment>
          </Grid.Row>
        </Grid>
      </Container>
    )
  }
}

export default withRouter(withKeycloak(UserSettings))