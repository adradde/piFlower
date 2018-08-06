import * as actions from './actions'

const initialState = {
    websocket: {
        flowers: {
            connected: false,
            socket: null
        }
    }

}

const rootReducer = (state = initialState, action) => {

    switch (action.type) {
        case actions.CONNECT_FLOWER_SOCKET:
            return {
                ...state,
                websocket: {
                    flowers: {
                        connected: true,
                        socket: action.value
                    }
                }
            }
        default:
            return state;
    }
}

export default rootReducer;