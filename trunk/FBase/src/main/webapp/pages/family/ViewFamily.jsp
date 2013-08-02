<%@page import="com.ksoft.fbase.Constants"%>
<jsp:include page="../include/common_includes.jsp" flush="true" />
<script type="text/javascript">

var storeFamily = Ext.create('Ext.store.Family');

function getStore(){
    return storeFamily;
}
function removeSelected(){

	var record = Ext.getCmp('grid').getView().getSelectionModel().getSelection()[0];
	Ext.Msg.confirm('KSOFT', 'Do you want to remove selected records ?', function(btn){
    	
        if (btn == 'yes'){
    	   
            Ext.Ajax.request({
                url: './viewFamily.html',
                success: onAjaxSuccess,
                failure: onAjaxFailure,
                callback : function(){                    
                   getStore().load();  
                },
                params: {
                    Action :'delete',
                    familyId	:record.get('familyId')
                }
            });
          
        }
    });
	
}
var grid = Ext.create('Ext.view.ViewGrid', {
	id:'grid',
	width: '100%',
    height:'100%',
    minHeight:300,
    store: getStore(),    
    frame:false,
    columns: [{
	        
	        flex: 1,
	        sortable : true,
	        renderer  : function(arg1,arg2, record) {
	            
	            return '<a href="createFamily.html?Action=init&Mode=Update&familyId='+ record.get("familyId") +'"><img src="styles/icons/icon-edit.png"></a>';
	        }
	    },{
	        
	        flex: 1,
	        sortable : true,
	        renderer  : function(arg1,arg2, record) {
	            
	            return '<a href="#" onclick = removeSelected()><img src="styles/icons/icon-delete.png"></a>';
	        }
	    },{
            text: 'Family ID',            
            dataIndex: 'familyId',
            flex: 5,
            sortable : true
        },{
            text: 'Family Name',            
            dataIndex: 'familyName',
            flex: 5,
            sortable : true
        },{
        	text: 'Address Line 1',
        	dataIndex : 'addressLine1',
        	flex: 5,
            sortable : true
    	},{
    		text: 'Address Line 2',
    		dataIndex : 'addressLine2',
    		flex: 5,
            sortable : true
    	},{
    		text: 'Address line 3',
    		dataIndex : 'addressLine3',
    		flex: 5,
            sortable : true
    	},{
    		text: 'State',
    		dataIndex : 'state',
    		flex: 5,
            sortable : true
    	},{
    		text: 'District',
    		dataIndex : 'district',
    		flex: 5,
            sortable : true
    	},{
    		text: 'Country',
    		dataIndex : 'country',
    		flex: 5,
            sortable : true
    	},{
    		text: 'Zip Code',
    		dataIndex : 'zipCode',
    		flex: 5,
            sortable : true
    	}],
    	listeners:{
            itemdblclick: function(view, record) {
             	location.href='createFamily.html?Action=init&Mode=Update&familyId='+ record.get("familyId");
             }
         }
});
    
    Ext.onReady(function(){
       
        Ext.create('Ext.view.ContentPanel', {
        	title :'View Family',   
            bodyStyle:'padding:10px 10px 10px 10px;',  
            items: [messagebar,grid]
        });
        
        
    });

</script>

