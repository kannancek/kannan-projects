Ext.define('Ext.view.Header', {
    extend: 'Ext.Container',
    xtype: 'appHeader',
    id: 'app-header',
    height: 80,
    layout: {
        type: 'hbox',
        align: 'middle'
    },
    initComponent: function() {
        this.items = [{
            xtype: 'component',
            id: 'app-header-title',
            contentEl: 'appHeader',
            flex: 1
        },{
            xtype: 'component',
            id: 'app-header-fbase'           
        }];
        this.callParent();
    }
});
