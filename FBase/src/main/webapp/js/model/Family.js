Ext.define('Ext.model.Family', {

	extend: 'Ext.data.Model',
	fields: [{
		name : 'familyId',
		type : 'string'
	},{
		name : 'familyName',
		type : 'string'
	},{
		name : 'addressLine1',
		type : 'string'
	},{
		name : 'addressLine2',
		type : 'string'
	},{
		name : 'addressLine3',
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
		name : 'zipCode',
		type : 'string'
	},{
		name : 'status',
		type : 'string'
	}]

});

