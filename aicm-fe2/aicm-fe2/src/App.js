import './App.css';
import Keycloak from "keycloak-js"
import {Dimmer,Icon,Header} from "semantic-ui-react"
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'

function App() {
  const keycloak = new Keycloak({
    url: `${config.url.KEYCLOAK_BASE_URL}`,
    realm: "company-services",
    clientId: "movies-app"
  })
  const initOptions = { pkceMethod: 'S256' }
  
  const handleOnEvent = async (event, error) => {
    if (event === 'onAuthSuccess') {
      if (keycloak.authenticated) {
        let response = await moviesApi.getUserExtrasMe(keycloak.token)
        if (response.status === 404) {
          const userExtra = { avatar: keycloak.tokenParsed.preferred_username }
          response = await moviesApi.saveUserExtrasMe(keycloak.token, userExtra)
          console.log('UserExtra created for ' + keycloak.tokenParsed.preferred_username)
        }
        keycloak['avatar'] = response.data.avatar
      }
    }
  }

  const loadingComponent = (
    <Dimmer inverted active={true} page>
      <Header style={{ color: '#4d4d4d' }} as='h2' icon inverted>
        <Icon loading name='cog' />
        <Header.Content>Keycloak is loading
          <Header.Subheader style={{ color: '#4d4d4d' }}>or running authorization code flow with PKCE</Header.Subheader>
        </Header.Content>
      </Header>
    </Dimmer>
  )

  return (
    <ReactKeycloakProvider
      authClient={keycloak}
      initOptions={initOptions}
      LoadingComponent={loadingComponent}
      onEvent={(event, error) => handleOnEvent(event, error)}
    >
      <Router>
        <Navbar />
        <Switch>
          <Route path='/' exact component={Home} />
          <Route path='/home' component={Home} />
          <Route path='/movies/:id' component={MovieDetail} /> 
          <PrivateRoute path='/movies' component={MoviesPage} />
          <PrivateRoute path='/wizard' component={MovieWizard} />
          <PrivateRoute path='/settings' component={UserSettings} />
          <Route component={Home} />
        </Switch>
      </Router>
    </ReactKeycloakProvider>
  )
}

export default App;
