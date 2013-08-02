menu = 	[{
        xtype:'label',
        text: 'Menu',
        width:125,
        style:'padding:5px 5px 5px 50px',
        defaultType:'splitbutton'
    },{
        text: 'Employees',
        iconCls: 'employee-add-icon',
        menu: [{
            text: 'Add Employee',
            handler:function(){
                window.location = '../employee/add_employee.php';
            }
        },{
            text: 'View Employees',
            handler:function(){
                window.location = '../employee/view_employee.php';
            }
        },{
            text: 'Add Designation',
            handler:function(){
                window.location = '../employee/add_designation.php';
            }
        },{
            text: 'View Designation',
            handler:function(){
                window.location = '../employee/view_designation.php';
            }
        }]
    }];
    
