Ext.define('Ext.view.Navigation', {
    extend: 'Ext.tree.Panel',
    xtype: 'navigation',
    title: 'Menu',
    rootVisible: false,
    lines: true,
    useArrows: true,
    root: {
        expanded: true,
        children: [
            {
                text: 'Family',
                expanded: false,
                children: [
                    {
	                    id:"{title:'Create Family',url:'./createFamily.html?Action=init&Mode=Create'}",
	                    text : "Create Family",
	                    leaf : true,
	                    iconCls:'icon-save'
                    }, {
	                    id:"{title:'View Family',url:'./viewFamily.html?Action=init'}",
	                    text : "View Family",
	                    leaf : true
                    }
                ]
            },{
                text: 'Reports',
                expanded: false,
                children: [
                    {
	                    id:"{title:'New Family1',url:'./family.do?Action=redirect&Mode=create'}",
	                    text : "New Family",
	                    leaf : true,
	                    iconCls:'icon-save'
                    }, {
	                    id:"{title:'View Family1',url:'./family.do?Action=redirect&Mode=view'}",
	                    text : "View Family",
	                    leaf : true
                    }
                ]
            },{
                text:'Configurations',
                expanded: false,
                children: [
                    {
	                    id:"{title:'Zip Codes',url:'./zipCodes.html'}",
	                    text : "Zip Codes",
	                    leaf : true,
	                    iconCls:'icon-save'
                    }, {
	                    id:"{title:'View Family1',url:'./family.do?Action=redirect&Mode=view'}",
	                    text : "View Family",
	                    leaf : true
                    }
                ]
            }
        ]
    },listeners: {
        itemclick: {
            fn: function(view, record, item, index, event) {
                var id= Ext.JSON.decode(record.data.id);
                var url =id.url;
                var title = id.title;                   
                setSrc(title,url) ;
            }
        }

    }
});