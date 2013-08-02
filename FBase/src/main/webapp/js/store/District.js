
Ext.define('Ext.store.District', {
    extend: 'Ext.data.ArrayStore',
    fields: [
             {name: 'district'}
         ],
         data: [['Alappuzha'], ['Ernakulam'],['Idukki'],['Kannur'],['Kasaragod'],['Kollam'],['Kottayam'],['Kozhikode'],
                ['Malappuram'],['Palakkad'],['Pathanamthitta'],['Thiruvananthapuram'],['Thrissur'],['Wayanad']],
    autoLoad: true
});


