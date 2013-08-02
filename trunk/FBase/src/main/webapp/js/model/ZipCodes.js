Ext.define('Ext.model.ZipCodes', {

	extend: 'Ext.data.Model',
	fields: [{
		name : 'zipCode',
		type : 'string'
	},{
		name : 'state',
		type : 'string'
	},{
		name : 'district',
		type : 'string'
	},{
		name : 'country',
		type : 'string'
	},{
		name : 'post',
		type : 'string'
	}]

});

