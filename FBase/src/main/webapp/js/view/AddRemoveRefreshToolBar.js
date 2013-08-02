
var btnAdd=  Ext.create('Ext.Button',{
    text: 'Add',
    iconCls:'icon-add',
    handler:function() {
    	showAddForm();
    }
});
var btnRemove= Ext.create('Ext.Button',{
    text: 'Remove',
    iconCls:'icon-delete',
    handler:function() {
    	  removeSelected();
    }
});
var btnRefresh= Ext.create('Ext.Button',{
    text: 'Refresh',
    iconCls:'icon-refresh',
    handler:function() {
    	refresh();
    }
});

Ext.define('Ext.view.AddRemoveRefreshToolBar', {

    extend:'Ext.toolbar.Toolbar',
    items:[btnRefresh,btnRemove],
    border:false,
    constructor: function(config) {
        this.callParent(arguments);
        this.initConfig(config);

    }

});