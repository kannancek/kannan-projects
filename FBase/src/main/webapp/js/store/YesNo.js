
Ext.define('Ext.store.YesNo', {
    extend: 'Ext.data.ArrayStore',
    fields: [
             {name: 'name'},{name:'value'}
         ],
         data: [['Yes','Y'],['No','N']],
    autoLoad: true
});

