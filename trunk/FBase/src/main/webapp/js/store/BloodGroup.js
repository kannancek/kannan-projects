
Ext.define('Ext.store.BloodGroup', {
    extend: 'Ext.data.ArrayStore',
    fields: [
             {name: 'bloodGroup'}
         ],
         data: [['A+ve'],['A-ve'],['B+ve'],['B-ve'],['AB+ve'],['AB-ve'],['O+ve'],['O-ve']],
    autoLoad: true
});

