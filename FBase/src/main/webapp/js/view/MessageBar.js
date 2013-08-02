Ext.define('Ext.view.MessageBar', {

    extend:'Ext.Panel',
    id: 'messagebar',
    margins : '0 0 0 0',
    width : 'auto',
    border:false,
    hidden: true, 
    bodyPadding:5, 
    items:[{ id:'message', 
    		 border:false,	
    		  html: ''
           }],
    border:false,
    constructor: function(config) {
        this.callParent(arguments);
        this.initConfig(config);

    },
    showMessage: function(message, type) {
    	var msg = message;
    	if('OK' == type){    		
    		msg = '<div id= "success" style=""><img src="styles/icons/icon-ok.png" /">&nbsp;'+ message +'</div>';    		
    	}else if('ERROR' == type){    		
    		msg = '<div id= "error" style=""><img src="styles/icons/icon-error.png" /">&nbsp;'+ message +'</div>';    		
    	}else if('LOADER' == type){    		
    		msg = '<div id= "loader" style="">'+ message +'&nbsp;<img src="styles/icons/ajax-loader.gif" /"></div>';    		
    	}
    	this.update(msg);
    	this.show();
    }

});