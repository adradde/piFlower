const redux = require('redux');

// Initial state
const initialState = {
    websocket: {
        flower: {
            connected: false
        }
    }

}

// Reducer
const rootReducer = (state = initialState, action) => {

    if (action.type === 'CONN_FLOW_SOCCET')
        return {
            ...state,
            websocket: {
                flower: { connected: true }
            }
        }
    return state;
}

// Store
const store = redux.createStore(rootReducer);

// Dispatchig Actions
store.dispatch({
    type: 'CONN_FLOW_SOCCET'
})

// Subscription
store.subscribe(() => {
    
});