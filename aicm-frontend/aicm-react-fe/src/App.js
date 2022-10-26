import {Provider} from "react-redux"
import {BrowserRouter as Router, Route, Switch} from "react-router-dom"
import Claims from "./Components/Claims"
import StoreService from "./Services/StoreService"
import RenderOnAnonymous from "./Components/RenderOnAnonymous"
import RenderOnAuthenticated from "./Components/RenderOnAuthenticated"
import Welcome from "./Components/Welcome"


const store = StoreService.setup();

const App = () => (
      <Provider store={store}>
        <Router>
          <div className="container">
            <RenderOnAnonymous>
              <Welcome/>
            </RenderOnAnonymous>
            <RenderOnAuthenticated>
              <Claims/>
            </RenderOnAuthenticated>
          </div>
        </Router>
      </Provider>
);

export default App;
