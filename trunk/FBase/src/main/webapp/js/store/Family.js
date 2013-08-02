
Ext.define('Ext.store.Family', {
    extend: 'Ext.data.Store',
    requires: [
    ],
    model: 'Ext.model.Family',
    proxy: {
        type: 'ajax',
        url: './viewFamily.html?Action=getAllRecords',
        reader: {
            type: 'json',
            root: 'family',
            totalProperty: 'count'

        }
    },
    pageSize :10,
    autoLoad: true
}, function() {
});