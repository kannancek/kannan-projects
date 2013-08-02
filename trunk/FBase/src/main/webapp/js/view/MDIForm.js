  Ext.Loader.setConfig({
        enabled:true
    });
    Ext.Loader.setPath('Ext.ux', 'extjs/ux');
    Ext.Loader.setPath('Ext.store', 'js/store');
    Ext.Loader.setPath('Ext.model', 'js/model');
    Ext.Loader.setPath('Ext.view', 'js/view');

var mainPanel = getMainPanel();
var topPanel = getTopPanel();

Ext.onReady(function() {
    
   renderViewPort(topPanel,mainPanel);
	 

});


function renderViewPort(topPanel,mainPanel){

	Ext.create('Ext.container.Viewport', {
        layout : 'border',
        items : [ topPanel,  mainPanel ]
    });
	
		
}

function getTopPanel(){
	
    var topPanel = Ext.create('Ext.view.Header', {
        region : 'north',
        margins : '0 0 0 0',
        split : false
    });
    
    return topPanel;
}

function getMainPanel(){

	var mainPanel =Ext.create('Ext.view.SimpleIFrame', {
    	id:'main',    	
    	border: false,            
    	id : 'doc-body',
		region : 'center',
		margins : '0 0 0 0',  
	    src: './home.html',	    
	    lbar: [{
	        xtype:'label',
	        text: 'Menu',
	        width:150,
	        style:'padding:5px 5px 5px 50px;font-weight:bold',
	        defaultType:'splitbutton'
	    },{
	        text: 'Family',
	        textAlign:'left',
	        iconCls: 'icon-family',
	        menu: [{
	            text: 'Create Family',
	            iconCls: 'icon-add-member',
	            handler:function(){
	            	setSrc('Create Family','./createFamily.html?Action=init&Mode=Create');
	            }
	        },{
	            text: 'View Family',
	            iconCls: 'icon-family',
	            handler:function(){
	            	setSrc('View Family','./viewFamily.html?Action=init');
	            }
	        }]
	    },{
	        text: 'Configurations',
	        textAlign:'left',
	        iconCls: 'icon-settings',
	        menu: [{
	            text: 'ZipCode',
	            iconCls: 'icon-notes',
	            handler:function(){
	            	setSrc('Create Family','./zipCodes.html');
	            }
	        }]
	    }]
	});   
    return mainPanel;
    
}

function setSrc(title,url) {
    mainPanel.setSrc(url);
    mainPanel.reload();
}
   

