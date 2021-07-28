import Vue from 'vue'
import App from './App.vue'
import router from './router'
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import ColumnGroup from 'primevue/columngroup'; 

import 'primevue/resources/themes/saga-blue/theme.css';
import 'primevue/resources/primevue.min.css';
import 'primeicons/primeicons.css';
import Toolbar from 'primevue/toolbar';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import Dropdown from 'primevue/dropdown';
import Dialog from 'primevue/dialog';
import RadioButton from 'primevue/radiobutton';
import InputNumber from 'primevue/inputnumber';
import Textarea from 'primevue/textarea';


Vue.config.productionTip = false
Vue.component('DataTable', DataTable);
Vue.component('Column', Column);
Vue.component('ColumnGroup', ColumnGroup);
Vue.component('Toolbar', Toolbar);
Vue.component('Button', Button);
Vue.component('InputText', InputText);
Vue.component('InputNumber', InputNumber);
Vue.component('Dialog', Dialog);
Vue.component('RadioButton', RadioButton);
Vue.component('Dropdown', Dropdown);
Vue.component('Textarea', Textarea);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
