
Ext.define('Ext.store.ZipCodes', {
    extend: 'Ext.data.Store',
    requires: [
    ],
    model: 'Ext.model.ZipCodes',
    proxy: {
        type: 'ajax',
        url: './zipCodes.html?Action=getAllRecords',
        reader: {
            type: 'json',
            root: 'zipcodes',
            totalProperty: 'count'

        }
    },
    pageSize :10,
    autoLoad: true
}, function() {
});