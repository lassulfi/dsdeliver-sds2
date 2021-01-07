import { BrowserRouter, Switch, Route } from 'react-router-dom';
import Home from './Home';
import Nabvar from './Navbar';
import Orders from './Orders';

function Routes() {
    return (
        <BrowserRouter>
            <Nabvar />
            <Switch>
                <Route path="/orders">
                    <Orders />
                </Route>
                <Route path="/">
                    <Home />
                </Route>
            </Switch>
        </BrowserRouter>
    )
}

export default Routes;