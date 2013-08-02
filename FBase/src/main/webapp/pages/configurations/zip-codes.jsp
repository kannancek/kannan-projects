<html>
	<head>
		<jsp:include page="../include/common_includes.jsp" flush="true" />
	</head>
<body>

<script type="text/javascript">

var mode ='create';
var toolbar = Ext.create('Ext.view.AddRemoveRefreshToolBar');
var store = Ext.create('Ext.store.ZipCodes');
var storeState = Ext.create('Ext.store.States');
var storeDistrict = Ext.create('Ext.store.District');
var storeCountry = Ext.create('Ext.store.Country');

function getStore(){
    return store;
}

var btnSave=  Ext.create('Ext.Button',{
    text: 'Save',
    iconCls:'icon-save',
    margin: '10 0 0 0',
    handler:function() {
    	form = formGrid.getForm();
    	form.url = './zipCodes.html?Action=save&Mode='+mode;
    	if (form.isValid()) {
    		messagebar.showMessage(Ext.conf.Constants.PAGE_SIZE,'LOADER'); 
            form.submit({
                success: onSuccess,
                failure: onFailure
            });
            
        }
    }
});
var btnNew =  Ext.create('Ext.Button',{
    text: 'New',
    iconCls:'icon-add',
    margin: '10 0 0 10',
    handler:function() {
    	form = formGrid.getForm();
    	form.reset();
    	Ext.getCmp('zipCode').setReadOnly(false);
    	Ext.getCmp('zipCode').focus();
    	mode ='create';
    	messagebar.hide();
    }
});
var buttonPanel =  Ext.create('Ext.Panel',{
	frame: false,
	border:false,
	layout: 'hbox',
	items:[btnSave,btnNew]
	
});

function removeSelected(){
	var record = Ext.getCmp('grid').getView().getSelectionModel().getSelection()[0];

	if(null == record || undefined == record ){
		 messagebar.showMessage('Select record to be removed','ERROR');   
	}else{

		Ext.Msg.confirm('KSOFT', 'Do you want to remove selected records ?', function(btn){
	    	
	        if (btn == 'yes'){
	    	   
	            Ext.Ajax.request({
	                url: './zipCodes.html',
	                success: onAjaxSuccess,
	                failure: onAjaxFailure,
	                callback : function(){                    
	                   getStore().load();  
	                   formGrid.getForm().reset();               
	                },
	                params: {
	                    Action :'delete',
	                    zipCodes	:record.get('zipCode')
	                }
	            });
	          
	        }
	    });

	}
	
	
}
function refresh(){
	getStore().reload();
}


var formGrid = Ext.create('Ext.form.Panel', {
	frame: false,
	border:false,
    bodyPadding: 5,
    layout: 'column',
    width: 'auto',
    fieldDefaults: {
        labelAlign: 'left',
        labelWidth: 90,
        anchor: '100%',
        msgTarget: 'side'
    },
    items: [{
        columnWidth: 0.50,
        id:'grid',
        xtype: 'gridpanel',
        store: getStore(),
        height: 400,
        columnLines:true,
        columns: [{
            text: 'Zip Code',
            sortable: true,
            dataIndex: 'zipCode'
        }, {
            text: 'State',
            sortable: true,
            dataIndex: 'state'
        },{
            text: 'District',
            sortable: true,
            dataIndex: 'district'
        }, {
            text: 'Country',
            sortable: true,
            dataIndex: 'country'
        },{
            text: 'Post',
            sortable: true,
            dataIndex: 'post'
        }],
        listeners: {
            scope: this,
            selectionchange: function(model, records) {
                var rec = records[0];
                if (rec) {
                	formGrid.getForm().loadRecord(rec);
                	Ext.getCmp('zipCode').setReadOnly(true);
                	mode ='update';
                }
            }
        },bbar: Ext.create('Ext.PagingToolbar', {
            store: getStore(),
            displayInfo: true,
            displayMsg: 'Displaying records {0} - {1} of {2}',
            emptyMsg: "No records to display"

        })
    },{
        columnWidth: 0.50,
        margin: '0 0 0 10',
        frame: false,
        border:false,
        defaultType: 'textfield',
        items: [{
            fieldLabel: 'Zip Code',
            name: 'zipCode',
            id:'zipCode',
            allowBlank : false
        },{
            xtype: 'combo',
            name: 'state',
            fieldLabel: 'State',
            store: storeState,
            queryMode: 'local',
            displayField: 'state',
            valueField: 'state',
            value:'Kerala',
            editable:true
        },{
            xtype: 'combo',
            name: 'district',
            fieldLabel: 'District',
            store: storeDistrict,
            queryMode: 'local',
            displayField: 'district',
            valueField: 'district',
            value:'Thiruvananthapuram',
            editable:true
        },{
            xtype: 'combo',
            name: 'country',
            fieldLabel: 'Country',
            store: storeCountry,
            queryMode: 'local',
            displayField: 'country',
            valueField: 'country',
            value:'India',
            editable:true
        },{
            fieldLabel: 'Post',
            name: 'post'
        },buttonPanel]
    }]
});



Ext.onReady(function() {

    Ext.create('Ext.view.ContentPanel', { 
        id:'contentpanel', 
        title :'Pin Codes',           
        contentEl:'zipcodes',
        items:[toolbar,messagebar, formGrid]  
    });
 
});
</script>
<div id ="zipcodes">

</div>
</body>
</html>	
