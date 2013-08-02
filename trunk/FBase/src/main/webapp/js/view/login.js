
Ext.onReady(function() {
    
    var btnCancel= Ext.create('Ext.Button',{
        text: 'Close',
        width: 75,
        handler:function() {
            var form = this.up('form').getForm();
            form.reset();
        }
    });
    var btnLogin=  Ext.create('Ext.Button',{
        text: 'Login',
        width: 75,
        handler:function() {
            var form = this.up('form').getForm();
            form.url= './Welcome.action';
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


    var loginForm = Ext.create('Ext.form.Panel', {
        id:'login-form',
       
        border:false,
        frame:false,
        bodyStyle:'background-color:transparent',
        padding:'125px 0 0 50px ',
        renderTo:Ext.get('login'),
        fieldDefaults: {
            msgTarget: 'side',
            labelWidth: 100,
            labelStyle:'color:white'
        },
        defaults: {
            width:300
        },
        items: [{
            xtype: 'textfield',
            fieldLabel: 'Username' ,
            name: 'username',
            allowBlank:false
        }, {
            xtype: 'textfield',
            inputType: 'password',
            fieldLabel: 'Password' ,
            name: 'password'
        }],
        dockedItems: [{
            xtype: 'toolbar',
            ui: 'footer',
            dock: 'bottom',
            layout: {
                type: 'hbox',
                pack: 'center'
            },
            items: [btnLogin, btnCancel]
        }]
    });
    loginForm.getForm().findField('username').focus();
    var  onSuccess=  function (form, action){
    	alert('onSuccess');
        response = Ext.JSON.decode(action.response.responseText);
        window.location = 'home.php';

    }

    var onFailure=function(form, action){
    	alert('onFailure');
        form.reset();
        if(action.failureType == 'server'){
            response = Ext.JSON.decode(action.response.responseText);
            Ext.MessageBox.show({
                title: 'ITGOA',
                msg: response.message,
                buttons: Ext.MessageBox.OK,
                icon: Ext.MessageBox.ERROR
            });
        }else{
            Ext.MessageBox.show({
                title: 'ITGOA',
                msg: 'Authentication server is unreachable : ' + action.response.responseText,
                buttons: Ext.MessageBox.OK,
                icon: Ext.MessageBox.ERROR
            });
        }
    }



});




