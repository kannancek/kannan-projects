
<jsp:include page="../include/common_includes.jsp" flush="true" />
<script type="text/javascript">

	var mode = '<%=request.getSession().getAttribute("Mode")%>';
	var storeMembers = Ext.create('Ext.store.Members');
	var storeState = Ext.create('Ext.store.States');
	var storeDistrict = Ext.create('Ext.store.District');
	var storeCountry = Ext.create('Ext.store.Country');
	var storeBloodGroup = Ext.create('Ext.store.BloodGroup');
	var storeYN = Ext.create('Ext.store.YesNo');
	
	function getStore(){
	    return storeMembers;
	}

	function removeSelected(){

		var record = Ext.getCmp('members').getView().getSelectionModel().getSelection()[0];
		Ext.Msg.confirm('KSOFT', 'Do you want to remove selected records ?', function(btn){
	    	
	        if (btn == 'yes'){
	    	   
	            Ext.Ajax.request({
	                url: './createFamily.html',
	                success: onAjaxSuccess,
	                failure: onAjaxFailure,
	                callback : function(){                    
	                   getStore().load();  
	                },
	                params: {
	                    Action :'deleteMember',
	                    RowIndex	:  getStore().indexOf(record)
	                }
	            });
	          
	        }
	    });
		
	}
    var form = Ext.create('Ext.form.Panel', {		
        id : 'family',		
        frame:false,
        border:false,
        title:'Family Address',
        height:'100%',        
        bodyStyle:'padding:10px 2px 10px 2	px;',                		    
        url : './createFamily.html?Action=save&Mode='+mode,
        items : [{
                xtype : 'fieldset',	
                collapsible : false,
                layout: {
                    type: 'table',
                    width:'100%',            
                    columns: 3,
                    tdAttrs:
                    {
                        style:
                        {
                            margin: '0 5 0 5',
                            padding: '4 5 0 5'
                        }
                    }
                },
                items : [{
                    xtype : 'hidden',
                    name : 'familyId'
                },{
                    xtype : 'textfield',
                    fieldLabel : 'Family Name',
                    name : 'familyName',
                    allowBlank : false,                  
                },{
                    xtype : 'textfield',
                    fieldLabel : 'Address Line 1',
                    name : 'addressLine1',
                    allowBlank:false,
                }, {
                    xtype : 'textfield',
                    fieldLabel : 'Address Line 2',
                    name : 'addressLine2',
                },{
                    xtype : 'textfield',
                    fieldLabel : 'Address Line 3',
                    name : 'addressLine3'
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
                    valueNotFoundText:	'sd',
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
                    xtype : 'numberfield',
                    fieldLabel : 'Zip Code',
                    name : 'zipCode',
                    allowBlank:false,
                    hideTrigger: true
                }],
                plugins : [Ext.create('Ext.ux.plugin.form.LabelRequired')]
            }]

    });

   
     var grid = Ext.create('Ext.grid.Panel', {
    	 id : 'members',	
         width: '100%',
         height:300,
         store: getStore(),    
         frame:false,
         columnLines: true,
         title:'Member Details',
         viewConfig: {
             id: 'gv',
             trackOver: false,
             stripeRows: false
         },     
         columns: [{
 	        
	 	        flex: 1,
	 	        sortable : true,
	 	        renderer  : function(arg1,arg2, record) {
		 	        
	 	            return '<a href="#" onclick = onEdit()><img src="styles/icons/icon-edit.png"></a>';
	 	        }
	 	    },{
	 	        
	 	        flex: 1,
	 	        sortable : true,
	 	        renderer  : function(arg1,arg2, record) {
	 	            
	 	            return '<a href="#" onclick = removeSelected()><img src="styles/icons/icon-delete.png"></a>';
	 	        }
 	    	},{
	        	 text: 'ID',
	             flex: 5,
	             dataIndex : 'memberId',
	             sortable : true
         	 },{
            	 text: 'Family ID',
                 flex: 5,
                 dataIndex : 'familyId',
                 sortable : true
             },{
	        	 text: 'Family Head Y/N',
	             flex: 5,
	             dataIndex : 'familyHeadYn',
	             sortable : true
         	 },{
                 text: 'Name',
                 flex: 5,
                 dataIndex: 'name',
                 sortable : true
             },{
                 text: 'Sex',
                 flex: 5,
                 dataIndex: 'sex',
                 sortable : true,
                 renderer : function(val) {
                     if (val == 'M') {
                         return 'Male';
                     } else if (val == 'F') {
                         return 'Female';
                     }
                     return val;
                 }
             },{
                 text: 'Date of Birth',
                 flex: 5,
                 dataIndex: 'dob',
                 sortable : true,
                 
             },{
                 text: 'Blood Group',
                 flex: 5,
                 dataIndex: 'bloodGroup',
                 sortable : true
             },{
                 text: 'Occupation',
                 flex: 5,
                 dataIndex: 'occupation',
                 sortable : true
             },{
                 text: 'Qualification',
                 flex: 5,
                 dataIndex: 'qualification',
                 sortable : true
             },{
                 text: 'Marital Status',
                 flex: 5,
                 dataIndex: 'maritalStatus',
                 sortable : true, 
                 renderer : function(val) {
                     if (val == 'S') {
                         return 'Single';
                     } else if (val == 'M') {
                         return 'Married';
                     }
                     return val;
                 }
             },{	sortable : true,
         		dataIndex : 'contactNo',
        		text : 'Contact No'
        	},{
        		sortable : true,
        		dataIndex : 'email',
        		text : 'Email'
        	},{
        		sortable : true,
        		dataIndex : 'annualIncome',
        		text : 'Annual Income'
        	},{
        		sortable : true,
        		dataIndex : 'fatherName',
        		text : 'Father\'s Name'
        	},{
        		sortable : true,
        		dataIndex : 'motherName',
        		text : 'Mother\'s Name'
        	},{
        		sortable : true,
        		dataIndex : 'spouseName',
        		text : 'Spouse\'s Name'
        	},{
        		sortable : true,
        		dataIndex : 'relation',
        		text : 'Relation with Family Head'
        	},{
        		sortable : true,
        		dataIndex : 'voterId',
        		text : 'Voter ID'
        	},{
        		sortable : true,
        		dataIndex : 'aadharId',
        		text : 'Aadhar ID'
        	}],tbar: [{
                 text: 'Refesh',
                 iconCls: 'icon-refresh',
                 handler:function(){
                	 getStore().reload();
                 }
             },{
                 text: 'Add',
                 iconCls: 'icon-add-member',
                 handler:function(){
                	 showAddForm();
                 }
             },{
                 text: 'Remove',
                 iconCls: 'icon-delete-member',
                 handler:function(){
                	
                	 	var record = Ext.getCmp('members').getView().getSelectionModel().getSelection()[0];
                	 	var rowIndex = getStore().indexOf(record);
                	 	
                		if(null != record || undefined != record ){
                    		
                			Ext.Msg.confirm('KSOFT', 'Do you want to remove selected records ?', function(btn){
                		    	
                		        if (btn == 'yes'){
                		    	   
                		            Ext.Ajax.request({
                		                url: './createFamily.html',
                		                success: onAjaxSuccess,
                		                failure: onAjaxFailure,
                		                callback : function(){                    
                		                   getStore().load();  
                		                },
                		                params: {
                		                    Action :'deleteMember',
                		                    RowIndex	: rowIndex
                		                }
                		            });
                		          
                		        }
                		    });  
                		}
                 }
             }],
             listeners:{
                 itemdblclick: function(view, record) {
                 	onEdit();
                 }
             }
     });
     
    function showAddForm(record){

    	var btnAddUpdate = null;
    	
    	var  onSuccess=  function (form, action){
            response = Ext.JSON.decode(action.response.responseText);
            getStore().reload();  
            if(undefined != record){
            	editWindow.close();
            }
        };
        	   
 	   var btnClose= Ext.create('Ext.Button',{
            text: 'Close',
            width: 75,
            handler:function() {
            	editWindow.close();
            }
        });

       if(undefined != record){

    	   var rowIndex = getStore().indexOf(record);

    	   btnAddUpdate =  Ext.create('Ext.Button',{
               text: 'Update',
               width: 75,
               handler:function() {
                   var form = Ext.getCmp('membersdetails').getForm();   
                   if (form.isValid()) {
                       form.url = './createFamily.html?Action=updateMember&Mode='+mode+'&RowIndex='+rowIndex;
                       form.submit({
                           waitTitle:'Connecting',
                           waitMsg:'Please wait...',
                           success: onSuccess,
                           failure: onFailure
                       });
                      
                   }
                            
               }
           });

       }else{
    	   btnAddUpdate =  Ext.create('Ext.Button',{
               text: 'Add',
               width: 75,
               handler:function() {
                   var form = Ext.getCmp('membersdetails').getForm();   
                   if (form.isValid()) {
                	   form.url = './createFamily.html?Action=addMember&Mode='+mode;
                       form.submit({
                           waitTitle:'Connecting',
                           waitMsg:'Please wait...',
                           success: onSuccess,
                           failure: onFailure
                       });
                      
                   }
                            
               }
           });
			
       }
        

        
        var editForm = Ext.create('Ext.form.Panel', {		
            id : 'membersdetails',		
            frame: true,
            border: false,
            height: 439,
            layout: {
                type: 'table',
                columns:2,
                tdAttrs:
                {
                    style:
                    {
                        margin: '0 5 0 5',
                        padding: '4 5 0 5'
                    }
                }
            },
           bodyStyle:'padding:20px 20px 20px 20px;',  
            items : [{
                xtype : 'hidden',
                name : 'memberId'
           },{
                xtype : 'hidden',
                name : 'familyId'
           },{
               xtype : 'textfield',
               fieldLabel : 'Name',
               name : 'name',
               allowBlank : false,                  
           },{
               xtype: 'combo',
               name: 'familyHeadYn',
               fieldLabel: 'Family Head Y/N',
               store: storeYN,
               queryMode: 'local',
               displayField: 'name',
               valueField: 'value',
               editable:true
           },{
               xtype: 'radiogroup',allowBlank:false,
               defaults: {
                   name : 'sex'
               },
               fieldLabel: 'Sex',
               columns: 2,
               width:250,
               items: [{
                       inputValue: 'M',
                       boxLabel: 'Male'
                   }, {
                       inputValue: 'F',
                       boxLabel: 'Female'
                   }]
           }, {
               xtype : 'datefield',
               fieldLabel : 'Date of Birth',
               name : 'dob',
               format: 'd/m/Y'
           },{
               xtype: 'combo',
               name: 'bloodGroup',
               fieldLabel: 'Blood Group',
               store: storeBloodGroup,
               queryMode: 'local',
               displayField: 'bloodGroup',
               valueField: 'bloodGroup',
               editable:true
           },{
               xtype: 'radiogroup',
               defaults: {
                   name : 'maritalStatus'
               },
               fieldLabel: 'Marital Status',
               columns: 2,
               width:250,
               items: [{
                       inputValue: 'S',
                       boxLabel: 'Single'
                   }, {
                       inputValue: 'M',
                       boxLabel: 'Married'
                   }]
       		},{
               xtype : 'textfield',
               fieldLabel : 'Occupation',
               name : 'occupation'
           },  {
               xtype : 'textfield',
               fieldLabel : 'Qualification',
               name : 'qualification'
           },{	xtype : 'textfield',
       			name : 'contactNo',
       			fieldLabel : 'Contact No'
       		},{
       			xtype : 'textfield',
       			name : 'email',
       			fieldLabel : 'Email'
       		},{
       			xtype : 'textfield',
       			name : 'annualIncome',
       			fieldLabel : 'Annual Income'
       		},{
       			xtype : 'textfield',
       			name : 'fatherName',
       			fieldLabel : 'Father\'s Name'
       		},{
       			xtype : 'textfield',
       			name : 'motherName',
       			fieldLabel : 'Mother\'s Name'
       		},{
       			xtype : 'textfield',
       			name : 'spouseName',
       			fieldLabel : 'Spouse\'s Name'
       		},{
       			xtype : 'textfield',
       			name : 'relation',
       			fieldLabel : 'Relation with Family Head'
       		},{
       			xtype : 'textfield',
       			name : 'voterId',
       			fieldLabel : 'Voter ID'
       		},{
       			xtype : 'textfield',
       			name : 'aadharId',
       			fieldLabel : 'Aadhar ID'
       		}],plugins : [Ext.create('Ext.ux.plugin.form.LabelRequired')]
        });
        
        if(undefined != record){
        	editForm.getForm().loadRecord(record);
        }
         
 	   
  	 
  	  editWindow = Ext.create('widget.window', {
           title: 'Member Details',
           closable: true,
           modal:true,
           autoScroll : true,
           animateTarget: grid,
           width: 800,
           height: 500, 
           //bodyStyle:'padding:10px 10px 10px 10px',
           items: [editForm],
           buttons : [btnAddUpdate, btnClose],
           buttonAlign:'center'
       }).show();  
 	   
    }
     
     
    
     function onEdit(record) {
         
    	 var record = Ext.getCmp('members').getView().getSelectionModel().getSelection()[0];
    	 showAddForm(record);
     	      
     }
         
     function onDelete(record) {
 	   
         Ext.Msg.confirm('KSOFT', 'Do you want to remove selected records ?', function(btn){
         	
             if (btn == 'yes'){
         	   
                 Ext.Ajax.request({
                     url: './organization.do',
                     success: function (response){
                         response = Ext.JSON.decode(response.responseText);
                         Ext.MessageBox.show({
                             title: 'KSOFT',
                             msg: response.message,
                             buttons: Ext.MessageBox.OK,
                             icon: Ext.MessageBox.INFO
                         });
                         getStore().load();
                     },
                     failure: function (response){
                         response = Ext.JSON.decode(response.responseText);
                         Ext.MessageBox.show({
                             title: 'KSOFT',
                             msg: response.message,
                             buttons: Ext.MessageBox.OK,
                             icon: Ext.MessageBox.ERROR
                         });
                     },
                     params: {
                         Action :'delete',
                         Id	:record.get('id')
                     }
                 });
               
             }
         });
 	   
 	    
     }
   
     var btnSave=  Ext.create('Ext.Button',{
         text: 'Save',
         width: 75,               
         handler:function() {
        	 
           var form = Ext.getCmp('family').getForm();
             if (form.isValid()) {
                
                 form.submit({
                     waitTitle:'Connecting',
                     waitMsg:'Please wait...',
                     success: onSuccess,
                     failure: onFailure
                 });
             }
         }
     });

     var btnClear= Ext.create('Ext.Button',{
         text: 'Clear',
         width: 75,
         handler:function() {
             var form = Ext.getCmp('family').getForm();
             form.reset();
         }
     });
     
    Ext.onReady(function() {

        Ext.create('Ext.view.ContentPanel', {  
            title :'Family Details',   
            bodyStyle:'padding:10px 10px 10px 10px;',  
            html:'<div id="msg"></div><div id="form_div" style="height:150px;width:100%" ></div><div id="grid_div"></div> <br/><div id="button_div"></div> '     
            //items : [ form,grid ]
        });
        messagebar.render('msg');
        form.render('form_div');
        grid.render('grid_div');
        btnSave.render('button_div');
        
        
    });

    if("Update" == mode){

    	var record =  Ext.create('Ext.model.Family',
    	    	 
    	    	<%=request.getAttribute("familyData")%>
    	          
    		);	
    	Ext.getCmp('family').getForm().loadRecord(record);
    	storeMembers.proxy.url = storeMembers.proxy.url + "&familyId=" + record.get('familyId');
    	//storeMembers.reload();
    }
    
</script>

