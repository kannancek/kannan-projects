
Ext.define('Ext.view.ViewGrid', {
    
    extend:'Ext.grid.Panel',   
    frame:true,
    split: false,
    columnLines: true,
    viewConfig: {
        id: 'gv',
        trackOver: true,
        stripeRows: true
    },    
    bbar: Ext.create('Ext.PagingToolbar', {
        store: getStore(),
        displayInfo: true,
        displayMsg: 'Displaying records {0} - {1} of {2}',
        emptyMsg: "No records to display"

    }),
    tbar: [{
        text: 'Refesh',
        iconCls: 'icon-refresh',
        handler:function(){
       	 getStore().reload();
        }
    }],
    config: {
    },
    constructor: function(config) {
        this.callParent(arguments);
        this.initConfig(config);

    }

});


/* Implement below funtions
 * 
function getStore(){
    return store;
}
function onEditClick(record) {}
function onDeleteClick(record) {}
*/