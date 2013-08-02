Ext.require([
    'Ext.panel.*'
    ]);
 
Ext.define('Ext.view.ContentPanel', {

    extend:'Ext.panel.Panel',
    frame:false,
    border:false,
    width:'100%',
    height:'100%',
    renderTo: Ext.getBody(),
    config: {
    },
    constructor: function(config) {
        this.callParent(arguments);
        this.initConfig(config);

    }

});