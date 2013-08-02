
Ext.define('Ext.store.Country', {
    extend: 'Ext.data.ArrayStore',
    fields: [
             {name: 'country'}
         ],
         data: [['India']],
    autoLoad: true
});
