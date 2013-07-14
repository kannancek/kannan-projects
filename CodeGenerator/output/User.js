Ext.define('Ext.model.User', {

	extend: 'Ext.data.Model',
	fields: [{
		name : 'username',
		type : 'string'
	},{
		name : 'password',
		type : 'string'
	},{
		name : 'name',
		type : 'string'
	},{
		name : 'role',
		type : 'string'
	},{
		name : 'status',
		type : 'string'
	},{
		name : 'creationDate',
		type : 'string'
	}]

});

