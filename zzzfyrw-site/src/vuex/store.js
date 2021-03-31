import { createStore } from 'vuex'

import state from './state/init';
import actions from './actions/init';
import mutations from "./mutations/init";
import getters from "./getters/init";


const store = createStore({
    ...state,
    ...actions,
    ...mutations,
    ...getters
})

export default store;

