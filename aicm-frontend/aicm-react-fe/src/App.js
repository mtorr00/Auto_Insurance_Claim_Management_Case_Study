import {Provider} from "react-redux"
import {BrowserRouter as Router, Route, Switch} from "react-router-dom"
import Claims from "../Components/Claims"
import ClaimDetails from "../Components/ClaimDetails"

const store = StoreService.setup();

const App = () => (
      <Provider store={store}>
        <Router history={history}>
          <div className="container">
            <Switch>
              <Route exact path="/" component={Claims}/>
              <Route path="claims/:claimId" component={ClaimDetails}/>
            </Switch>
          </div>
        </Router>
      </Provider>
);

export default App;
