
Ext.define('Ext.store.Members', {
    extend: 'Ext.data.Store',
    requires: [
    ],
    model: 'Ext.model.Member',
    proxy: {
        type: 'ajax',
        url: './createFamily.html?Action=getMembers',
        reader: {
            type: 'json',
            root: 'members'
            //totalProperty: 'count'

        }
    },
    //pageSize :10,
    autoLoad: true
}, function() {
});