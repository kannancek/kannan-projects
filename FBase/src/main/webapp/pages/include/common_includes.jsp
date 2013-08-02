<!-- Cascade Style Sheets -->

<!-- ExtJS ----------------------------------------------------------------------------------------------------------->

 <script type="text/javascript" src="extjs/include-ext.js?theme=<%=request.getServletContext().getAttribute("css")%>"></script>
<!-- <script type="text/javascript" src="extjs/options-toolbar.js"></script> -->

<!-------------------------------------------------------------------------------------------------------------------->

<link href="styles/basic.css" rel="stylesheet" type="text/css" />
<link href="styles/icon.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

    Ext.Loader.setConfig({
        enabled:true
    });
    Ext.Loader.setPath('Ext.ux', 'extjs/ux');
    Ext.Loader.setPath('Ext.store', 'js/store');
    Ext.Loader.setPath('Ext.model', 'js/model');
    Ext.Loader.setPath('Ext.view', 'js/view');
    Ext.Loader.setPath('Ext.conf', 'js/conf');

    
    Ext.require([ 
                 'Ext.conf.Constants'
             ]); 
    var messagebar = Ext.create('Ext.view.MessageBar');
    

    var  onSuccess=  function (form, action){
        response = Ext.JSON.decode(action.response.responseText);
        messagebar.showMessage(response.message,'OK'); 
        getStore().reload();  
    };

    var onFailure=function(form, action){
        if(action.failureType == 'server'){
            response = Ext.JSON.decode(action.response.responseText);
            messagebar.showMessage(response.message,'ERROR');      
        }else{
        	 messagebar.showMessage('Authentication server is unreachable : ' + action.response.responseText,'ERROR');        
        }   
    };
    
    var  onAjaxSuccess=  function (response){
        response = Ext.JSON.decode(response.responseText);
        messagebar.showMessage(response.message,'OK');  
    };

    var onAjaxFailure = function (response){
        response = Ext.JSON.decode(response.responseText);
        messagebar.showMessage(response.message,'ERROR');   
    }
   
</script>    