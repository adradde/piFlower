import React from "react";
import ReactDOM from "react-dom";
import { createBrowserHistory } from "history";
import { Router, Route, Switch } from "react-router-dom";
import { createStore } from 'redux';
import { Provider } from 'react-redux';

import "assets/css/material-dashboard-react.css?v=1.4.0";

import indexRoutes from "routes/index.jsx";
import reducer from './store/reducer';

const store = createStore(reducer);

const hist = createBrowserHistory();

ReactDOM.render(
  <Provider store={store}>
    <Router history={hist}>
      <Switch>
        {indexRoutes.map((prop, key) => {
          return <Route path={prop.path} component={prop.component} key={key} />;
        })}
      </Switch>
    </Router>
  </Provider>
  ,
  document.getElementById("root")
);
